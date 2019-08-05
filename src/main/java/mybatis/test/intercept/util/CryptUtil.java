package mybatis.test.intercept.util;

import mybatis.test.intercept.config.ConfigInit;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

/**
 * 加解密工具类
 *
 * @author tuosen
 * @date 2019-07-30 12:49
 */
public final class CryptUtil {

    private static final String CRYPT_WAY = "AES";
    private static final String ALGORITHM_MODE_COMPLEMENT = "AES/ECB/PKCS5Padding";//算法/模式/补码方式
    private static final String BYTE_CONTROL = "utf-8";
    private static final Integer STANDARD_SUPPORT = 192;//加密标准支持：128/192/256 对应key长度分别为16/24/32
    private static final Integer KEY_LENGTH = 24;
    private static final String KEY_NOT_BE_NULL = "KEY不能为空";
    private static final String KEY_LENGTH_NOT_SUPPORT = "KEY长度不符合";

    private static String secretKey;
    private static Boolean cryptEnable;

    private static final Set<Class> IGNORE_CLASS = new HashSet<>();

    static {
        //initIgnoreClass
        IGNORE_CLASS.add(Byte.class);
        IGNORE_CLASS.add(Short.class);
        IGNORE_CLASS.add(Integer.class);
        IGNORE_CLASS.add(Long.class);
        IGNORE_CLASS.add(Float.class);
        IGNORE_CLASS.add(Double.class);
        IGNORE_CLASS.add(Boolean.class);
        IGNORE_CLASS.add(Character.class);

        //initProp
        secretKey = ConfigInit.DbCrypt_SecretKey;
        cryptEnable = ConfigInit.DbCrypt_Enable;
    }

    public static boolean inIgnoreClass(Class cls) {
        return IGNORE_CLASS.contains(cls);
    }

    public static String encrypt(String sSrc) {
        return encrypt(sSrc, cryptEnable);
    }

    public static String decrypt(String sSrc) {
        return decrypt(sSrc, cryptEnable);
    }

    /**
     * 加密
     *
     * @param sSrc
     * @return
     * @throws Exception
     */
    private static String encrypt(String sSrc, Boolean enable) {
        String sKey = secretKey;
        checkKey(sKey);

        try {
            KeyGenerator kgen = KeyGenerator.getInstance(CRYPT_WAY);
            kgen.init(STANDARD_SUPPORT, new SecureRandom(sKey.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec skeySpec = new SecretKeySpec(enCodeFormat, CRYPT_WAY);

            Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_COMPLEMENT);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes(BYTE_CONTROL));

            //此处使用BASE64做转码功能，能起到2次加密的作用。
            return new Base64().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     *
     * @param sSrc
     * @param enable
     * @return
     */
    private static String decrypt(String sSrc, Boolean enable) {
        String sKey = secretKey;
        checkKey(sKey);

        try {
            KeyGenerator kgen = KeyGenerator.getInstance(CRYPT_WAY);
            kgen.init(STANDARD_SUPPORT, new SecureRandom(sKey.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec skeySpec = new SecretKeySpec(enCodeFormat, CRYPT_WAY);

            Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_COMPLEMENT);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            //先用base64解密
            byte[] encrypted1 = new Base64().decode(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, BYTE_CONTROL);
                return originalString;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 检查SecretKey
     */
    private static void checkKey(String sKey) {
        if (sKey == null) {
            throw new RuntimeException(KEY_NOT_BE_NULL);
        }
        if (sKey.length() != KEY_LENGTH) {
            throw new RuntimeException(KEY_LENGTH_NOT_SUPPORT);
        }
    }

}

