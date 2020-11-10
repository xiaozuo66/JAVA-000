package java0.conc0303;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 实现方式2：利用Callable接口
 */
public class HomeworkDemo2 {

    public Integer sum;
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Calclator result=new Calclator();

        FutureTask task=new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return result.fibo(36);
            }
        });

        new Thread(task).start();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+task.get());//24157817
    }
}

class Calclator{

    public int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

}
