$(function() {
	var appId = respObj.appId;
	var timestamp = respObj.timestamp;
	var nonceStr = respObj.noncestr;
	var jsapiTicket = respObj.ticket;

   var  str = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + location.href;
   var  sha1 = CryptoJS.SHA1(str);
	wx.config({
//		beta : true,
//		debug: true,
		appId: appId,
		timestamp: timestamp,
		nonceStr: nonceStr,
		signature: sha1.toString(),
		jsApiList: ['configWXDeviceWiFi']
	});
	wx.ready(function() {
		document.querySelector('.wififooter').onclick = function() {//点击配置wifi
			WeixinJSBridge.invoke('configWXDeviceWiFi', {}, function(res) {
				if(res.err_msg == 'configWXDeviceWiFi:ok') {
					console.log('configWXDeviceWiFi is successed');
					layer.open({
						content: '配置成功',
						skin: 'msg',
						time: 2
					});
					navigatToBindPage();
					//wx.closeWindow();
                    // alert("***"+"11111");
				} else if(res.err_msg == 'configWXDeviceWiFi:fail') {
					console.log('configWXDeviceWiFi is failed. ' + res.err_msg);
					layer.open({
						content: 'WiFi配置失败，重新进去页面进入配置',
						skin: 'msg',
						time: 4
					});
				} else if(res.err_msg == 'configWXDeviceWiFi:cancel') {
					console.log('configWXDeviceWiFi is cancel. ' + res.err_msg);
				} else {
					layer.open({
						content: '调用微信airkiss失败，请退出重试！',
						skin: 'msg',
						time: 4
					});
					console.log("注入configWXDeviceWiFi错误信息： " + JSON.stringify(res));
				}
				console.log(JSON.stringify(res));
			});
		};
	});
	wx.error(function(res) {
		layer.open({
			content: '微信Server异常：原因：' + res.errMsg + '</br> 请重新进入页面',
			skin: 'msg',
			time: 6
		});
	});
$(".wifibind").click(function () {
   	 navigatToBindPage();
})

function navigatToBindPage() {
		location.replace("/zic_wechat/cdzic/wechat-bind");
	}
})