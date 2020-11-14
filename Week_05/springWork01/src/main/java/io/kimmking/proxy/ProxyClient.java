package io.kimmking.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 代理类测试
 */
public class ProxyClient {

    public static void main(String[] args) {

        WorkInterceptor handler=new WorkInterceptor();

        handler.setWork(new WorkImpl());

        Work work= (Work) Proxy.newProxyInstance(WorkImpl.class.getClassLoader(),new Class[]{Work.class},handler);

        work.homeWork();
    }
}
