<jsp:include page="../include/header.jsp">
    <jsp:param name="teamName" value="${teamName}" />
    <jsp:param name="coins" value="${coins}" />
</jsp:include>

<%--<div id="leftMenu" class="sidebar">
    Side
</div>--%>

<div class="content">
    <jsp:include page="./map.jsp"/>
    <jsp:include page="./assign_to_area.jsp"/>
    <ul id="trainers"></ul>
</div>

<script>

    const createPokemonList = (pokemons) => {
        let ul = document.createElement('ul');
        for (let pokemon of pokemons) {
            let li = document.createElement('li');
            li.innerText = pokemon;
            //li.style.backgroundColor = "rgba(0,255,42,0.48)";
            ul.append(li);
        }
        return ul;
    };

    const getTrainerLocations = () => {
        $.ajax({
            url: '/game/getTrainerLocations',
            type: 'GET',
            data: {},
            success: (data) => {
                const tLocs = JSON.parse(data);
                console.log(tLocs);
                $.ajax({
                    url: '/game/getTrainerLocations',
                    type: 'GET',
                    data: {},
                    success: (data) => {
                        const tLocs = JSON.parse(data);
                        console.log(tLocs);
                    },
                    error: (request, error) => {
                        console.log("error = " + error + "  " + request)
                    }
                });
               /* $('#tLocEle').append(
                    tLocs.map(tl =>
                        $(`

                            <li class="trainer draggable"
                                data-index="\${trainer.id}"
                                data-name="\${trainer.name}"
                                draggable="true">\${trainer.name}
                            </li>

                        `)
                    )
                );*/
                //setupDragHandlers();
            },
            error: (request, error) => {
                console.log("error = " + error + "  " + request)
            }
        });
    }

    const getTrainers = () => {
        $.ajax({
            url: '/getTrainers',
            type: 'GET',
            data: {},
            success: (data) => {
                const trainers = JSON.parse(data);
                console.log(trainers);
                $('#trainers').append(
                    trainers.map(trainer =>
                        $(`

                            <li class="trainer draggable"
                                data-index="\${trainer.id}"
                                data-name="\${trainer.name}"
                                draggable="true">\${trainer.name}
                            </li>

                        `).append(

                            /*`<li style="padding-left: 10px;">\${pokemon.name}</li>` +*/

                            trainer.pokemons.map(pokemon =>
                                $(`

                                    <li style="padding-left: 10px;">\${pokemon.name}</li>

                                `)
                            )
                        )
                    )
                );
                setupDragHandlers();
            },
            error: (request, error) => {
                console.log("error = " + error + "  " + request)
            }
        });
    }

    getTrainers();
    getTrainerLocations();
</script>

<script>
    const trainerNameEle = document.querySelector("#trainerName");
    const modal = document.querySelector("#assignmentModal");
    const destination = document.querySelector("#locationName");
    const distanceEle = document.querySelector("#distance");
    const submitBtn = document.querySelector("#submitBtn");
    const assignForm = document.querySelector("#assignForm")
    const closeBtn = document.querySelector(".close");
    let dragged;

    // Close Modal
    const closeModal = () => {
        modal.style.display = "none";
        // TODO reset values
    }
    closeBtn.addEventListener("click", closeModal);
    window.onclick = (e) => {
        if (e.target === modal) {   // works because modal fills the screen
            closeModal();
        }
    }

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

                modal.style.display = "block";
            },
            error: (request, error) => {
                console.log("error = " + error + "  " + request)
            }
        });
    }

    /*submitBtn.addEventListener("click", (e) => {
        e.preventDefault();
        let response = {};
        let inputs = modal.querySelectorAll('input');
        console.log(inputs);
        for (let inputEle of inputs) {
            response[inputEle.id] = inputEle.value;
            // TODO reset inputs
        }
        console.log(response);
        closeModal();
    });*/

    const handleSubmit = (e) => {
        e.preventDefault();

        const formData = new FormData(e.target);
        const {trainerId, locationId, pokeballs, potions} = Object.fromEntries(formData.entries());

        $.ajax({
            url: '/game/assignSubmit',
            type: "POST",
            data: {
                "trainerId": trainerId,
                "locationId": locationId,
                "pokeballs": pokeballs,
                "potions": potions
            },
            success: (data) => {
                // TODO show assignment on map
                console.log(data);
                closeModal();
            },
            error: (request, error) => {
                // TODO professor oak error handler
                console.log(request)
            }
        });
    };
    assignForm.addEventListener("submit", handleSubmit);

    setupDragHandlers = () => {

        document.addEventListener("dragstart", (e) => {
            if (!e.target.classList.contains("trainer")) return false;

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
                console.log('tName: ' + tName);
                console.log('tId: ' + tId);

                // Location
                let locName = e.target.dataset.name;
                let locId = e.target.dataset.index;
                console.log('locName: ' + locName);
                console.log('locId: ' + locId);

                showAssignmentModal(tName, tId, locName, locId);

                e.target.classList.remove('cursor');
                //e.target.appendChild( dragged ); //TODO insert trainer pin on location
            }
        }, false);
    }
</script>

<jsp:include page="../include/footer.jsp"/>