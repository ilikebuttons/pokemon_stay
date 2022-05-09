<jsp:include page="../include/header.jsp">
    <jsp:param name="teamName" value="${teamName}" />
    <jsp:param name="coins" value="${coins}" />
</jsp:include>

<%--<div id="leftMenu" class="sidebar">
    Side
</div>--%>

<%-- IMPORTS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/components/pokemons.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/components/trainers.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/components/locations.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/components/adventures.js"></script>

<div class="content">
    <div id="mapContainer">
        <svg xmlns="http://www.w3.org/2000/svg" <%--viewBox="0 0 56.69 56.69"--%> id="map" width="920" height="902"></svg>
        <img id="mapImg" width="920" height="902" src="./../../../pub/images/map.png">
    </div>

    <%--<jsp:include page="./assign_to_area.jsp"/>--%>

    <div id="assignmentModal" class="modal">
        <form class="modal-content" id="assignForm">

            <input type="hidden" id="trainerId" name="trainerId">
            <input type="hidden" id="locationId" name="locationId">

            <span class="close">&times;</span>

            <h2 id="trainerName"></h2>
            <br>
            <h3>Pallet Town to <span id="locationName"></span>: <span id="distance"></span></h3>
            <br>
            Pokeballs: <input type="number" name="pokeballs" id="pokeballs"/>
            <br>
            Potions: <input type="number" name="potions" id="potions"/>
            <br>
            <button type="submit" id="submitBtn">"Send On Adventure!"</button>
            <br>
        </form>

        <ul id="#errors">

        </ul>
    </div>

    <ul id="trainers"></ul>

</div>

<script>
    getMap();
    getTrainers();
    // TODO ensure getMap and getTrainers execute in order

    //getTrainerLocations();
</script>

<script>
    let eventHandlersHaveBeenSetup = false;

    const modal = document.querySelector("#assignmentModal");
    const assignForm = document.querySelector("#assignForm");
    const closeBtn = document.querySelector(".close");

    /*const errorListEle = document.querySelector("#errors");*/

    const showAssignmentModal = (tName, tId, locName, locId) => {
        $.ajax({
            url: '/getDistance',
            type: 'GET',
            data: {"fromId": 1, "toId": locId},
            success: (distance) => {
                $('#distance').text((Math.round(distance * 100) / 100).toFixed(2) + " km");

                $('#trainerName').text(tName);      // non-input
                $('#trainerId').val(tId);

                $('#locationName').text(locName);    // non-input
                $('#locationId').val(locId);

                $('#assignmentModal').css('display', 'block');
            },
            error: (error) => {
                alert("Trainer must be equipped with at least one Pokeball!")
                console.log(error)
                /*for (let errorMsg of error) {
                    let errorEle = document.createElement("li");
                    errorEle.innerText = errorMsg;
                    errorListEle.appendChild(errorEle);
                }
                console.log("error = " + error + "  " + request)*/
            }
        });
    }

    // DRAG HANDLERS
    let dragged;

    setupDragHandlers = () => {
        if (eventHandlersHaveBeenSetup === true) return;

        assignForm.addEventListener('submit', (e) => {
            e.preventDefault();
            //e.stopPropagation();

            const formData = new FormData(e.target);
            const {trainerId, locationId, pokeballs, potions} = Object.fromEntries(formData.entries());

            $.ajax({
                url: '/game/assignSubmit',
                type: "POST",
                data: {
                    "trainerId": trainerId,
                    "locationId": locationId,
                    "pokeballs": pokeballs ? pokeballs : 0,
                    "potions": potions ? potions : 0
                },
                success: (data) => {
                    console.log("success");
                    // TODO show assignment on map
                    console.log(data);
                    closeModal();
                    getTrainers();
                },
                error: ({status, responseText}) => {
                    if (status == 400) {
                        const errors = JSON.parse(responseText);

                        for (const errorMsg of errors) {
                            console.log(errorMsg);
                        }
                    } else {
                        console.log("Something went wrong");
                    }
                }
            });
        });

        document.addEventListener("dragstart", (e) => {
            if (!e.target.classList.contains("trainer")) {
                e.preventDefault();
                return false;
            }

            const pokelist = e.target.nextElementSibling;
            if (pokelist.style.maxHeight) {
                pokelist.style.maxHeight = null;
            }
            /*if (!pokelist.classList.contains('collapsed')) {
                pokelist.style.maxHeight = null;
                pokelist.classList.add('collapsed');
            }*/

            e.dataTransfer.setData('text/plain', null);
            dragged = e.target;
            // make it half transparent
            e.target.style.opacity = .5;
        }, false);

        document.addEventListener("dragend", (e) => {
            if (!e.target.classList.contains("trainer")) return false;
            // reset the transparency
            e.target.style.opacity = "";
            dragged = null;
        }, false);

        document.addEventListener("dragover", (e) => {
            e.preventDefault();
        }, false);

        document.addEventListener("dragenter", (e) => {
            if (dragged === null || !dragged.classList.contains("trainer")) return false;
            if (e.target.classList.contains("dropzone")) {
                e.target.classList.add('cursor');
            }
        }, false);

        document.addEventListener("dragleave", (e) => {
            if (dragged === null || !dragged.classList.contains("trainer")) return false;
            if (e.target.classList.contains("dropzone")) {
                e.target.classList.remove('cursor');
            }
        }, false);

        document.addEventListener("drop", (e) => {
            e.preventDefault();
            console.log(dragged);
            if (dragged === null || !dragged.classList.contains("trainer")) return false;
            // move dragged elem to the selected drop target
            if (e.target.classList.contains("dropzone")) {

                // Trainer
                let tName = dragged.dataset.name;
                let tId = dragged.dataset.index;
                //console.log('tName: ' + tName);
                //console.log('tId: ' + tId);

                // Location
                let locName = e.target.dataset.name;
                let locId = e.target.dataset.index;
                //console.log('locName: ' + locName);
                //console.log('locId: ' + locId);

                showAssignmentModal(tName, tId, locName, locId);

                e.target.classList.remove('cursor');
                //e.target.appendChild( dragged ); //TODO insert trainer pin on location
            }
        }, false);

        // Close Modal
        const closeModal = () => {
            modal.style.display = "none";
            // TODO reset values
        }

        $('.close').click(() => {
            closeModal();
        });

        window.onclick = (e) => {
            if (e.target === modal) {   // works because modal fills the screen
                closeModal();
            }
        }

        eventHandlersHaveBeenSetup = true;
    }

</script>

<jsp:include page="../include/footer.jsp"/>