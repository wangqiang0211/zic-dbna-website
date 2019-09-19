package com.cdzic.zicTemplate.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class CommonUtil {

    /**
     * 创建随机字符串
     * @param strLength
     * @return
     */
    public static String createNoncestr(int strLength) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i < strLength; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    /**
     * 获得一个去掉"-"的UUID 32bits 例: "863a74e5c9a54395862179721918d7a7"
     *
     * @return String UUID
     */
    public static String get32UUID() {
        String s = UUID.randomUUID().toString();
        // 去掉"-"符号操作  原uuid ；类似 3e0ffd52-af76-4ccf-888e-53b13fdf17d5，中间有"-" 符号
        String uuId=s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
                + s.substring(19, 23) + s.substring(24);
        // 已经去除"-" 符号的uuid  3e0ffd52af764ccf888e53b13fdf17d5
        return uuId;
    }

    /**
     * 获得指定数目的UUID
     * 例:{"e7d83456191d4f8fa37c722df3af4017","196a0e99817f46679fb82d52d4ec39a3"}
     *
     * @param number
     *            int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = get32UUID();
        }
        return ss;
    }

    /**
     * 获取时间戳
     * @return
     */
    public static long getTimestamp(){
        return (new Date()).getTime();
    }

    public static String getFormatDateTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }



}
