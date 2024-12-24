function showErrorToast(msg) {
    $("#coeToast").html(msg);
    $(".toast").addClass("bg-danger").toast("show");
}