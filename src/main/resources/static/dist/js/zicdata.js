$(function () {
    var contain = $(".zicdata_con")[0];
    var lists = $(".zicdata_con ul")[0];
    $(".data_Delivery").click(function () {//显示数据发送页面
        $(".zicdata_box").addClass("db").removeClass("dn");
        $(".list-block").addClass("dn").removeClass("db");
    });
    $(".data_uid span").html(respObj.deviceName);
    $(".zic_uid").html(respObj.toDevice);//设备uid
    var currentdate;//时间
    var jsonFromDevice;//返回设备uid
    var jsonData;
    var zic_uid = respObj.deviceName;
    var zic_appKey = respObj.productKey;
    var zic_appSecret = respObj.deviceSecret;
    var zic_client = new zic_clound(zic_uid, zic_appKey, zic_appSecret);

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
        if(respObj.bindDeviceModel=="o2o"){
            subscription();
        }else {
            subscriptions();
        }
        zic_client.sendMSG(respObj.toDevice + "&TX", "getStatus");
        layer.open({
            content: "连接设备成功",
            skin: "msg",
            time: 2
        })
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
            })
        }
    }

    //接收消息回调
    function zic_msg(data) {
        var dataSucc = data.payloadString;
        // alert(dataSucc);
        var jsonT = JSON.parse(dataSucc);//字符串转为json
        var jsonType=jsonT.type;
        jsonFromDevice=jsonT.fd;
        jsonData = jsonT.data;//返回设备消息
        if("c"==jsonType){
            var baseT = new Base64();
            jsonData = baseT.decode(jsonData);//解密内容base64   data里的base64
        }else if("s"==jsonType){
            $(".zic_state").addClass("zic_stateOn").removeClass("zic_stateNoton");
            $(".zic_state").html("在线");
            return;
        }
            times();
            var listSize = "<li ><div class='clearfix'><p class='fl zic_deviceUid'>" + jsonFromDevice + "</p><p class='fl zic_time'>" + currentdate + "</p></div><div class='clearfix'><p class='pimg fl'><img src='../dist/img/logo1.png' /></p><p class='fl zicsart_texts'>" + jsonData + "</p></div></li>";
            var ulls = $(".zicdata_con ul");
            ulls.append(listSize);
            contain.scrollTop = lists.offsetHeight;//数据滚动
    }

    //消息发送成功回调
    function zic_msgsuccess(data) {
        console.log(data)
    }

    //绑定设备
    function subscription() {
        var topic = zic_uid + "&RX";
        zic_client.subscription(topic, function (data) {
            console.log(data.topic + "***" + data.status + "***" + "订阅成功")
            // alert("o2o"+data.status)
        })
    }

    function subscriptions() {
        var topic = respObj.toDevice + "&SM";
        zic_client.subscriptions(topic,function (data) {
            // alert("o2m---status****:"+data.status+"&topic:"+data.topic)
        })
    }

    //解绑设备
    function unsubscription() {
        var topic = zic_uid + "&RX";
        zic_client.unsubscription(topic, function (data) {
            console.log(data.topic + "***" + data.status + "***" + "取消订阅成功")
        })
    }

    //发送消息
    function sendMsg() {
        var topic = jsonData + "&TX";
        zic_client.sendMSG(topic, "00010100")
    }
    //清除ul
    $(".delBtn").click(function () {
        $(".zicdata_con ul li").remove();
    });
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

    function times() {//获取当前的时间
        function p(s) {
            return s < 10 ? '0' + s : s;
        }

        var myDate = new Date();
        // var month=myDate.getMonth()+1;//获取当前月
        // var date=myDate.getDate();//获取当前日
        var h = myDate.getHours();       //获取当前小时数(0-23)
        var m = myDate.getMinutes();     //获取当前分钟数(0-59)
        var s = myDate.getSeconds();

        currentdate = p(h) + ':' + p(m) + ":" + p(s);
    }

    $(document).on("click", ".data_sub", function () {
        times();
        var reg = /^[0-9a-zA-Z]+$/;
        var dataInput = $.trim($(".data_input input").val());
        if (dataInput == "" || dataInput == null) {
            layer.open({
                content: "请输入发送的指令",
                skin: "msg",
                time: 2
            })
        } else  if(!reg.test(dataInput)){
            layer.open({
                content: "不能输入汉字或者空格",
                skin: "msg",
                time: 2
            })
        } else {
            var list = "<li><div class='clearfix'><p class='fl zic_deviceUid'>" + respObj.deviceName + "</p><p class='fl zic_time'>" + currentdate + "</p></div><div class='clearfix'><p class='pimg fr'><img src='../dist/img/logo1.png' /></p><p class='fl zicsart_text'>" + dataInput + "</p></div></li>";
            var ull = $(".zicdata_con ul");
            ull.append(list);
            contain.scrollTop = lists.offsetHeight;//数据滚动
            zic_client.sendMSG(respObj.toDevice + "&TX", dataInput);
            $(".data_input input").val("");
        }
    });

});