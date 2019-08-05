package mybatis.test.intercept.resolver;

/**
 * 方法加密处理者
 *
 * @author tuosen
 * @date 2019-03-30 13:36
 */
public interface MethodEncryptResolver {

    Object processEncrypt(Object param);
}