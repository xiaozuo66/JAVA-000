package io.kimmking.springboot.bean;


import java.io.Serializable;


public class Student implements Serializable {
    
    private int id;
    private String name;
    
    public void init(){
        System.out.println("hello...........");
    }
    
    public Student create(){
        return new Student();
    }
}
