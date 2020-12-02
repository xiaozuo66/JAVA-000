package io.kimmking.springboot.context;


public class ReadWriteDataSourceContext {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDBType(String type) {
        contextHolder.set(type);
    }

    public static String getDBType() {
        return contextHolder.get();
    }
}
