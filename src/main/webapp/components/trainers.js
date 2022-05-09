// TRAINER LIST
const createTrainerListEle = (trainers) => {
    const ele = document.createElement('ul');
    ele.id = 'trainers';
    ele.append(...trainers.map(trainer => createTrainerEle(trainer)));

    // TODO refactor
    let retBtns = ele.querySelectorAll(".returnBtn");
    for (let btn of retBtns) {
        btn.addEventListener("click", () => {
            returnHome(btn.id);
        });
    }

    return ele;
}

// TRAINER
const createTrainerEle = (trainer) =>  {
    const ele = document.createElement('li');
    ele.innerHTML =
        `
            <div class="trainer draggable"
                 style="margin-top: 20px;"
                 data-index="${trainer.id}"
                 data-name="${trainer.name}"
                 draggable="true">

                <h3 style="margin: 0">
                    <span>${trainer.name}</span> -
                    <span>
                        ${trainer.trainerLocation ? trainer.trainerLocation.status.toUpperCase()
                            + ' ' + trainer.trainerLocation.location.name : "RESTING @ Headquarters"}
                    </span>
                    <div style="display: ${trainer.trainerLocation ? "inline-block" : "none"}"
                            class="returnBtn" id="${trainer.trainerLocation?.id}">&#9166;</div>
                </h3>
            </div>
        `;

    ele.addEventListener('click', () => {
        const pokelist = ele.lastElementChild;

        // if trainer has pokemon
        if (pokelist.children.length > 0) {
            if (pokelist.style.maxHeight) {
                pokelist.style.maxHeight = null;
            } else {
                pokelist.style.maxHeight = pokelist.scrollHeight + "px";
            }
            /*if (pokelist.classList.contains('collapsed')) {
                pokelist.classList.remove('collapsed');
            } else {
                pokelist.classList.add('collapsed');
            }*/
        }
    })

    ele.appendChild(createPokemonListEle(trainer.pokemons));
    return ele;
}

// GET
const getTrainers = () => {
    $.ajax({
        url: '/getTrainers',
        type: 'GET',
        data: {},
        success: (data) => {
            const trainers = JSON.parse(data);
            $('#trainers').replaceWith(createTrainerListEle(trainers))
            setupDragHandlers(); // TODO how to call this only once?
        },
        error: (request, error) => {}
    });
}

// RETURN HOME
const returnHome = (tlId) => {
    $.ajax({
        url: '/game/returnHome',
        type: "POST",
        data: {
            "tlId": tlId,
        },
        success: (data) => {
            getTrainers();  // TODO Hide return button instead of loading everything
        },
        error: (request, error) => { }
    });
}