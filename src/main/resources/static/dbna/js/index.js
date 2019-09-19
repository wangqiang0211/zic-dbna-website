$(function () {
    $('#full_feature').swipeslider();

    //新闻列表
    $(".newlistUl ul li").first().addClass("on");
    $(".newlistUl ul li").click(function () {
        var this_id=$(this).find("span").html();
        window.location.href="/news-find?id="+this_id;
    });

    //产品展示
    // $(".caseUl ul li").click(function () {
    //     var this_id=$(this).find("span").valueOf();
    //     window.location.href="assembleNew.html?id="+this_id;
    // })
});