package com.cdzic.zicTemplate.utils;

import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OauthUtil {
    public static final Logger logger = LogManager.getLogger();

    /**
     * {
     "access_token": "OezXcEiiBSKSxW0eoylIeAsR0GmYd1awCffdHgb4fhS_KKf2CotGj2cBNUKQQvj-G0ZWEE5-uBjBz941EOPqDQy5sS_GCs2z40dnvU99Y5AI1bw2uqN--2jXoBLIM5d6L9RImvm8Vg8cBAiLpWA8Vw",
     "expires_in": 7200,
     "refresh_token": "OezXcEiiBSKSxW0eoylIeAsR0GmYd1awCffdHgb4fhS_KKf2CotGj2cBNUKQQvj-G0ZWEE5-uBjBz941EOPqDQy5sS_GCs2z40dnvU99Y5CZPAwZksiuz_6x_TfkLoXLU7kdKM2232WDXB3Msuzq1A",
     "openid": "oLVPpjqs9BhvzwPj5A-vTYAX3GLc",
     "scope": "snsapi_userinfo,"
     }
     * @param appSecret
     * @param code
     * @param appId
     * @return
     */
    public static JSONObject getSimpleOauthInfo(String appSecret, String code, String appId) {
        JSONObject json = null;
        try {
            json = MessageUtil.httpsRequest("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId
                    + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code", "GET", null);
            if (null == json) {
                json = new JSONObject();
                json.put("openid", "");
            }
        } catch (Exception e) {
            logger.error("Oauth2.0授权失败:" + e.getMessage());
            json = new JSONObject();
            json.put("openid", "");
        }
        logger.debug("网页授权access_token json={}"+json.toString());
        return json;
    }

    /**
     * {
     "subscribe": 1,
     "openid": "oLVPpjqs2BhvzwPj5A-vTYAX4GLc",
     "nickname": "方倍",
     "sex": 1,
     "language": "zh_CN",
     "city": "深圳",
     "province": "广东",
     "country": "中国",
     "headimgurl": "http://wx.qlogo.cn/mmopen/JcDicrZBlREhnNXZRudod9PmibRkIs5K2f1tUQ7lFjC63pYHaXGxNDgMzjGDEuvzYZbFOqtUXaxSdoZG6iane5ko9H30krIbzGv/0",
     "subscribe_time": 1386160805
     }
     * @param access_token
     * @param openid
     * @return
     */
    public static JSONObject getBaseOauthInfo(String access_token, String openid) {
        JSONObject json = null;
        try {
            json = MessageUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openid, "GET", null);
            if (null == json) {
                json = new JSONObject();
                json.put("openid", "");
            }
        } catch (Exception e) {
            logger.error("Oauth2.0授权失败:" + e.getMessage());
            json = new JSONObject();
            json.put("openid", "");
        }
        logger.debug("网页授权access_token json={}"+json.toString());
        return json;
    }
}
