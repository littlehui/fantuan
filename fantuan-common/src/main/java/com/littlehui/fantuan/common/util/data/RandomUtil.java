package com.littlehui.fantuan.common.util.data;

import java.util.Random;

/**
 * User: littlehui
 * Date: 14-12-22
 * Time: 下午3:37
 */
public class RandomUtil {
    /**
     * 产生随机字符串
     * */
    private static Random randGen = null;
    private static char[] numbersAndLetters = null;

    public static final String randomString(int length) {
        if (length < 1) {
            return null;
        }
        if (randGen == null) {
            randGen = new Random();
            numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" +
                    "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
            //numbersAndLetters = ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        }
        char [] randBuffer = new char[length];
        for (int i=0; i<randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
            //randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
        }
        return new String(randBuffer);
    }

}
