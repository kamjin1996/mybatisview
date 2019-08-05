package mybatis.test.intercept.handler;


import mybatis.test.intercept.annotation.CryptField;

/**
 * 空的加解密执行者，避免过多空指针判断
 *
 * @author tuosen
 * @date 2019-07-30 11:40
 */
public class EmptyCryptHandler implements CryptHandler<Object> {

    @Override
    public Object encrypt(Object param, CryptField cryptField) {
        System.out.println(this.getClass().getSimpleName() + ":crypt: " + param);
        return param;
    }

    @Override
    public Object decrypt(Object param, CryptField cryptField) {
        System.out.println(this.getClass().getSimpleName() + ":decrypt: " + param);
        return param;
    }
}
