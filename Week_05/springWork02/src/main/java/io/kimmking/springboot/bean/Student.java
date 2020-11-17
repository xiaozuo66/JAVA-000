package io.kimmking.springboot.bean;


import lombok.Data;

import java.io.Serializable;

//不加该注解，无法转换Property
@Data
public class Student implements Serializable {
    
    private int id;
    private String name;
    
    public void init(){
        System.out.println("hello...........");
    }
    
    public Student create(){
        return new Student();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
