$(function () {
    var appId = respObj.appId;
    var timestamp = respObj.timestamp;
    var nonceStr = respObj.noncestr;
    var jsapiTicket = respObj.ticket;
    var str = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + location.href;
    var sha1 = CryptoJS.SHA1(str);
    wx.config({
//		debug: true,
        appId: appId,
        timestamp: timestamp,
        nonceStr: nonceStr,
        signature: sha1.toString(),
        jsApiList: ['configWXDeviceWiFi']
    });
    wx.error(function (res) {
        layer.open({
            content: '微信配置错误：' + res.errMsg,
            skin: 'msg',
            time: 6
        });
    });
    // alert(respObj.bindDeviceProductKey + "**" + respObj.deviceName + "**" + respObj.deviceSecret);
    var AppendDeciveList = "<li><label><input type='checkbox' value='' class='fl mgc mgc-success mgc-circle' name='device'/><div class='fl decive_name'>设备</div><div class='fl dcive_wire'></div><div class='fl decive_uid'>" + respObj.bindDeviceName + "</div></label></li>";
    $(".device_list ul").append(AppendDeciveList);
    $(".device_btn").click(function () {
        if ($("input[type='checkbox']").is(":checked")) {
            layer.open({
                content: "确定解绑该设备吗？",
                btn: ["确定", "取消"],
                yes: function (index) {
                    $.ajax({
                        type: "post",
                        url: "/zic_wechat/cdzic/wechat-unbind-interface1",
                        data: {},
                        success: function (data) {
                            if (data.errorCode == 100) {
                                if (respObj.bindDeviceModel == "o2m" && null != respObj.deviceName && null != respObj.deviceSecret) {
                                    // alert(respObj.bindDeviceModel + "**" + respObj.deviceName + "**" + respObj.deviceSecret);
                                    $(".device_list ul li").remove();
                                    layer.open({
                                        content: "解除设备成功，2秒后关闭页面",
                                        skin: "msg",
                                        time: 2
                                    })
                                    unsubscription();
                                } else {//无需链接zicjsjdk
                                    $(".device_list ul li").remove();
                                    layer.open({
                                        content: "解除设备成功，2秒后关闭页面",
                                        skin: "msg",
                                        time: 2
                                    })
                                }
                                setTimeout(function () {
                                    wx.closeWindow();
                                }, 2000)
                            } else {
                                layer.open({
                                    content: "解除设备失败",
                                    skin: "msg",
                                    time: 2
                                })
                            }
                        }
                    })
                }
            })
        } else {
            layer.open({
                content: "请选择要解绑的设备",
                skin: "msg",
                time: 2
            })
        }
    });

    var jsonFromDevice;//返回设备uid
    var zic_appKey = respObj.bindDeviceProductKey;
    var zic_uid = respObj.deviceName;
    var zic_appSecret = respObj.deviceSecret;
    var zic_client = new zic_clound(zic_uid, zic_appKey, zic_appSecret);
    if (respObj.bindDeviceModel == "o2m" && null != respObj.deviceName && null != respObj.deviceSecret) {
        // alert(respObj.bindDeviceModel + "**" + respObj.deviceName + "**" + respObj.deviceSecret);
        goConnectionS();
    }

    function goConnectionS() {
        zic_client.connection(3000, {
            onConnectionSucc: zic_connect,
            onConnectionFail: zic_fail,
            onConnectionLost: zic_lost,
            onMessageArrived: zic_msg,
            onMessageDelivered: zic_msgsuccess
        });
    }

    /*连接成功回调*/
    function zic_connect() {
        // subscription();
        // alert("登陆成功");
    }

    /*发生错误回调*/
    function zic_fail(data) {
        console.log(data)
    }

    /*断开连接回调*/
    function zic_lost(data) {
        isOnline = false;
        if (data.errorCode == 8) {
            layer.open({
                content: "设备在其他地方登陆",
                skin: "msg",
                time: 2
            })
        } else {
            layer.open({
                content: "设备断开连接",
                skin: "msg",
                time: 2
            })
        }
    }

    /*接收消息回调*/
    function zic_msg(data) {
        var dataSucc = data.payloadString;
        var jsonT = JSON.parse(dataSucc);//字符串转为json
        // var jsonTemp=jsonObj.data.replace(/\'/g,"\"");//单引号替换为双引号
        jsonFromDevice = jsonT.fromDevice;//返回设备uid
        // jsonData = jsonT.data;//返回设备uid
    }

    /*消息发送成功回调*/
    function zic_msgsuccess(data) {
        console.log(data)
    }

    /*绑定设备*/
    function subscription() {
        var topic = zic_uid + "&RX";
        zic_client.subscription(topic, function (data) {
            console.log(data.topic + "***" + data.status + "***" + "订阅成功")
        })
    }

    /*解绑设备*/
    function unsubscription() {
        var topic = respObj.bindDeviceName + "&SM";
        zic_client.unsubscriptions(topic, function (data) {
            // alert(data.status);
            console.log(data.topic + "***" + data.status + "***" + "取消订阅成功")
        })
    }

    /*发送消息*/
    function sendMsg() {
        var topic = jsonData + "&TX";
        zic_client.sendMSG(topic, "00010100")
    }

    function stringToByte(str) {
        var bytes = new Array();
        var len, c;
        len = str.length;
        for (var i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if (c >= 0x010000 && c <= 0x10FFFF) {
                bytes.push(((c >> 18) & 0x07) | 0xF0);
                bytes.push(((c >> 12) & 0x3F) | 0x80);
                bytes.push(((c >> 6) & 0x3F) | 0x80);
                bytes.push((c & 0x3F) | 0x80);
            } else if (c >= 0x000800 && c <= 0x00FFFF) {
                bytes.push(((c >> 12) & 0x0F) | 0xE0);
                bytes.push(((c >> 6) & 0x3F) | 0x80);
                bytes.push((c & 0x3F) | 0x80);
            } else if (c >= 0x000080 && c <= 0x0007FF) {
                bytes.push(((c >> 6) & 0x1F) | 0xC0);
                bytes.push((c & 0x3F) | 0x80);
            } else {
                bytes.push(c & 0xFF);
            }
        }
        return bytes;
    }
});