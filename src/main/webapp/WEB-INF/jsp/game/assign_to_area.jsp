
<div id="assignmentModal" class="modal">
    <form class="modal-content" id="assignForm">

        <input type="hidden" id="trainerId" name="trainerId">
        <input type="hidden" id="locationId" name="locationId">

        <span class="close">&times;</span>

        <h2 id="trainerName"></h2>
        <br>
        <h3>Pallet Town to <span id="locationName"></span>: <span id="distance"></span></h3>
        <br>
        Pokeballs: <input type="number" name="pokeballs" id="pokeballs" value="0"/>
        <br>
        Potions: <input type="number" name="potions" id="potions" value="0"/>
        <br>
        <button type="submit" id="submitBtn">"Send On Adventure!"</button>
        <br>
    </form>
</div>