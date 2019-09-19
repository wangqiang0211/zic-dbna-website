package com.cdzic.zicTemplate.common;

public class WechatConstant {
    /**
     * 微信AppID.
     */
    public static final String APP_ID = "appid";

    /**
     * 微信Appsecret.
     */
    public static final String APP_SECRET = "appsecret";

    /**
     * 微信唯一票据ACCESS_TOKEN.
     */
    public static final String ACCESS_TOKEN = "accessToken";

    /**
     * 微信加密签名.
     */
    public static final String SIGNATURE = "signature";

    /**
     * 时间戳.
     */
    public static final String TIMESTAMP = "timestamp";

    /**
     * 随机数.
     */
    public static final String NONCE = "nonce";

    /**
     * 随机字符串.
     */
    public static final String ECHOSTR = "echostr";

    /**
     * Token.
     */
    public static final String TOKEN = "token";

    /**
     * 微信默认换行符.
     */
    public static final String NEWLINE_WEIXIN = "\n";


    //被动回复用户消息类型
    /**
     * 返回消息类型：文本.
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 返回消息类型：音乐.
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 返回消息类型：图文.
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";


    //用户发送消息类型
    /**
     * 请求消息类型：文本.
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型：图片.
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";


    /**
     * 请求消息类型：语音消息.
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     *请求消息类型：视频消息
     */
    public static final String REQ_MESSAGE_TYPE_VODEO = "video";

    /**
     *请求消息类型：小视频消息
     */
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";

    /**
     * 请求消息类型：地理位置消息.
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**
     * 请求消息类型：链接消息.
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";


    //事件推送

    /**
     * 请求消息类型：推送.
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 事件类型：subscribe(订阅).
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：unsubscribe(取消订阅).
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：CLICK(自定义菜单点击事件).
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";

    /**
     * 事件类型：VIEW(自定义菜单点击事件).
     */
    public static final String EVENT_TYPE_VIEW = "VIEW";
    /**
     * 事件类型：模板消息发送后推送事件
     */
    public static final String EVENT_TYPE_TEMPLATE = "TEMPLATESENDJOBFINISH";
    /**
     * 事件类型：扫描带参数二维码事件
     */
    public static final String EVENT_TYPE_SCAN = "SCAN";
}
