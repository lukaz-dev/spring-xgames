function loadGame(code) {
    $('#modalBodyGameDetails').load('/games/search/' + code);
    setTimeout(function () {
        $('#showGameDetailsModal').modal('show');
    }, 500);
};

$('#pageSizeSelect').change(function(evt) {
    window.location.replace("/games?size=" + this.value + "&page=1");
});
