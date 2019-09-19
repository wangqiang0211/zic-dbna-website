package com.cdzic.zicTemplate.utils.ali;

import com.cdzic.zicTemplate.domain.myexception.MyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Mac;
import java.text.SimpleDateFormat;
import java.util.*;

public class SMSUrl {
    private static final Logger logger = LogManager.getLogger(SMSUrl.class);

    /**
     * 获取发送短信url
     * @param accessKeyId
     * @param accessSecret
     * @param phoneNumbers 手机号码 多个手机号码以逗号隔开
     * @param smsSignName 短信签名
     * @param smsTempletId 短信模板id
     * @param validateCode 需要发送的验证码
     * @return
     * @throws MyException
     */
    public static String sendSMSUrl(String accessKeyId, String accessSecret, String phoneNumbers, String smsSignName, String smsTempletId, String validateCode) throws MyException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sf.setTimeZone(new SimpleTimeZone(0, "GMT"));
        logger.info("时间解析="+sf.format(new Date()));
        Map<String, String> paras = new HashMap<>();
        // 1. 系统参数
        paras.put("SignatureMethod", "HMAC-SHA1");
        paras.put("SignatureNonce", UUID.randomUUID().toString());
        paras.put("AccessKeyId", accessKeyId);
        paras.put("SignatureVersion", "1.0");
        paras.put("Timestamp", sf.format(new Date()));
        paras.put("Format", "JSON");

        // 2. 业务API参数
        paras.put("Action", "SendSms");
        paras.put("Version", "2017-05-25");
        paras.put("RegionId", "cn-hangzhou");
        paras.put("PhoneNumbers", phoneNumbers);
        paras.put("SignName", smsSignName);
        paras.put("TemplateParam", "{\"code\":\"" + validateCode + "\"}");
        paras.put("TemplateCode", smsTempletId);
//        paras.put("OutId", "123");

        // 3. 去除签名关键字Key
        if (paras.containsKey("Signature"))
            paras.remove("Signature");

        // 4. 参数KEY排序
        TreeMap<String, String> sortParas = new TreeMap<>();
        sortParas.putAll(paras);

        // 5. 构造待签名的字符串
        Iterator<String> it = sortParas.keySet().iterator();
        StringBuilder sortQueryStringTmp = new StringBuilder();
        String signature;
        try {
            while (it.hasNext()) {
                String key = it.next();
                sortQueryStringTmp.append("&").append(specialUrlEncode(key)).append("=").append(specialUrlEncode(paras.get(key)));
            }
            String sortedQueryString = sortQueryStringTmp.substring(1);// 去除第一个多余的&符号
            StringBuilder stringToSign = new StringBuilder();
            stringToSign.append("GET").append("&");
            stringToSign.append(specialUrlEncode("/")).append("&");
            stringToSign.append(specialUrlEncode(sortedQueryString));
            String sign = sign(accessSecret + "&", stringToSign.toString());
            // 6. 签名最后也要做特殊URL编码
             signature = specialUrlEncode(sign);
        } catch (Exception e) {
            logger.error("get send sms url error"+e);
            e.printStackTrace();
            throw new MyException(e.getMessage());
        }
        return "http://dysmsapi.aliyuncs.com/?Signature=" + signature + sortQueryStringTmp;
    }

    public static String specialUrlEncode(String value) throws Exception {
        return java.net.URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

    public static String sign(String accessSecret, String stringToSign) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new javax.crypto.spec.SecretKeySpec(accessSecret.getBytes("UTF-8"), "HmacSHA1"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        return new sun.misc.BASE64Encoder().encode(signData);
    }
}
