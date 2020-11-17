package io.kimmking.springboot.bean;

import java.util.List;


public class Klass { 
    
    List<Student> students;

    @Override
    public String toString() {
        return "Klass{" +
                "students=" + students +
                '}';
    }
}
