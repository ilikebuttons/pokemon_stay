const createMapEle = (data) => {
    const areas = JSON.parse(data);
    //console.log(areas);
    const theMap = document.querySelector('#map');
    for (const area of areas) {
        const path = document.createElementNS('http://www.w3.org/2000/svg',"path");
        path.dataset.name = area.name;
        path.dataset.index = area.id;
        path.classList.add('area', 'dropzone');
        path.setAttributeNS(null, "d", "M " + area.coordinates + " Z");
        theMap.appendChild(path);
    }
}

const getMap = () => {
    $.ajax({
        url: '/getMap',
        type: 'GET',
        data: {},
        success: data => createMapEle(data),
        error: (request, error) => {
            console.log("error = " + error + "  " + request)
        }
    });
}