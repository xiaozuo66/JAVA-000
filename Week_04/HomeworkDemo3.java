package java0.conc0303;

import java.util.concurrent.CountDownLatch;

/**
 * 实现方式3：利用CountDownLatch闭锁，在主线程中等待子线程执行完毕
 * CountDownLatch即闭锁，通俗理解就是一个线程等待多个线程都结束
 * --等所有人都干完活，就一起去吃饭
 */
public class HomeworkDemo3 {

    private Integer result;

    public static void main(String[] args) throws InterruptedException {

        HomeworkDemo3 demo3=new HomeworkDemo3();
        demo3.test();
    }

    private void test() throws InterruptedException {
        //通过一个线程计算结果
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    System.out.println("---开始计算----");
                    result=Calclator.fibo(36);
                    System.out.println("---计算结束----");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    //线程内必须加上这一句，否则主线程会一直等待;放到finally中是为了避免出现异常的话出现主线程“死等”的情况
                    countDownLatch.countDown();
                    countDownLatch.countDown();
                    //可以多次调用countDown
                }
            }
        }).start();
        System.out.println("---等待计算结果------");
        countDownLatch.await();//等待子线程执行完毕，在创建CountDownLatch的时候指定需要等待几个线程
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);//24157817
    }

}
