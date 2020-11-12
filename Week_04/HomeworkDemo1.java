package java0.conc0303;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现方式1：利用引用传递，把主线程的变量传递给计算的线程，计算结束从变量中获取到结果,
 * 跟ThreadLocal类似
 */
public class HomeworkDemo1 {

    public Integer sum;
    
    public static void main(String[] args) throws InterruptedException {

        final Map<String,Object> result=new HashMap<>();

        MyThread myThread=new MyThread(result);
        myThread.start();

        myThread.join();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result.get("result"));//24157817
    }
}

class MyThread extends Thread {

    private Map<String,Object> resultmap;

    MyThread(Map<String,Object> map){
        resultmap=map;
    }

    @Override
    public void run() {
        resultmap.put("result",fibo(36));
    }

    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

}
