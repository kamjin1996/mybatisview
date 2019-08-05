package mybatis.test.intercept.executor;

/**
 * 加解密执行者，可能是加密手机号码，可能是加密姓名等
 *
 * @author tuosen
 * @date 2019-08-01 13:20
 */
public interface CryptExecutor {

    String encrypt(String str);

    String decrypt(String str);
}
