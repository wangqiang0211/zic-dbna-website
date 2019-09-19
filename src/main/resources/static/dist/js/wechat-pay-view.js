$(function () {
    //微信配置 wxJsConfig 是从后台传递的值
    var appId = wxJsConfig.appId;
    var timestamp = wxJsConfig.timestamp;
    var nonceStr = wxJsConfig.noncestr;
    var signature = wxJsConfig.ticket;
    var str = "jsapi_ticket=" + signature + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + location.href;
    var sha1 = CryptoJS.SHA1(str);
    wx.config({
        // debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: appId, // 必填，公众号的唯一标识
        timestamp: timestamp, // 必填，生成签名的时间戳
        nonceStr: nonceStr, // 必填，生成签名的随机串
        signature: sha1.toString(),// 必填，签名
        jsApiList: ['onMenuShareAppMessage', 'onMenuShareTimeline'] // 必填，需要使用的JS接口列表
    });

    wx.ready(function () {   //需在用户可能点击分享按钮前就先调用
        wx.onMenuShareAppMessage({
            title: '腾讯、优酷、爱奇艺VIP', // 分享标题
            desc: '优酷、腾讯视频、爱奇艺、搜狐等12个平台VIP', // 分享描述
            link: 'http://' + serverDomain + '/zic_qwsp/wechat-pay-view1', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: 'http://' + serverDomain + '/video.png', // 分享图标
            type: 'link', // 分享类型,music、video或link，不填默认为link
            dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
            success: function () {
                // 用户点击了分享后执行的回调函数
            }
        });
        wx.onMenuShareTimeline({
            title: '腾讯、优酷、爱奇艺VIP', // 分享标题
            link: 'http://' + serverDomain + '/zic_qwsp/wechat-pay-view1', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: 'http://' + serverDomain + '/video.png', // 分享图标
            success: function () {
                // 用户点击了分享后执行的回调函数
            }
        });
    });


    $(".moth").click(function () {
        // alert("敬请期待！");
        var mothSpan = $(".moth span").html();//钱
        unifiedorder(0);
    });
    $(".year").click(function () {
        // alert("敬请期待！");
        var yearSpan = $(".year span").html();//钱
        unifiedorder(1);
    });

    //下单
    function unifiedorder(type) {
        // return;
        $.ajax({
            type: "post",
            url: path + "/wxpay/unifiedorder",
            data: {
                payType: type
            },
            success: function (data) {
                layer.close(loadIndex);
                if (100 == data.errorCode) {
                    var payObj = data.respObj;
                    if (typeof WeixinJSBridge == "undefined") {
                        if (document.addEventListener) {
                            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
                        } else if (document.attachEvent) {
                            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
                        }
                    } else {
                        WeixinJSBridge.invoke(
                            'getBrandWCPayRequest', {
                                "appId": payObj.appId,   //公众号名称，由商户传入
                                "timeStamp": payObj.timeStamp,         //时间戳，自1970年以来的秒数
                                "nonceStr": payObj.nonceStr, //随机串
                                "package": payObj.package_,
                                "signType": payObj.signType,         //微信签名方式：
                                "paySign": payObj.paySign //微信签名
                            },
                            function (res) {
                                if (res.err_msg == "get_brand_wcpay_request:ok") {
                                    // 使用以上方式判断前端返回,微信团队郑重提示：
                                    //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
                                    getCode();//调用方法，返回激活码给用户
                                }
                            });
                    }
                } else if (403 == data.errorCode) {
                    location.reload();
                } else {
                    layer.open({
                        content:data.errorMsg
                        ,skin: 'msg'
                        ,time: 2 //2秒后自动关闭
                    });
                }
            }
        });
    }

    var loadIndex;
    $.ajaxSetup({
        beforeSend: function (xhr) {
            loadIndex = layer.open({type: 2});
        }, error: function (jqXHR, textStatus, errorMsg) {
            layer.open({
                content:'请求服务器"' + '"时出错[' + jqXHR.status + ']：' + errorMsg
                ,skin: 'msg'
                ,time: 2 //2秒后自动关闭
            });
            // layer.alert('发送请求到"' + this.url + '"时出错[' + jqXHR.status + ']：' + errorMsg, {icon: 5,anim:6});
        }, complete: function (xhr, status) {//status=success/error
            if ('error' == status) {
                layer.close(loadIndex);
            }
        }
    });

    function getCode() {
        $.ajax({
            type: "post",
            url: path + "/wxpay/obtain/code",
            data: {},
            success: function (data) {
                layer.close(loadIndex);
                if (100 == data.errorCode) {
                    layer.open({
                        content: "激活码：" + data.respObj
                        , btn: '我知道了'
                    });
                } else if (403 == data.errorCode) {
                    location.reload();
                } else {
                    layer.open({
                        content: data.errorMsg
                        , btn: '我知道了'
                    });
                }
            }
        });
    }

});