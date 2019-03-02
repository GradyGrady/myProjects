package com.worm;


import java.util.ArrayList;
import java.util.HashMap;

public class Access {
    //数据处理，把信息中的Url返回给核心，文本信息储存
    public ArrayList<String> DataAccess(HashMap<String, ArrayList<String>> Message){
        return Message.get("Url");
    }
}