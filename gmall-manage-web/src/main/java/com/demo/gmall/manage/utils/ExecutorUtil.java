package com.demo.gmall.manage.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author kong
 * @version 1.0
 * @description Executor Framework并发任务处理类
 * @date2019/10/11 21:56
 **/
public class ExecutorUtil {

    /**
     * 并发执行Callable任务方法，支持泛型参数
     *
     * @param tasks
     * @return
     * @throws Exception
     */
    public static <T> List<T> concurrentExecute(List<Callable<T>> tasks)
            throws Exception {
        // 1. 获取线程池
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
                .newFixedThreadPool(100);

        // 2. 并发执行任务，并获取返回结果
        List<Future<T>> futureResults = new ArrayList<Future<T>>();
        try {
            futureResults = executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new Exception("InterruptedException occurs.");
        }

        // 3. 取回并解析返回结果
        List<T> results = getFromFutureResults(futureResults);

        return results;
    }

    /**
     * 从并发Future结果中取回并解析结果，支持泛型参数
     *
     * @param futureResults
     * @return
     * @throws Exception
     */
    private static <T> List<T> getFromFutureResults(
            List<Future<T>> futureResults) throws Exception {

        List<T> results = new ArrayList<T>();
        for (Future<T> ret : futureResults) {
            try {
                T r = ret.get(); // get()方法会阻塞等到，直到获取到结果为止
                if (null != r) {
                    results.add(r);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new Exception("InterruptedException occurs.");
            } catch (ExecutionException e) {
                e.printStackTrace();
                throw new Exception("ExecutionException occurs.");
            }
        }

        return results;
    }

}