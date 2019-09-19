$(function () {

    var appId = respObj.appId;
    var timestamp = respObj.timestamp;
    var nonceStr = respObj.noncestr;
    var jsapiTicket = respObj.ticket;
    var  str = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + location.href;
    var  sha1 = CryptoJS.SHA1(str);
    wx.config({
//		debug: true,
        appId: appId,
        timestamp: timestamp,
        nonceStr: nonceStr,
        signature: sha1.toString(),
        jsApiList: ['scanQRCode']
    });
    wx.error(function (res) {
        layer.open({
            content: '微信配置错误：' + res.errMsg,
            skin: 'msg',
            time: 6
        });
    });
    var deviceModel;
    wx.ready(function () {
        // 9.1.2 扫描二维码并返回结果
        document.querySelector('.bind_img').onclick = function () {
            wx.scanQRCode({
                needResult: 1,
                desc: 'scanQRCode desc',
                success: function (res) {
                    var obj = JSON.parse(res.resultStr);
                    var uid = obj.uid;
                    var Secret = obj.appkey;
                    deviceModel=obj.model;
                    if(deviceModel=="o2o" || deviceModel=="o2m" ||undefined!=uid || undefined!=Secret ){
                        $(".Secret").val(Secret);
                        $(".Uid").val(uid);
                        $(".Secret").attr("disabled", true);
                        $(".Uid").attr("disabled", true);
                    }else {
                        layer.open({
                            content:"无效二维码",
                            skin:"msg",
                            time:2
                        })
                    }
                    // if(undefined==uid || undefined==Secret || undefined==deviceModel  )

                }
            });
        };
    });
    //确定事件
    $(".bindbtn").click(function () {
        var inputSecret = $(".Secret").val();
        var inputUid = $(".Uid").val();
        var name = $(".name").val();
        var userAccount = $(".userAccount").val();
        var sex = $(".sex").val();
        var theName = $(".theName").val();
        if (inputSecret == "" || inputSecret == null || inputSecret == undefined) {
            layer.open({
                content: "请获取APPKEY",
                skin: "msg",
                time: 2
            })
        } else if (inputUid == "" || inputUid == null || inputUid == undefined) {
            layer.open({
                content: "请获取设备UID",
                skin: "msg",
                time: 2
            })
        } else if (name == "" || name == null || name == undefined){
            layer.open({
                content: "请输入姓名",
                skin: "msg",
                time: 2
            })
        } else if (userAccount == "" || userAccount == null || userAccount == undefined){
            layer.open({
                content: "请输入账号",
                skin: "msg",
                time: 2
            })
        } else if (sex == "" || sex == null || sex == undefined){
            layer.open({
                content: "请输入性别",
                skin: "msg",
                time: 2
            })
        } else if (theName == "" || theName == null || theName == undefined){
            layer.open({
                content: "请输入住址",
                skin: "msg",
                time: 2
            })
        }else {
            $(".bindbtn button").attr("disabled",true);
            $.ajax({
                type: "post",
                url: "/zic_wechat/cdzic/wechat-bind-interface1",
                data:{
                    deviceName:inputUid,
                    equipmentCoding:inputSecret,
                    // deviceModel:deviceModel
                    userAccount:userAccount,
                    name:name,
                    sex:sex,
                    theName:theName,
                },
                success: function (data) {
                    if(data.errorCode==100){
                        layer.open({
                            content:"绑定设备成功，2秒后将关闭页面",
                            skin:"msg",
                            time:2
                        });
                      setTimeout(function () {
                          wx.closeWindow();
                      },2000)
                    }else {
                        $(".bindbtn button").attr("disabled",false);
                        layer.open({
                            content:"绑定设备失败",
                            skin:"msg",
                            time:2
                        })
                    }
                },
                error:function (data) {
                    layer.open({
                        content:"请求服务求异常",
                        skin:"msg",
                        time:2
                    });
                    $(".bindbtn button").attr("disabled",false);
                }
            })
        }
    })
});