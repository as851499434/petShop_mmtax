package com.mmtax.common.utils;

import java.io.*;

public class ByteUtil {
    private static final String[] CH = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    private static final char[] ac = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 转换16进制字符串为byte[]
     *
     * @param ss
     * @return
     */
    public static byte[] convertHexString(String ss) {
        byte digest[] = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }
        return digest;
    }

    /**
     * 转换byte[]为16进制字符串
     *
     * @param b
     * @return
     */
    public static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }
        return hexString.toString();
    }

    public static byte[] getRandomKey(int byteLen) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteLen; i++) {
            sb.append(CH[((int) (15.0D * Math.random()))]);
        }
        return hexStringToByte(sb.toString());
    }

    public static byte[] addBytes(byte[][] src) {
        int length = 0;
        for (int i = 0; i < src.length; i++) {
            if (src[i] != null)
                length += src[i].length;
        }
        byte[] score = new byte[length];
        int index = 0;
        for (int i = 0; i < src.length; i++) {
            if (src[i] != null) {
                System.arraycopy(src[i], 0, score, index, src[i].length);
                index += src[i].length;
            }
        }
        src = (byte[][]) null;
        return score;
    }

    public static byte[] getMidBytes(byte[] src, int startIndex, int length) {
        byte[] b = new byte[length];
        System.arraycopy(src, startIndex, b, 0, length);
        return b;
    }

    public static byte[] getBytes(byte[] data, int len, byte flag) {
        byte[] b = new byte[len];
        for (int i = 0; i < b.length; i++) {
            if (i < len - data.length)
                b[i] = flag;
            else {
                b[i] = data[(i - b.length + data.length)];
            }
        }
        data = (byte[]) null;
        return b;
    }

    public static byte[] getBytes(byte[] data, byte old_flag, byte new_flag) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != old_flag)
                break;
            data[i] = new_flag;
        }

        return data;
    }

    public static byte[] hexStringToByte(String hex) {
        byte[] data = hex.getBytes();
        int i = data.length;
        byte[] result = new byte[i / 2];
        for (int j = 0; j < i; j += 2) {
            String s1 = new String(data, j, 2);
            result[(j / 2)] = ((byte) Integer.parseInt(s1, 16));
        }
        data = (byte[]) null;
        return result;
    }

    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);

        for (int i = 0; i < bArray.length; i++) {
            String sTemp = byteToHexString(bArray[i]);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    private static String byteToHexString(byte b) {
        char[] ch = new char[2];
        ch[0] = ac[(b >>> 4 & 0xF)];
        ch[1] = ac[(b & 0xF)];
        return new String(ch);
    }

    public static final Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = new ObjectInputStream(in);
        Object o = oi.readObject();
        oi.close();
        return o;
    }

    public static final byte[] objectToBytes(Serializable s) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream ot = new ObjectOutputStream(out);
        ot.writeObject(s);
        ot.flush();
        ot.close();
        return out.toByteArray();
    }

    public static final String objectToHexString(Serializable s) throws IOException {
        return bytesToHexString(objectToBytes(s));
    }

    public static final Object hexStringToObject(String hex) throws IOException, ClassNotFoundException {
        return bytesToObject(hexStringToByte(hex));
    }

    public static String bcd2Str(byte[] bytes) {
        StringBuffer temp = new StringBuffer(bytes.length * 2);

        for (int i = 0; i < bytes.length; i++) {
            temp.append((byte) ((bytes[i] & 0xF0) >>> 4));
            temp.append((byte) (bytes[i] & 0xF));
        }
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
    }

    public static byte[] str2Bcd(String asc) {
        int len = asc.length();
        int mod = len % 2;

        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }

        byte[] abt = new byte[len];
        if (len >= 2) {
            len /= 2;
        }

        byte[] bbt = new byte[len];
        abt = asc.getBytes();

        for (int p = 0; p < asc.length() / 2; p++) {
            int j;
            if ((abt[(2 * p)] >= 48) && (abt[(2 * p)] <= 57)) {
                j = abt[(2 * p)] - 48;
            } else {
                if ((abt[(2 * p)] >= 97) && (abt[(2 * p)] <= 122))
                    j = abt[(2 * p)] - 97 + 10;
                else
                    j = abt[(2 * p)] - 65 + 10;
            }
            int k;
            if ((abt[(2 * p + 1)] >= 48) && (abt[(2 * p + 1)] <= 57)) {
                k = abt[(2 * p + 1)] - 48;
            } else {
                if ((abt[(2 * p + 1)] >= 97) && (abt[(2 * p + 1)] <= 122))
                    k = abt[(2 * p + 1)] - 97 + 10;
                else {
                    k = abt[(2 * p + 1)] - 65 + 10;
                }
            }
            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }

    public static byte[] getMultiples(byte[] src, int multiples, byte b) {
        int remnant = src.length % multiples;
        if (remnant == 0) {
            return src;
        }
        int quotient = src.length / multiples;
        byte[] newByte = new byte[(quotient + 1) * multiples];
        for (int i = 0; i < newByte.length; i++) {
            if (i < src.length)
                newByte[i] = src[i];
            else {
                newByte[i] = b;
            }
        }
        return newByte;
    }
}
