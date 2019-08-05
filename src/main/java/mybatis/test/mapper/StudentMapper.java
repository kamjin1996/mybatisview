package mybatis.test.mapper;

import mybatis.test.intercept.annotation.CryptField;
import mybatis.test.model.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertBatch(@CryptField @Param("s") String s);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> selectByName(String username);

    int deleteBySelective(Student student);

    List<Student> selectBySelective(Student student);
}