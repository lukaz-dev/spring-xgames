$('.mask-price-min').mask('0.000.000,00', {reverse: true});
$('.mask-price-max').mask('0.000.000,00', {reverse: true});

$('#confirmEditModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var title = button.data('title');
    var url = button.data('url-edit');

    var modal = $(this);
    var form = modal.find('form');
    form.attr('action', url);
    modal.find('.modal-body span').html('<strong>Deseja editar o jogo ' + title + ' ?</strong>');
});

function loadGame(code) {
    $('#modalBodyGameDetails').load('/games/search/' + code);
    setTimeout(function () {
        $('#showGameDetailsModal').modal('show');
    }, 500);
};

function excluir(code) {
    swal({
        title: "Tem certeza?",
        text: "Você não poderá recuperar o jogo após a exclusão.",
        type: "warning",
        cancelButtonText: "Cancelar",
        showCancelButton: true,
        confirmButtonColor: "#d33",
        confirmButtonText: "Sim, exclua agora!",
        closeOnConfirm: false,
        showLoaderOnConfirm: true
    }, function () {
        setTimeout(function () {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $.ajax({
                url: '/games/' + code,
                type: 'DELETE',
                beforeSend: function (request) {
                    request.setRequestHeader(header, token);
                },
                success: function (result) {
                    $('#table-games').html(result);
                }
            });
            swal("Excluído!", "O jogo foi excluído com sucesso.", "success");
        }, 1000);
    });
};

$('#search-form').submit(function (event) {
    event.preventDefault();

    var form = $(this);
    var inputs = form.find('input, select');
    var serializedData = form.serialize();

    inputs.prop("disabled", true);

    $.ajax({
        url: '/games/search',
        type: 'POST',
        data: serializedData,
        success: function (result) {
            $('#table-games').html(result);
            inputs.prop("disabled", false);
        }
    });
});
