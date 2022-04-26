
<%--<div class="footer">My footer</div>--%>
<%--<a href="https://www.flaticon.com/free-icons/pokemon" title="pokemon icons">Pokemon icons created by Nikita Golubev - Flaticon</a>--%>
</body>

<script>
    let dropdown = document.querySelector('.dropdown-content');
    let title = document.querySelector('.fa-caret-down');
    title.addEventListener("click", (e) => {
        if (dropdown.style.display !== "block") {
            dropdown.style.display = "block";
        } else {
            dropdown.style.display = "none";
        }
    });

    const dmCheckEle = document.querySelector(".dmcheck");
    dmCheckEle.addEventListener("change", (e) => {
        console.log(dmCheckEle.checked);
        if (dmCheckEle.checked) {
            document.documentElement.style.setProperty('--bg', 'rgb(12, 13, 28);');
            document.documentElement.style.setProperty('--main', 'white');
        } else {
            document.documentElement.style.setProperty('--bg', 'white');
            document.documentElement.style.setProperty('--main', 'rgb(12, 13, 28);');
        }
    });


    //document.documentElement.style.setProperty('--your-variable', '#YOURCOLOR');
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
