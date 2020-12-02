package com.test.demo;

import java.io.*;

public class GenerateInsertSql {


    public static void main(String[] args) throws IOException {

        String path="/Library/WorkSpace/test.sql";
        File file=new File(path);
        OutputStream out=new FileOutputStream(file);
        for(int i=0;i<1000000;i++){
            out.write(getSql(i).getBytes());
        }
        out.close();
    }

    private static String getSql(int i){
        return "insert into USER_INFO(user_name) values("+i+");\n";
    }
}
