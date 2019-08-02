package mybatis.test.service;

import mybatis.test.model.Student;

import java.util.Collection;
import java.util.List;

/**
 * @auther: tuosen
 * @date: 14:58 2019-08-01
 * @description: 学生服务
 */
public interface StudentService {

    Student findById(Integer id);

    List<Student> find(Student student);

    void add(Student student);

    void update(Student student);

    void delete(Student student);

    List<Student> findAll();

    void addBatch(List<Student> list, Collection<Student> collection);
}
