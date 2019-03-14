package com.sinochem.androidarchitecture.enities;

/**
 * @author jackydu
 * @date 2019/3/4
 */
public class TitleBean {
    //value :blockchain(区块链资讯)；topics(热门话题);news（科技动态）;technews(开发者资讯);jobs(招聘行情)
    public static final String TYPE_TOPIC = "topic";
    public static final String TYPE_FUZZY = "fuzzy";
    public static final String TYPE_NEWS = "news";
    public static final String TYPE_BLOCKCHAIN = "blockchain";
    public static final String TYPE_TECHNEWS = "technews";
    public static final String TYPE_JOBS = "jobs";
    public String title;
    public String type;
    public TitleBean(String title,String type){
        this.title = title;
        this.type = type;
    }

}
