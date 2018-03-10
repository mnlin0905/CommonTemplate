package com.acchain.community.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created on 2018/1/18
 * function : 特殊数据加密
 *
 * @author ACChain
 */

public final class EncodeUtils {
    public static final String SHA_1 = "SHA-1";
    public static final String MD5 = "MD5";
    public static final String SHA_256 = "SHA-256";
    public static final String SHA_384 = "SHA-384";

    public static String encode(String source, String type) {
        MessageDigest md;
        String strDes;
        byte[] bt = source.getBytes();
        try {
            md = MessageDigest.getInstance(type);
            md.update(bt);
            strDes = bytes2Hex(md.digest());
            return strDes;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("签名失败！");
            return null;
        }
    }

    private static String bytes2Hex(byte[] bts) {
        StringBuilder builder=new StringBuilder("");
        String tmp;
        for (byte bt : bts) {
            tmp = (Integer.toHexString(bt & 0xFF));
            if (tmp.length() == 1) {
                builder.append("0");
            }
            builder.append(tmp);
        }

        return builder.toString();
    }
}
