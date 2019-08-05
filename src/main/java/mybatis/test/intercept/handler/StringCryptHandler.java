package mybatis.test.intercept.handler;


import mybatis.test.intercept.annotation.CryptField;
import mybatis.test.intercept.executor.CryptExecutorFactory;

/**
 * 处理 String 对象的加解密
 *
 * @author tuosen
 * @date 2019-08-01 11:40
 */
public class StringCryptHandler implements CryptHandler<String> {


    @Override
    public Object encrypt(String param, CryptField cryptField) {
        if (needCrypt(param, cryptField)) {
            String encrypt = CryptExecutorFactory.getTypeHandler(cryptField).encrypt(param);
            System.out.println(this.getClass().getSimpleName() + " :crypt: " + param + " : " + encrypt);
            return encrypt;
        }
        return param;
    }

    private boolean needCrypt(String param, CryptField cryptField) {
        return cryptField != null && param != null && param.length() != 0;
    }

    @Override
    public Object decrypt(String param, CryptField cryptField) {
        if (needCrypt(param, cryptField)) {
            String decrypt = CryptExecutorFactory.getTypeHandler(cryptField).decrypt(param);
            System.out.println(this.getClass().getSimpleName() + " :decrypt: " + param + " : " + decrypt);
            return decrypt;
        }
        return param;
    }
}

