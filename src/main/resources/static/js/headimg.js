$(function () {
    displayheadimg();
});
function displayheadimg() {
    $.ajax({
        type: "GET",
        url: "/getHeadImg",
        data: {},
        dataType: "text",
        success: function (data) {
            var headimg = data;
            console.log(headimg);
            $("#head_img").attr("src",headimg);
        }
    })
}