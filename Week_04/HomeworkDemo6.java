package java0.conc0303;

import java.util.concurrent.CompletableFuture;

/**
 * 实现方式6：使用CompletableFuture
 */
public class HomeworkDemo6 {

    public static void main(String[] args){

        Integer result=CompletableFuture.supplyAsync(()->{return Calclator.fibo(36);}).join();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);//24157817

        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("---方法测试----");
            }
        }).join();

        CompletableFuture.supplyAsync(()->{return 36;}).thenAccept((v)->{
            System.out.println(Calclator.fibo(v));});

    }
}
