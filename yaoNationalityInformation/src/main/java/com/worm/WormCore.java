package com.worm;


import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;


public class WormCore {

    //Document获取层对象
    private volatile Catch Catch = new Catch();
    //Document解析层对象
    private volatile Analysis analysis = new Analysis();
    //数据处理层对象
    public volatile Access access = new Access();

    public void Wormcore(LinkedBlockingQueue<String> UrlQueue) throws IOException, InterruptedException {
        synchronized (this) {
            if (!UrlQueue.isEmpty()) {
                String Url = UrlQueue.take();
                //通过Url队列中的Url抓取Document，进行Url和文本信息的抓取
                Document document = Catch.CatchDocument(Url);
                //数据解析模块返回的数据（含有文本信息以及URL）
                HashMap<String, ArrayList<String>> DataMap = analysis.AnalysisDocument(document, Url);
                //数据处理模块分离出的、只含有URL的集合
                ArrayList<String> UrlList = access.DataAccess(DataMap);

                //定义迭代器，把抓取到的Url添加到Url队列中
                Iterator<String> iterator = UrlList.iterator();


                while (iterator.hasNext()) {
                    UrlQueue.put(iterator.next());
                }
                //打印URL队列中的URL条数以及队列是否为空
                System.out.println(UrlQueue.size());
                System.out.println(UrlQueue.isEmpty());
                //为空说明爬取完毕，由于个人技术问题，在抓取完毕之后只能强制退出
                if (UrlQueue.isEmpty()) {
                    System.out.println("抓取完毕！");
                    System.exit(1);
                }
            }
        }
    }
}
