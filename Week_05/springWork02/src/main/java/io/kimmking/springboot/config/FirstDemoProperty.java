package io.kimmking.springboot.config;


import io.kimmking.springboot.bean.Klass;
import io.kimmking.springboot.bean.School;
import io.kimmking.springboot.bean.Student;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "first")
public class FirstDemoProperty {

    private Klass klass;

    private School school;

    private Student student;

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
