<jsp:include page="../include/header.jsp">
    <jsp:param name="teamName" value="${teamName}" />
    <jsp:param name="coins" value="${coins}" />
</jsp:include>

<%--<div id="leftMenu" class="sidebar">
    Side
</div>--%>

<div class="content">
    <jsp:include page="./map.jsp"/>
    <ul id="trainers"></ul>
</div>

<script>

    const createPokemonList = (pokemons) => {
        let ul = document.createElement('ul');
        for (let pokemon of pokemons) {
            let li = document.createElement('li');
            li.innerText = pokemon;
            li.backgroundColor = "green";
            ul.append(li);
        }
        return ul;
    };

    const getTrainers = () => {
        $.ajax({
            url: '/getTrainers',
            type: 'GET',
            data: {},
            success: (data) => {
                const trainers = JSON.parse(data);
                console.log(data);
                $('#trainers').append(
                    trainers.map(trainer =>
                        $(`

                            <li>\${trainer.name}</li>

                        `).append(
                            trainer.pokemons.map(pokemon =>
                                $(`

                                    <li style="padding-left: 10px;">\${pokemon.name}</li>

                                `)
                            )
                        )
                    )
                );
            },
            error: (request, error) => {
                console.log("error = " + error + "  " + request)
            }
        });
    }

    getTrainers();

</script>
<jsp:include page="../include/footer.jsp"/>