<div id="mapContainer">
    <svg xmlns="http://www.w3.org/2000/svg" <%--viewBox="0 0 56.69 56.69"--%> id="map" width="920" height="902">

    </svg>
    <img id="mapImg" width="920" height="902" src="./../../../pub/images/map.png">
</div>
<script>
    const createMapEle = (data) => {
        const areas = JSON.parse(data);
        //console.log(areas);
        const theMap = document.querySelector('#map');
        for (const area of areas) {
            const path = document.createElementNS('http://www.w3.org/2000/svg',"path");
            path.id = "loc_" + (area.index + 1);
            path.classList.add('area');
            path.setAttributeNS(null, "d", "M " + area.coordinates + " Z");
            path.addEventListener("mouseenter", () => {
                path.classList.add('cursor');
            })
            path.addEventListener("mouseleave", () => {
                path.classList.remove('cursor');
            })
            theMap.appendChild(path);
        }
    }

    $.ajax({
        url: '/getMap',
        type: 'GET',
        data: {},
        success: data => createMapEle(data),
        error: (request, error) => {
            console.log("error = " + error + "  " + request)
        }
    });

    /*$(".area").hover(() => {
        $(this).css("http://www.w3.org/2000/svg", "stroke", "#6AFF56");
        $(this).css("http://www.w3.org/2000/svg", "stroke-width", "3px");
    }, () => {
        $(this).css("http://www.w3.org/2000/svg", "stroke", "");
        $(this).css("http://www.w3.org/2000/svg", "stroke-width", "");
    });*/

</script>