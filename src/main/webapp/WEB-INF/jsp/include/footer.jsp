
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
    //TODO convert to JQuery
</script>

</html>