package com.test.demo;

/**
 * 作业2练习
 */
public class HttpDemo {

    private final String url="http://localhost:8801";

    public static void main(String[] args) {

        OkHttpClient okHttpClient = new OkHttpClient();

        Request build = new Request.Builder().get().url(url).build();

        Response response = okHttpClient.newCall(build).execute();

        if (!response.isSuccessful()) {
            System.out.println("-----失败------");
        }
        System.out.println("----返回信息-----"+response.body());
    }
}
