$(function () {
    $(".moth").click(function () {
        var mothSpan=$(".moth span").html();//钱
        ajaxFun(mothSpan)
    });
    $(".year").click(function () {
        var yearSpan=$(".year span").html();//钱
        ajaxFun(yearSpan);
    });
    function ajaxFun(money) {
        layer.msg('敬请期待！', {icon: 6});
        return;
        layer.open({
            type: 3,
            shadeClose: false
        });
        $.ajax({
            type:"post",
            url:"",
            data:money,
            dataType:"josn",
            success:function (data) {
                if(data.msgCode==100){
                    layer.closeAll();
                }
            },error:function (msg) {
                console.log(msg)
            }
        })
    }
});