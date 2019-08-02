package mybatis.test.intercept2.handler;


import mybatis.test.intercept2.annotation.CryptField;

/**
 * 加解密处理抽象类
 *
 * @author tuosen
 * @date 2019-07-31 11:40
 */
public interface CryptHandler<T> {

    Object encrypt(T param, CryptField cryptField);

    Object decrypt(T param, CryptField cryptField);
}
