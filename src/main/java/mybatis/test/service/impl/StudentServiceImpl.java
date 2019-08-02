package mybatis.test.service.impl;

import mybatis.test.mapper.StudentMapper;
import mybatis.test.model.Student;
import mybatis.test.service.StudentService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @auther: tuosen
 * @date: 14:59 2019-08-01
 * @description: 学生服务实现类
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student findById(Integer id) {
        return this.studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Student> find(Student student) {
        return this.studentMapper.selectBySelective(student);
    }

    @Override
    public void add(Student student) {
        this.studentMapper.insert(student);
    }

    @Override
    public void update(Student student) {
        this.studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public void delete(Student student) {
        this.studentMapper.deleteBySelective(student);
    }

    @Override
    public List<Student> findAll() {
        return this.studentMapper.selectBySelective(null);
    }

    @Override
    public void addBatch(List<Student> list, Collection<Student> collection) {
        List<Student> studentList = (List<Student>) collection;
        this.studentMapper.insertBatch(studentList.get(0).getUsername());
    }

}
