package com.littlehui.fantuan.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Stack;

/**
 * EncryptUtil
 *
 * @author longlin(longlin@cyou-inc.com)
 * @date 2013-10-29
 * @since V1.0
 */
public class EncryptUtil {
    public static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
    public static String SCALE_DIGIT = "abcdefghijklmnopqrstuvwxyzABCDEF";
    public static char[] SCALE_DIGIT_CHARS = SCALE_DIGIT.toCharArray();
    public static int SCALE_NUM = 32;

    /**
     * md5加密
     *
     * @param bytes
     * @return
     */
    public static String md5(byte[] bytes) {
        //16进制字符
        try {
            byte[] strTemp = bytes;
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(strTemp);
            byte[] digest = messageDigest.digest();
            int j = digest.length;
            char[] str = new char[j * 2];
            int k = 0;
            //移位 输出字符串
            for (int i = 0; i < j; i++) {
                byte byte0 = digest[i];
                str[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf];
                str[k++] = HEX_DIGITS[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * 加密时间戳，即将10进制转为32进制
     *
     * @param time
     * @return
     */
    public static String encodeTimestamp(long time) {
        StringBuffer str = new StringBuffer("");
        Stack<Character> s = new Stack<Character>();
        while (time != 0) {
            s.push(SCALE_DIGIT_CHARS[(int) (time % SCALE_NUM)]);
            time /= SCALE_NUM;
        }
        while (!s.isEmpty()) {
            str.append(s.pop());
        }
        return str.toString();
    }

    /**
     * 解密时间戳，即将32进制转为10进制
     *
     * @param encryptedTime
     * @return
     */
    public static long decodeTimestamp(String encryptedTime) {
        char[] chars = encryptedTime.toCharArray();
        int len = chars.length;

        long n = 0;
        for (int i = len - 1; i >= 0; i--) {
            n += SCALE_DIGIT.indexOf(chars[i]) * Math.pow(SCALE_NUM, len - i - 1);
        }
        return n;
    }
}
