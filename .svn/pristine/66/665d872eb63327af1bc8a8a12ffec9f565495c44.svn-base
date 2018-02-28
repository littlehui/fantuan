package com.littlehui.fantuan.common.util;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;


/**
 * 产生由时间信息、随机数和校验信息组成的的UniqueId, 并提供提取时间信息的方法和校验的方法.
 *
 * @author Zhikuan Lin
 *         Modify by Linliangyi（林良益）
 * @version 1.0.0
 * @since 1.0.0
 */
public class UUID {
    /*
     * 记录系统开始时刻nanoTime()和System.currentTimeMillis()之差, 之后便能由System.nanoTime()直接计算出当前时间.
     */
    private static final long START_MICRO_TIME = System.currentTimeMillis() * 1000l;
    private static final long START_NANO_TIME = System.nanoTime();

    /**
     * 返回当前系统从1970-01-01 00:00:00 UTC起的微秒数(microseconds).
     *
     * @return
     */
    private static long microseconds() {
        return START_MICRO_TIME + (System.nanoTime() - START_NANO_TIME) / 1000l;
    }


    private static final Random random = new Random();

    /**
     * 产生并返回一个值范围在0至range之间的随机数.
     * <p/>
     * {@link Random#nextInt(int)}方法本身是线程安全的, 所以这里没有做额外的同步
     *
     * @param range 表示范围上限的正整数
     * @return
     */
    private static int random(int range) {
        return random.nextInt(range);
    }


    private static final char[] CHARS = new char[]{
            '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y',
            'z'
    };

    /**
     * 产生字符串指定范围字符的校验值.
     *
     * @param s
     * @param start 开始下标(包含在范围内)
     * @param end   结束下标(不包含在范围内)
     * @return
     */
    private static char checksum(String s, int start, int end) {
        return checksum(s.toCharArray(), start, end);
    }

    /**
     * 产生字符数组指定范围字符的校验值.
     *
     * @param
     * @param start 开始下标(包含在范围内)
     * @param end   结束下标(不包含在范围内)
     * @return
     */
    private static char checksum(char[] buff, int start, int end) {
        int c = 17;
        for (int i = start; i < end; i++) {
            c = 31 * c + (int) buff[i];
        }
        return CHARS[Math.abs(c % CHARS.length)];
    }


    /**
     * 将i填充至目标数组指定位置, 不足位补0, 超出位忽略.
     *
     * @param buff  目标数组
     * @param start 开始下标(包含在范围内)
     * @param end   结束下标(不包含在范围内)
     * @param i
     */
    private static void fill(char[] buff, int start, int end, long i) {
        if (i < 0) {
            throw new IllegalArgumentException("i must >=0");
        }

        Arrays.fill(buff, start, end, CHARS[0]);

        int pos = end - 1;
        long radix = CHARS.length;

        while (i >= radix && pos >= start) {
            buff[pos--] = CHARS[(int) (i % radix)];
            i = i / radix;
        }

        if (pos >= start) {
            buff[pos] = CHARS[(int) i];
        }
    }

    /**
     * 将i填充至目标数组指定位置, 不足位补0, 超出位忽略.
     *
     * @param buff  目标数组
     * @param start 开始下标(包含在范围内)
     * @param end   结束下标(不包含在范围内)
     * @param i
     */
    private static void fill(char[] buff, int start, int end, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("i must >=0");
        }

        Arrays.fill(buff, start, end, CHARS[0]);

        int pos = end - 1;
        int radix = CHARS.length;

        while (i >= radix && pos >= start) {
            buff[pos--] = CHARS[i % radix];
            i = i / radix;
        }

        if (pos >= start) {
            buff[pos] = CHARS[i];
        }
    }


    private static final int RANDOM_RANGE = (int) Math.pow(CHARS.length, 3);

    /**
     * 产生一个16字符的UID, 1char version + 11char microseconds + 3char random + 1char checksum
     * <p/>
     * 该UID第0字符为版本标识,
     * 第1-11共11个字符为36进制表示的从1970-01-01 00:00:00 UTC起的微秒数(microseconds),
     * 第12-14共3个字符为36进制表示的随机数,
     * 第15字符为第1-14字符的校验值
     *
     * @return 产生的UID
     */
    public static String generate() {
        char[] buff = new char[16];

        buff[0] = 'a'; // version
        fill(buff, 1, 12, microseconds()); // microseconds
        fill(buff, 12, 15, random(RANDOM_RANGE)); // random
        buff[15] = checksum(buff, 1, 15); //checksum

        return String.valueOf(buff);
    }

    /**
     * 从指定的时间生成1个UID.
     *
     * @param milliseconds 从1970-01-01 00:00:00 UTC起的毫秒数(milliseconds)
     * @return 产生的UID
     */
    public static String generate(long milliseconds) {
        char[] buff = new char[16];

        buff[0] = 'a'; // version
        fill(buff, 1, 12, milliseconds * 1000l); // microseconds
        fill(buff, 12, 15, random(RANDOM_RANGE)); // random
        buff[15] = checksum(buff, 1, 15); //checksum

        return String.valueOf(buff);
    }


    /**
     * 从传入的UniqueId提取时间, 返回从1970-01-01 00:00:00 UTC起的毫秒数(milliseconds).
     * <p/>
     * 可以直接由返回值构造新的{@link Date}.
     *
     * @param uniqueId
     * @return
     */
    public static long extractTime(String uniqueId) {
        if (uniqueId.length() != 16 || 'a' != uniqueId.charAt(0)) {
            throw new IllegalArgumentException("invalid uniqueId: " + uniqueId);
        }
        return Long.parseLong(uniqueId.substring(1, 12), 36) / 1000l;
    }

    /**
     * 验证传入的UniqueId是否有效.
     *
     * @param uniqueId
     * @return true如果UniqueId符合验证规则
     */
    public static boolean verify(String uniqueId) {
        if (uniqueId.length() != 16) {
            return false;
        } else if ('a' != uniqueId.charAt(0)) {
            return false;
        }
        return uniqueId.charAt(15) == checksum(uniqueId, 1, 15);
    }

    public static void main(String args[]) {
        int count = 5;
        String[] ids = new String[count];
        for (int i = 0; i < count; i++) {
            ids[i] = generate();
            if (verify(ids[i])) {
                System.out.print(ids[i]);
                System.out.print(": ");
                System.out.println(new Date(extractTime(ids[i])));
            } else {
                System.err.println(ids[i]);
            }
        }
    }
}
