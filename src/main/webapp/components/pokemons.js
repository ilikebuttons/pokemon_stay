// POKEMON LIST
const createPokemonListEle = (pokemons) => {
    const ele = document.createElement('ul');
    ele.classList.add('pokelist', 'collapsed');
    ele.append(...pokemons.map(pokemon => createPokemonEle(pokemon)));
    return ele;
}

// POKEMON
const createPokemonEle = (pokemon) =>  {
    const ele = document.createElement('li');
    ele.innerHTML =
        `
            <li style="display: inline-block">
                <img src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.index}.png" />
                <div style="padding-left: 20px; margin-bottom: 10px">${pokemon.name}</div>
            </li>
        `;
    return ele;
}