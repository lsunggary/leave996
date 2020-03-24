package com.leave996.leave996.threadpool;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class QueueTest {

    @Test
    public void arrayBlockingQueueTest() throws InterruptedException {
        /**
         * 有界队列，队列容量为10
         */
        ArrayBlockingQueue queue =
                new ArrayBlockingQueue<Integer>(10);

        // 循环向队列添加元素
        for (int i = 0 ;i < 20; i++) {
            queue.put(i);
            System.out.println("向队列中添加值: " + i);
        }
    }

    /**
     *T
     */
    @Test
    public void linkedBlockingQueueTeset() throws InterruptedException {
        /**
         * 基于链表的有界队列，
         */
        LinkedBlockingQueue queue =
                new LinkedBlockingQueue<Integer>(10);

        // 循环向队列添加元素
        for (int i = 0 ;i < 20; i++) {
            queue.put(i);
            System.out.println("向队列中添加值: " + i);
        }
    }

    /**
     * 无界
     */
    @Test
    public void nolinkedBlockingQueueTeset() throws InterruptedException {
        /**
         * 基于链表的有界队列，如果不填数字，则可以视为无界
         */
        LinkedBlockingQueue queue =
                new LinkedBlockingQueue<Integer>();

        // 循环向队列添加元素
        for (int i = 0 ;i < 20; i++) {
            queue.put(i);
            System.out.println("向队列中添加值: " + i);
        }
    }

    /**
     * 同步移交队列
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {
        /**
         * 同步移交阻塞队列
         */
        SynchronousQueue queue =
                new SynchronousQueue<Integer>();

        //插入值
        new Thread(() -> {
            try {
                queue.put(1);
                System.out.println("输入成功！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;
        }).start();

        // 删除值
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                queue.take();
                System.out.println("删除成功！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000L * 60);
    }
}
