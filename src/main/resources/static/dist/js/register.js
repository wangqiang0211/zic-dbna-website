$(function () {
    var countdown = 60;
    $("#btn").click(function () {
        sendemail();
    });

    function sendemail() {//获取验证码
        var obj = $("#btn");
        var phoneNum = $(".registerPhone input").val();

        if (phoneNum == "" || phoneNum == null) {
            layer.open({
                content: '请输入手机号',
                skin: 'msg',
                time: 2 //2秒后自动关闭
            });
            return;
        } else if (!(/^1[34578]\d{9}$/.test($(".registerPhone input").val()))) {
            layer.open({
                content: '手机格式不对',
                skin: 'msg',
                time: 2 //2秒后自动关闭
            });
            return false;
        } else {
            $("#btn").attr("disabled",true);
            $.ajax({
                type: "post",
                url: "/zic_wechat/cdzic/wechat-send-code1",
                data: {
                    phoneNum:phoneNum
                },
                dataType: "json",
                success: function (data) {
                    var jsonT = eval(data);
                    if (jsonT.errorCode == 100) {
                        settime(obj);
                        layer.open({
                            content: '验证码已发送',
                            skin: "msg",
                            time: 1.5
                        });
                        $("#btn").attr("disabled",false);
                    } else {
                        layer.open({
                            content: jsonT.errorMSG,
                            time: 1.5
                        });
                        $("#btn").attr("disabled",false);
                    }
                }
            })
        }
    }


    function settime(obj) { //发送验证码倒计时
        if (countdown == 0) {
            obj.attr('disabled', false);
            //obj.removeattr("disabled");
            obj.val("获取验证码");
            countdown = 60;
            return;
        } else {
            obj.attr('disabled', true);
            obj.val("还有" + countdown + "秒");
            countdown--;
        }
        setTimeout(function () {
            settime(obj);
        }, 1000)
    }

    /*确定事件*/
    $(".registerBtn").click(function () {
        var phoneNum = $(".registerPhone input").val();
        var registerCode = $(".refisterInput input").val();
        if (phoneNum == "" || phoneNum == null || phoneNum == undefined) {
            layer.open({
                content: '请输入手机号',
                skin: 'msg',
                time: 2 //2秒后自动关闭
            });
            return;
        } else if (!(/^1[34578]\d{9}$/.test($(".registerPhone input").val()))) {
            layer.open({
                content: '手机格式不对',
                skin: 'msg',
                time: 2 //2秒后自动关闭
            });
            return false;
        } else if (registerCode == "" || registerCode == null || registerCode == undefined) {
            layer.open({
                content: '请获取验证码',
                skin: 'msg',
                time: 2 //2秒后自动关闭
            });
            return false;
        } else {
            $(".registerBtn button").attr("disabled",true);
            $.ajax({
                type: "post",
                url: "/zic_wechat/cdzic/wechat-validate-code1",
                data: {
                    phoneNum:phoneNum,
                    code:registerCode
                },
                success: function (data) {
                    if (data.errorCode==100) {
                        layer.open({
                            content: "注册成功",
                            skin: "msg",
                            time: 1.5
                        });
                        // location.replace("/cdzic/wechat-index");
                        location.reload();
                        $(".registerBtn button").attr("disabled",true);
                    } else {
                        layer.open({
                            content: "验证码输入错误",
                            skin: "msg",
                            time: 1.5
                        });
                        $(".registerBtn button").attr("disabled",false);
                    }
                }, error: function (res) {
                    console.log(res);
                    $(".registerBtn button").attr("disabled",false);
                }
            })
        }

    })
});