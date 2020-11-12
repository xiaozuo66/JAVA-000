package java0.conc0303;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现方式2：利用Callable接口
 */
public class HomeworkDemo2 {

    public Integer sum;
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        FutureTask task=new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return Calclator.fibo(36);
            }
        });

        new Thread(task).start();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+task.get());//24157817
    }
}
