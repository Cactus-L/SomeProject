$(function () {

    $("#system").load("page-system.html");
    $("#sub_container").load("page-sport.html");

    $(".KeepBottom-item").click(function () {
        $("#sub_container").load($(this).data("page"));
    })
})