package mybatis.test.intercept2.resolver;

/**
 * 表示方法不需要加密
 *
 * @author tuosen
 * @date 2019-08-01 13:12
 */
public class EmptyMethodEncryptResolver implements MethodEncryptResolver {

    @Override
    public Object processEncrypt(Object param) {
        System.out.println("empty");
        return param;
    }

}