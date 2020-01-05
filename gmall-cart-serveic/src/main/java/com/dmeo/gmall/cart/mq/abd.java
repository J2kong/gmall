package com.dmeo.gmall.cart.mq;

/**
 * @author kong
 * @version 1.0
 * @description TODO
 * @date2019/11/21 15:57
 **/
public class abd {
    public static abd instance = null;


    public abd() {
    }

/*
    public static synchronized abd getInstance() {
        if (instance == null) {
            instance = new abd();
        }
        return instance;
    }
*/

/*    public static abd getInstance() {

        if (instance == null) {
            synchronized (abd.class) {
                if (instance == null) {
                    instance = new abd();
                }
            }
        }
        return instance;
    }*/

    /**
     * 3      * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 4      * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class adbHolder {
        /**
         * 8          * 静态初始化器，由JVM来保证线程安全
         */
        private static abd instance = new abd();
    }

    public static abd getInstance() {

        return adbHolder.instance;
    }
}
