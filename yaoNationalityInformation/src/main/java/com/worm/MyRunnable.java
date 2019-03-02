package com.worm;

import java.io.IOException;
import static com.worm.WormMain.UrlQueue;
import static com.worm.WormMain.wormCore;

public class MyRunnable implements Runnable {

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(200);
                //把主方法中的URL队列传给核心控制类，开始该线程的爬取
                wormCore.Wormcore(UrlQueue);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}