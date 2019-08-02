package mybatis.test.intercept2.resolver;

/**
 * 表示方法不需要解密
 *
 * @author tuosen
 * @date 2019-08-01 13:12
 */
public class EmptyMethodDecryptResolver implements MethodDecryptResolver {

    @Override
    public Object processDecrypt(Object param) {
        System.out.println("empty");
        return param;
    }

}