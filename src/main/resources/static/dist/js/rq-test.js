$(function () {
    layer.open({
        shadeClose: false,
        type: 2
        , content: '加载中...'
    });
    if (responseObj.errorCode == 100) {
        var respObj = responseObj.respObj;
        var firstUid = '';
        var zic_uid = respObj.deviceName;
        var zic_appSecret = respObj.deviceSecret;
        var appId = respObj.appId;
        var timestamp = respObj.timestamp;
        var nonceStr = respObj.noncestr;
        var jsapiTicket = respObj.ticket;
        // alert(zic_uid+"*"+zic_appSecret+"*"+appId+"*"+timestamp+"*"+nonceStr+"*"+jsapiTicket);
        var str = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + location.href;
        var sha1 = CryptoJS.SHA1(str);
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
                time: 3
            });
            wx.closeWindow();
        });
        var zic_client = new zic_clound(zic_uid, "a1YZEg3eHZe", zic_appSecret);

        function goConnectionS() {
            zic_client.connection(3000, {
                onConnectionSucc: zic_connect,
                onConnectionFail: zic_fail,
                onConnectionLost: zic_lost,
                onMessageArrived: zic_msg,
                onMessageDelivered: zic_msgsuccess
            });
        }

        goConnectionS();

        //连接成功回调
        function zic_connect() {
            layer.closeAll();
            $(".deviceBoxT span").html("在线");
            // subscriptions();
            // zic_client.sendMSG(respObj.toDevice + "&TX", "getStatus");
            // layer.open({
            //     content: "连接设备成功",
            //     skin: "msg",
            //     time: 2
            // })
        }

        //发生错误回调
        function zic_fail(data) {
            console.log(data)
        }

        //断开连接回调
        function zic_lost(data) {
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
                });
            }
            wx.closeWindow();
        }

        //接收消息回调
        function zic_msg(data) {
            // var dataSucc = data.payloadString;
            // // alert(dataSucc);
            // var jsonT = JSON.parse(dataSucc);//字符串转为json
            // var jsonType = jsonT.type;
            // var jsonFromDevice = jsonT.fd;
            // var jsonData = jsonT.data;//返回设备消息
            // if ()
            // if ("c" == jsonType) {
            //     var baseT = new Base64();
            //    var jsonData = baseT.decode(jsonData);//解密内容base64   data里的base64
            // } else if ("s" == jsonType) {
            //     $(".zic_state").addClass("zic_stateOn").removeClass("zic_stateNoton");
            //     $(".zic_state").html("在线");
            //     return;
            // }
            var topic = data.destinationName;
            if (topic.indexOf("broadcast") != 0 && firstUid == topic.split("/")[3].split("&")[0]) {
                var msgJson = JSON.parse(data.payloadString);
                var dataType = msgJson.type;
                var dataMsg = msgJson.data;
                if ("i" == dataType && "59475054" == dataMsg.substring(0, 8) && "9101" == dataMsg.substring(8, 12)) {
                    layer.open({
                        content: '当前设备实际温度为' + parseInt(dataMsg.substring(14, 16), 16) + "℃",
                        btn: '确定',
                        shadeClose: false,
                        yes: function () {
                            clearTimeout(getTemp);
                            layer.closeAll();
                        }
                    });
                }
            }


        }

        //给目标设备同步时间
        function communicateDevice(uid) {
            var curTime = new Date();
            var year = curTime.getFullYear() - 2000;
            var month = curTime.getMonth() + 1;
            var day = curTime.getDate();
            var hours = curTime.getHours();
            var min = curTime.getMinutes();
            var sec = curTime.getSeconds();
            zic_client.sendMSG(uid + "&TX", "5947505400001108010006" +
                timeFormat(year) + timeFormat(month) + timeFormat(day) + timeFormat(hours) + timeFormat(min) + timeFormat(sec));
        }

        function timeFormat(s) {
            var sHex = s.toString(16);
            return sHex.length > 1 ? sHex : "0" + sHex;
        }

        //消息发送成功回调
        function zic_msgsuccess(data) {
            console.log(data)
        }

        var getTemp;

        function subscriptions(uid) {
            var topic = uid + "&SM";
            zic_client.subscriptions(topic, function (data) {
                // alert("o2m---status****:"+data.status+"&topic:"+data.topic)
                if (data.status == 'success') {//成功
                    getTemp = setTimeout(function () {
                        layer.closeAll();
                        layer.open({
                            content: '查询温度超时，请检查设备！',
                            btn: '确定',
                            shadeClose: false,
                            yes: function () {
                                layer.closeAll();
                            }
                        });
                    }, 15000);
                    layer.open({
                        shadeClose: false,
                        type: 2
                        , content: '查询温度中...'
                    });
                    communicateDevice(uid);
                    setTimeout(function () {
                        firstUid = uid;
                        zic_client.sendMSG(uid + "&TX", "59475054000011020100");
                    },500);
                } else {//失败
                    layer.open({
                        content: '订阅设备发生错误，请重新扫码！'
                        , skin: 'msg'
                        , time: 2 //2秒后自动关闭
                    })
                }
            })
        }

        //解绑设备
        function unsubscription(firstUid, uid) {
            var topic = firstUid + "&SM";
            zic_client.unsubscriptions(topic, function (data) {
                firstUid = '';
                subscriptions(uid);
                console.log(data.topic + "***" + data.status + "***" + "取消订阅成功")
            })
        }

        //发送消息
        function sendMsg() {
            var topic = jsonData + "&TX";
            zic_client.sendMSG(topic, "00010100")
        }

        wx.ready(function () {
            // 9.1.2 扫描二维码并返回结果
            document.querySelector('.bind_img').onclick = function () {
                wx.scanQRCode({
                    needResult: 1,
                    desc: 'scanQRCode desc',
                    success: function (res) {
                        var obj = JSON.parse(res.resultStr);
                        var uid = obj.uid;
                        var appkey = obj.appkey;
                        var deviceModel = obj.model;
                        if (deviceModel == "o2m" && undefined != uid && appkey == "a1YZEg3eHZe") {
                            $(".Secret").val(appkey);
                            $(".Uid").val(uid);
                            if (firstUid != '') {
                                unsubscription(firstUid, uid);
                            } else {
                                subscriptions(uid);
                            }
                        } else {
                            layer.open({
                                content: "无效二维码",
                                skin: "msg",
                                time: 2
                            })
                        }
                    }
                });
            };
        });
    } else {
        layer.closeAll();
        layer.open({
            content: responseObj.errorMSG,
            btn: '确定',
            shadeClose: false,
            yes: function () {
                wx.closeWindow();
            }
        });
    }

});