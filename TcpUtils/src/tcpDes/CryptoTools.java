package tcpDes;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by ejiang on 2017-07-19.
 * 加密解密 DES--CBC
 */
public class CryptoTools {
    private static final String keystr = "ejiangejiang";
    private final static String TRANSFORMATION = "DES/CBC/PKCS5Padding";//DES是加密方式 CBC是工作模式 PKCS5Padding是填充模式
    private final static String ALGORITHM = "DES";//DES是加密方式

    /**
     * @param keystr
     * @return
     */
    private static byte[] getKey(String keystr) throws UnsupportedEncodingException {
        if (keystr == null) throw new NullPointerException("keystr为空");
        return md5(keystr).getBytes("ASCII");
    }

    /**
     * @param keystr
     * @return
     */
    private static byte[] getCutKey(String keystr) throws UnsupportedEncodingException {
        if (keystr == null) throw new NullPointerException("keystr为空");
        return md5(keystr).substring(0, 8).getBytes("ASCII");
    }

    private static String md5(String keystr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = keystr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 16进制字节数组转换string字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 字符串--转换成16进制的数组
     * Convert hex string to byte[]
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    // 对密钥进行处理
    private static Key getRawKey(byte[] bytes) throws Exception {
        DESKeySpec dks = new DESKeySpec(bytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(dks);
    }


    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @return 加密后的字节数组，一般结合Base64编码使用
     */
    public static String encode(String data) {
        return encode(data.getBytes());
    }


    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @return 加密后的字节数组，一般结合Base64编码使用
     */
    public static String encode(byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            IvParameterSpec iv = new IvParameterSpec(getCutKey(keystr));
            cipher.init(Cipher.ENCRYPT_MODE, getRawKey(getKey(keystr)), iv);
            byte[] bytes = cipher.doFinal(data);
            return bytesToHexString(bytes);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * DES算法，解密
     * @return 解密后的字节数组
     */
    public static String decode(String message) {
        String originalString = null;
        try {
            byte[] bytes = hexStringToBytes(message);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            IvParameterSpec iv = new IvParameterSpec(getCutKey(keystr));
            cipher.init(Cipher.DECRYPT_MODE, getRawKey(getKey(keystr)), iv);
            byte[] original = cipher.doFinal(bytes);
            originalString = new String(original);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return originalString;
    }
}
