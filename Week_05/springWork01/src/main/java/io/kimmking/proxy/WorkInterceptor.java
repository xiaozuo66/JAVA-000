package io.kimmking.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WorkInterceptor implements InvocationHandler {

    private Work work;

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---写作业之前了解作业要求--------");
        method.invoke(work,args);
        System.out.println("---写完作业提交github--------");
        return null;
    }
}
