package mybatis.test.model;

import lombok.Data;
import mybatis.test.intercept.annotation.CryptField;
import mybatis.test.intercept.executor.CryptType;

@Data
public class Student {
    private Integer id;

    @CryptField(CryptType.SPECIAL)
    private String username;

    private Integer age;

}