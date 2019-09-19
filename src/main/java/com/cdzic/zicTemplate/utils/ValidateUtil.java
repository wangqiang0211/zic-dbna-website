package com.cdzic.zicTemplate.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具
 */
public class ValidateUtil {

    /**
     * 验证字符串是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
