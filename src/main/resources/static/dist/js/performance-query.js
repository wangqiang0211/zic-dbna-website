$(function () {
    /**
     * 选择时间
     */
    laydate.render({
        elem: '#test1',
        value: new Date()
    });
    laydate.render({
        elem: '#test2',
        value: new Date()
    });

    var time1, time2, userCode;
    //查询事件
    var loadIndex;
    $(".queryBtn").click(function () {
        time1 = $("#test1").val();
        time2 = $("#test2").val();
        userCode = $("#userCodes").val();
        if (time1 == null || time1 == '' || time2 == null || time2 == '') {
            layer.open({
                content:"请选择时间"
                ,skin: 'msg'
                ,time: 2 //2秒后自动关闭
            });
        } else if (userCode == '' || userCode == null) {
            layer.open({
                content:"请输入正确的编码"
                ,skin: 'msg'
                ,time: 2 //2秒后自动关闭
            });
        } else {
            loadIndex = layer.open({type: 2});
            $.ajax({
                type: "get",
                url: path + "/pq/query",
                data: {
                    code: userCode,
                    startDate: time1,
                    stopDate: time2
                },
                success: function (data) {
                    // console.log(data)
                    if (data.errorCode == 100) {
                        $(".userName").text(data.respObj.name);
                        $(".userCode").text(data.respObj.code);
                        $(".userNumber span").text(data.respObj.memberNum + '人');
                        $(".mothOne span").text(data.respObj.payNum1);
                        $(".mothTwo span").text(data.respObj.payNum2);
                        $(".againOne span").text(data.respObj.againNum1);
                        $(".againTwo span").text(data.respObj.againNum2);
                        $(".allOne span").text(data.respObj.allNum1);
                        $(".allTwo span").text(data.respObj.allNum2);
                        layer.close(loadIndex);
                    } else {
                        layer.close(loadIndex);
                        layer.open({
                            content:data.errorMsg
                            ,skin: 'msg'
                            ,time: 2 //2秒后自动关闭
                        });
                    }
                }, error: function (jqXHR, textStatus, errorMsg) {
                    layer.close(loadIndex);
                    layer.open({
                        content:'请求服务器' + '时出错[' + jqXHR.status + ']：' + errorMsg
                        ,skin: 'msg'
                        ,time: 2 //2秒后自动关闭
                    });
                }
            })
        }
    })
});