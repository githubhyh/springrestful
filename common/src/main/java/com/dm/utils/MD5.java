package com.dm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class MD5 {
    private static final Logger logger = LoggerFactory.getLogger(MD5.class);

    private static final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 生成随机盐
     * */
    private static String salt() {
        StringBuilder builder = new StringBuilder(16);
        Random random = new Random();
        for (int i = 0; i < builder.capacity(); i++) {
            builder.append(hex[random.nextInt(16)]);
        }
        return builder.toString();
    }

    private static String MD5ByShift(byte[] bytes) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            byte temp = bytes[i];
            buffer.append(hex[temp >>> 4 & 0xf]);
            buffer.append(hex[temp & 0xf]);
        }
        return buffer.toString();
    }

    private static String MD5ByFormatString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02X", b));
        }
        return builder.toString();
    }
}
