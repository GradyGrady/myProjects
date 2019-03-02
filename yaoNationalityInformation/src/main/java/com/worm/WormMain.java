package com.worm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;


public class WormMain {
    //待抓取的Url队列，全局共享
    public static final LinkedBlockingQueue<String> UrlQueue = new LinkedBlockingQueue<>();

    public static final WormCore wormCore = new WormCore();

    public static void main(String[] args) {
        //要抓取的根URL
        String rootUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/index.html";
        //先把根URL加入URL队列
        UrlQueue.offer(rootUrl);
        Runnable runnable = new MyRunnable();
        //开启固定大小的线程池，爬取的过程由10个线程完成
        ExecutorService Fixed = Executors.newFixedThreadPool(10);

        //开始爬取
        for (int i = 0; i < 10; i++) {
            Fixed.submit(runnable);
        }
        //关闭线程池
        Fixed.shutdown();

    }
}
