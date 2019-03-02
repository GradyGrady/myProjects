package com.worm;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

import static com.util.Util.IsNumber;

public class Analysis {

    //根据Document解析网页
    public HashMap<String, ArrayList<String>> AnalysisDocument(Document document, String Url){
        //因为网页上的URL为相对地址，所以在这里进行URL的拼接，这是前半部分
        String Before_Url = Url.substring(0, Url.lastIndexOf("/") + 1);

        //储存文本信息的List
        ArrayList<String> Text = new ArrayList<>();
        //储存Url的List
        ArrayList<String> Urls = new ArrayList<>();

        HashMap<String,ArrayList<String>> Message = new HashMap<>();
        //最后一个页面的前三个文本不是我们想要的
        int Flag = 1;

        Elements elements = document.select("tr[class]").select("a[href]");
        //最后一个页面的处理
        if(elements.isEmpty()){
            elements = document.select("tr[class]").select("td");
            for (Element element : elements) {
                if (!IsNumber(element.text()) && Flag > 3) {
                    System.out.println(element.text());
                }
                Flag++;
            }
            //普通页面的处理
        }else {
            for (Element element : elements) {
                if (!IsNumber(element.text())) {
                    Text.add(element.text());
                    System.out.println(element.text());
                    Urls.add(Before_Url + element.attr("href"));
                }
            }
        }
        //把文本集合和URL集合装到Map中返回
        Message.put("text",Text);
        Message.put("Url",Urls);
        return Message;

    }

}
