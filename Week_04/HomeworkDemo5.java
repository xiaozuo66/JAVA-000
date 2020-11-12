package java0.conc0303;

import java.util.concurrent.*;

/**
 * 实现方式5：使用线程池,工具类创建、手动创建
 */
public class HomeworkDemo5 {

    private Integer result;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        HomeworkDemo5 demo4=new HomeworkDemo5();
        demo4.test();
    }

    private void test() throws InterruptedException, ExecutionException {

        ExecutorService executorService=Executors.newFixedThreadPool(1);

        Future task=executorService.submit(new Callable<Object>() {
            @Override
            public Object call() {
                System.out.println("---开始计算-----");
                return Calclator.fibo(36);
            }
        });

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+task.get());//24157817

        //关闭线程池程序才会退出，实际应用中，线程池一般应该不会关闭，会不断的向线程池提交任务
        executorService.shutdown();

        //手动创建线程池
        BlockingQueue queue=new ArrayBlockingQueue(10);
        //使用默认拒绝策略
        ExecutorService executor=new ThreadPoolExecutor(1,1,100,TimeUnit.MILLISECONDS,queue);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println("---继续计算----");
                    result=Calclator.fibo(36);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //shutdown关闭线程池，但是已提交的任务不会中断
        executor.shutdown();
        //shutdownNow关闭线程池，并且已提交的未完成的任务会被中断
//        executor.shutdownNow();
        System.out.println("---线程池已关闭-----");
        //中断等待执行结果
        Thread.sleep(1000);
        System.out.println("异步计算结果为："+result);//24157817
    }

}
