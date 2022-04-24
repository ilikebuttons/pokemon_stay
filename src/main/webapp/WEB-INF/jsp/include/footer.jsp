
<%--<div class="footer">My footer</div>--%>
<%--<a href="https://www.flaticon.com/free-icons/pokemon" title="pokemon icons">Pokemon icons created by Nikita Golubev - Flaticon</a>--%>
</body>

<script>
    let dropdown = document.querySelector('.dropdown-content');
    let title = document.querySelector('#nav-title');
    title.addEventListener("click", () => {
        if (dropdown.style.display !== "block") {
            dropdown.style.display = "block";
        } else {
            dropdown.style.display = "none";
        }
    });

    //TODO
    /*$(document).ready(() => {

        // Show
        $("#nav-title").click(() => {
            $(this).find(".dropdown-menu").slideToggle("fast");
        });
    });
    $(document).on("click", (e) => {
        let $trigger = $(".dropdown");
        if ($trigger !== e.target && !$trigger.has(e.target).length) {
            $(".dropdown-menu").slideUp("fast");
        }
    });*/


</script>

</html>
