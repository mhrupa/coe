function showErrorToast(msg) {
    $("#coeToast").html(msg);
    $(".toast").addClass("bg-danger").toast("show");
}

function showSuccessToast(msg) {
    $("#coeToast").html(msg);
    $(".toast").addClass("bg-success").toast("show");
}