package mybatis.test.service;

import mybatis.test.Application;
import mybatis.test.model.Student;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void findAll() {
        List<Student> all = this.studentService.findAll();
        all.stream().forEach(System.out::println);
    }

    @Test
    public void find() {
        Student student = new Student();
        student.setUsername("小红3");
        List<Student> studentList = this.studentService.find(student);
        Assert.assertNotNull(studentList);
        System.out.println(studentList);
    }

    @Test
    public void insertBatch() {
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setUsername("小红" + i);
            student.setAge(i);
            this.studentService.add(student);
        }
    }

    @Test
    public void addBatch() {
        List<Student> list = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            student.setUsername("小白" + i);
            student.setAge(10);
            list.add(student);
        }

        Collection<Student> collection = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            Student student = new Student();
            student.setUsername("小黑" + i);
            student.setAge(12);
            collection.add(student);
        }

        this.studentService.addBatch(list, collection);
    }

    @Test
    public void insertOne() {
        Student student = new Student();
        student.setUsername("小王");
        student.setAge(10);
        this.studentService.add(student);
    }

    @Test
    public void deleteOne() {
        Student student = new Student();
        student.setUsername("小王");
        this.studentService.delete(student);
    }

    @Test
    public void clear() {
        this.studentService.delete(null);
    }

    @Test
    public void update() {
        Student student = new Student();
        student.setId(5);
        student.setUsername("小红红红");
        this.studentService.update(student);
    }

}