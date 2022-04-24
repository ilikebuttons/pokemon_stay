<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../include/header.jsp"/>

<h1>Trainers</h1>

<form action="/game/trainers/create" method="POST" enctype="multipart/form-data">

    Name: <input type="text" name="name">
    <br>
    Image: <input id="img" type="file" name="file" accept="image/*">
    <br>
    <div id="preview" style="display: none;">
        Preview: <img id="output"/>
        <br>
        X<input type="number" name="thumbnail_x"/>
        <br>
        Y<input type="number" name="thumbnail_y"/>
    </div>
    <input type="submit" value="Submit"/>
</form>

<script>
    const inputEle = document.querySelector("#img");
    const outputEle = document.querySelector("#output");
    const previewEle = document.querySelector("#preview");

    inputEle.addEventListener("change", (e) => {
        outputEle.src = URL.createObjectURL(e.target.files[0]);
        outputEle.onload = () => {
            previewEle.style.display = "block";
            URL.revokeObjectURL(outputEle.src) // free memory
        }
    });
</script>

<jsp:include page="../include/footer.jsp"/>

<%--https://stackoverflow.com/a/27165977/10568210--%>
