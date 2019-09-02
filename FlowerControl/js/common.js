//购买后可得到AppCode，查看方法是在阿里云市场进入买家中心的管理控制台，
//在已购买的服务列表内，找到 智能植物识别（含花卉与杂草），下方AppCode一行即是
//相关截图请查看doc目录下的截图文件
var APP_CODE = "93c5fe22f92441c1a5a2633986ebd616";
var BASE_URL = "http://plantgw.nongbangzhu.cn/";

function post(apiContextUrl, formData, flag) {

    $.ajax ({
        url: BASE_URL + apiContextUrl,
        type: "POST",
        headers: { 'Authorization': 'APPCODE ' + APP_CODE },
        data: formData,
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        error:function(err){
            console.error('请求出错：' + err);
            alert('请求出错：' + err);
        },
        success:function(data){
        	if(flag == 0){
        		plus.storage.setItem("recResult",JSON.stringify(data));
				//alert('请求成功：' + JSON.stringify(data));
				plus.webview.open('../html/showInfo.html', 'new', {}, 'slide-in-right', 200);
        	}else{
        		plus.storage.setItem("info",JSON.stringify(data));
						//alert('请求成功：' + JSON.stringify(data));
				plus.webview.open('../html/detial.html', 'new', {}, 'slide-in-right', 200);
        	}
        },
        complete:function(){
            console.log("请求处理结束。");
        }
    });
}

function encodeImagetoBase64(element) {
	$(".hint").text("图像正在努力转码中，请稍候片刻......");
    var file = element.files[0];
    var reader = new FileReader();
    reader.onloadend = function() {
        $(".link").attr("href",reader.result);
        $(".link").text(reader.result);
        console.log(reader.result);
        $(".hint").text("图像已完成转码，可以进行识别");
       };
    reader.readAsDataURL(file);
}
