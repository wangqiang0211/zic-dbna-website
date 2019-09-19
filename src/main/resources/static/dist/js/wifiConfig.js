$(function() {
	$(document).on("click",".bind_jie_btn",function(){
		location.replace("bind.html")
	})
	var appId = null;
	var timestamp = null;
	var nonceStr = null;
	var signature = null;
	$.ajax({
		type: "get",
		async: true,
		url: "http://www.cdzic.cn/getSignPackage",
		data: {},
		dataType: "json",
		success: function(result) {
			console.log(result)
			appId = result.appId;
			timestamp = result.timestamp;
			nonceStr = result.nonceStr;
		//	signature = result.signature;
			jsapiTicket = result.jsapiTicket;
			str = result.rawString;
			sha1 = CryptoJS.SHA1(str);
			
			str = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + location.href;
			sha1 = CryptoJS.SHA1(str);
			wx.config({
								beta : true,
				debug: true,
				appId: appId,
				timestamp: timestamp,
				nonceStr: nonceStr,
				signature: sha1.toString(),
				jsApiList: ['configWXDeviceWiFi']
			});
			wx.ready(function() {
				document.querySelector('#scanQRCode0').onclick = function() {
					
					WeixinJSBridge.invoke('configWXDeviceWiFi', {}, function(res) {

						if(res.err_msg == 'configWXDeviceWiFi:ok') {
							console.log('configWXDeviceWiFi is successed .')
							layer.open({
								content: '配置成功',
								skin: 'msg',
								time: 2
							});
											//navigatToBindPage();
											//wx.closeWindow();
							
						} else if(res.err_msg == 'configWXDeviceWiFi:fail') {
							console.log('configWXDeviceWiFi is failed. ' + res.err_msg)
							layer.open({
								content: 'WiFi配置失败，重新进去页面进入配置',
								skin: 'msg',
								time: 4
							});
						} else if(res.err_msg == 'configWXDeviceWiFi:cancel') {
							console.log('configWXDeviceWiFi is cancel. ' + res.err_msg)
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
//				alert("出错啦11111")
				layer.open({
					content: '微信Server异常：原因：' + res.errMsg + '</br> 请重新进入页面',
					skin: 'msg',
					time: 6
				});
			});
		}
	})
		function navigatToBindPage(){
//			location.replace("zhu/equipment.html")
		}
})