package com.leave996.leave996.threadpool;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadVs {

    /**
     * 新版处理方式
     */
    @Test
    public void newHandle() throws InterruptedException {
        /**
         * 开启了一个线程池，有10个线程
         */
        ExecutorService threadPool =
                Executors.newFixedThreadPool(10);

        for (int request = 1; request <= 100; request++) {
            threadPool.execute(() -> {
                System.out.println("文档处理开始！");

                try {
                    //视为耗时操作
                    Thread.sleep(1000L*30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("文档处理结束");
            });
        }

        //视为耗时操作
        Thread.sleep(1000L*1000);

    }

    /**
     * 老的处理方式
     */
    @Test
    public void oldHandle() {
        /**
         * 将Word转换为PDF格式， 处理时长很长的耗时过程
         */

        /**
         * 使用循环模拟多用户请求
         */
        for (int request = 1; request <= 100; request++) {
            new Thread(() -> {
                System.out.println("文档处理开始！");

                try {
                    //视为耗时操作
                    Thread.sleep(1000L*30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("文档处理结束");
            }).start();
        }

        try {
            //视为耗时操作
            Thread.sleep(1000L*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
