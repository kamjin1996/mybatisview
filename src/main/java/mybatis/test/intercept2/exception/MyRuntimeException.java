package mybatis.test.intercept2.exception;

/**
 * 自定义运行时异常，避免太多异常链
 *
 * @author tuosen
 * @date 2019-07-31 09:48
 */
public class MyRuntimeException extends RuntimeException {

    public MyRuntimeException(){}

    public MyRuntimeException(String message) {
        super(message);
    }

}
