package java0.conc0303;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 实现方式4：利用CyclicBarrier栅栏，在回调中修改标志位
 * 主线程等待标志位的修改，计算结束后拿到结果返回。
 */
public class HomeworkDemo4 {

    private Integer result;

    boolean finish=false;//设置标志位，计算完成则置为true

    public static void main(String[] args) throws InterruptedException {

        HomeworkDemo4 demo4=new HomeworkDemo4();
        demo4.test();
    }

    private void test() throws InterruptedException {
        //通过一个线程计算结果,这里设置成几个线程就要求有几个线程调用cyclicBarrier.await()，否则一直等待
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有线程已结束");
                finish=true;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    System.out.println("---空线程---");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    //如果在线程开始的地方调用await方法，下面的代码不会继续执行，而是在回调结束后再继续执行
//                    cyclicBarrier.await();
                    System.out.println("---开始计算----");
                    result=Calclator.fibo(36);
                    System.out.println("---计算结束----");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    //线程内必须加上这一句，否则主线程会一直等待;放到finally中是为了避免出现异常的话出现主线程“死等”的情况
                    try {
                       /* 如果设置的线程数与实际线程数小，那么多次调用await方法会多次执行回调
                        如果设置的线程比实际线程多，多次调用await方法依然会阻塞
                        如果设置的线程与实际线程相等，多次调用await也会导致一直等待；
                        */
//                        cyclicBarrier.await();
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        System.out.println("---子线程已启动----");
//        Thread.sleep(5000);
        while (!finish){
            System.out.println("----等待计算结果------");
            continue;
        }
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);//24157817
    }

}
