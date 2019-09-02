$(function () {
    var index = 5;
    setInterval(function () {
        $("#part1-top").css("background-image","url(img/pic/fit"+index+".jpg)");
        index++;
        if(index > 7){
            index = 4;
        }
    },3000);
})