package com.cdzic.zicTemplate.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 * 加密工具类，包含MD5,BASE64,SHA,CRC32
 */
public class CryptoUtils {

    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * MD5加密
     *  32位
     * @param bytes an array of byte.
     * @return a {@link String} object.
     */
    public static String encodeMD5(final byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            byte[] digest = md.digest();
            return byteArrayToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * MD5加密，默认UTF-8
     *
     * @param str a {@link String} object.
     * @return a {@link String} object.
     */
    public static String encodeMD5(final String str) {
        return encodeMD5(str, DEFAULT_CHARSET);
    }


    /**
     * MD5 解密  一般运行两次解密  CryptoUtils.convertMD5(CryptoUtils.convertMD5(s))
     * @param inStr
     * @return
     */
    public static String convertMD5(String inStr){
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

    /**
     * MD5加密
     *
     * @param str     a {@link String} object.
     * @param charset a {@link String} object.
     * @return a {@link String} object.
     */
    public static String encodeMD5(final String str, final String charset) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes(charset);
            return encodeMD5(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * BASE64加密
     *
     * @param bytes an array of byte.
     * @return a {@link String} object.
     */
    public static String encodeBASE64(final byte[] bytes) {
        return new String(Base64.encodeBase64String(bytes));
    }

    /**
     * BASE64加密
     *
     * @param str     a {@link String} object.
     * @param charset a {@link String} object.
     * @return a {@link String} object.
     */
    public static String encodeBASE64(final String str, String charset) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes(charset);
            return encodeBASE64(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * BASE64加密,默认UTF-8
     *
     * @param str a {@link String} object.
     * @return a {@link String} object.
     */
    public static String encodeBASE64(final String str) {
        return encodeBASE64(str, DEFAULT_CHARSET);
    }

    /**
     * BASE64解密,默认UTF-8
     *
     * @param str a {@link String} object.
     * @return a {@link String} object.
     */
    public static String decodeBASE64(String str) {
        return decodeBASE64(str, DEFAULT_CHARSET);
    }

    /**
     * BASE64解密
     *
     * @param str     a {@link String} object.
     * @param charset 字符编码
     * @return a {@link String} object.
     */
    public static String decodeBASE64(String str, String charset) {
        try {
            byte[] bytes = str.getBytes(charset);
            return new String(Base64.decodeBase64(bytes));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * CRC32字节校验
     *
     * @param bytes an array of byte.
     * @return a {@link String} object.
     */
    public static String crc32(byte[] bytes) {
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);
        return Long.toHexString(crc32.getValue());
    }

    /**
     * CRC32字符串校验
     *
     * @param str     a {@link String} object.
     * @param charset a {@link String} object.
     * @return a {@link String} object.
     */
    public static String crc32(final String str, String charset) {
        try {
            byte[] bytes = str.getBytes(charset);
            return crc32(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * CRC32字符串校验,默认UTF-8编码读取
     *
     * @param str a {@link String} object.
     * @return a {@link String} object.
     */
    public static String crc32(final String str) {
        return crc32(str, DEFAULT_CHARSET);
    }

    /**
     * CRC32流校验
     *
     * @param input a {@link InputStream} object.
     * @return a {@link String} object.
     */
    public static String crc32(InputStream input) {
        CRC32 crc32 = new CRC32();
        CheckedInputStream checkInputStream = null;
        int test = 0;
        try {
            checkInputStream = new CheckedInputStream(input, crc32);
            do {
                test = checkInputStream.read();
            } while (test != -1);
            return Long.toHexString(crc32.getValue());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * CRC32文件唯一校验
     *
     * @param file a {@link File} object.
     * @return a {@link String} object.
     */
    public static String crc32(File file) {
        InputStream input = null;
        try {
            input = new BufferedInputStream(new FileInputStream(file));
            return crc32(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * CRC32文件唯一校验
     *
     * @param url a {@link URL} object.
     * @return a {@link String} object.
     */
    public static String crc32(URL url) {
        InputStream input = null;
        try {
            input = url.openStream();
            return crc32(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    public static String byteArrayToHex(byte[] byteArray) {
        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }
}
