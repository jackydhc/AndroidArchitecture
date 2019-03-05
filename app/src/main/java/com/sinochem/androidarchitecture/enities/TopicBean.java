package com.sinochem.androidarchitecture.enities;

import java.util.List;

/**
 * @author jackydu
 * @date 2019/3/4
 */
public class TopicBean {

    /**
     * id : 7KLRgBFEOCy
     * nelData : {"state":true,"result":[{"weight":0,"nerName":"LG","entityId":"baike_1653878","entityName":"LG","entityType":"company","entityUniqueId":"baike_1653878","finance":null},{"weight":0,"nerName":"OPPO","entityId":"baike_718115","entityName":"OPPO","entityType":"company","entityUniqueId":"baike_718115","finance":null},{"weight":0.418314923516589,"nerName":"vivo","entityId":"baike_909123","entityName":"vivo","entityType":"company","entityUniqueId":"baike_909123","finance":null},{"weight":0.744517494017285,"nerName":"三星","entityId":"baike_244153","entityName":"三星","entityType":"company","entityUniqueId":"baike_244153","finance":null},{"weight":0.422999053530122,"nerName":"华为","entityId":"baike_6455903","entityName":"华为","entityType":"company","entityUniqueId":"baike_6455903","finance":null},{"weight":0.421212545753275,"nerName":"小米","entityId":"baike_3250213","entityName":"小米","entityType":"company","entityUniqueId":"baike_3250213","finance":{"code":"HK:01810","name":"小米集团-W"}},{"weight":0,"nerName":"联想","entityId":"baike_1369017","entityName":"联想","entityType":"company","entityUniqueId":"baike_1369017","finance":null},{"weight":0.369394582404647,"nerName":"苹果","entityId":"baike_304038","entityName":"苹果","entityType":"company","entityUniqueId":"baike_304038","finance":{"code":"NASDAQ:AAPL","name":"苹果"}},{"weight":0,"nerName":"阿尔卡特","entityId":"baike_11901","entityName":"阿尔卡特","entityType":"company","entityUniqueId":"baike_11901","finance":null}]}
     * newsArray : [{"id":1440249,"url":"https://tech.sina.cn/it/2019-03-04/detail-ihsxncvf9689307.d.html?from=wap","title":"2018四季度全球手机市场报告：三星苹果华为份额紧咬","siteName":"新浪科技","mobileUrl":"https://tech.sina.cn/it/2019-03-04/detail-ihsxncvf9689307.d.html?from=wap","autherName":"新浪科技","duplicateId":1,"publishDate":"2019-03-04T08:01:04.000Z","language":"zh-cn","statementType":1}]
     * createdAt : 2019-03-04T09:01:18.706Z
     * eventData : []
     * publishDate : 2019-03-04T09:01:18.703Z
     * summary : 根据报告，全球智能手机市场上，三星的市场份额占18%，苹果占17%，华为占15%，OPPO占8%，小米和vivo均占7%，联想占3%，其他品牌占25% ... 拉丁美洲：三星占36%，华为占14%，联想占13%，LG占5%，苹果占5% ... 中东地区与非州：三星占22%，华为占11%，Tecno占9%，itel占7%，苹果占5%。
     * title : 2018四季度全球手机市场报告：三星苹果华为份额紧咬
     * updatedAt : 2019-03-04T09:01:18.706Z
     * timeline : null
     * order : 118208
     * extra : {"instantView":false}
     */

    private String id;
    private NelDataBean nelData;
    private String createdAt;
    private String publishDate;
    private String summary;
    private String title;
    private String updatedAt;
    private int order;
    private ExtraBean extra;
    private List<NewsArrayBean> newsArray;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NelDataBean getNelData() {
        return nelData;
    }

    public void setNelData(NelDataBean nelData) {
        this.nelData = nelData;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public List<NewsArrayBean> getNewsArray() {
        return newsArray;
    }

    public void setNewsArray(List<NewsArrayBean> newsArray) {
        this.newsArray = newsArray;
    }

    public static class NelDataBean {
        /**
         * state : true
         * result : [{"weight":0,"nerName":"LG","entityId":"baike_1653878","entityName":"LG","entityType":"company","entityUniqueId":"baike_1653878","finance":null},{"weight":0,"nerName":"OPPO","entityId":"baike_718115","entityName":"OPPO","entityType":"company","entityUniqueId":"baike_718115","finance":null},{"weight":0.418314923516589,"nerName":"vivo","entityId":"baike_909123","entityName":"vivo","entityType":"company","entityUniqueId":"baike_909123","finance":null},{"weight":0.744517494017285,"nerName":"三星","entityId":"baike_244153","entityName":"三星","entityType":"company","entityUniqueId":"baike_244153","finance":null},{"weight":0.422999053530122,"nerName":"华为","entityId":"baike_6455903","entityName":"华为","entityType":"company","entityUniqueId":"baike_6455903","finance":null},{"weight":0.421212545753275,"nerName":"小米","entityId":"baike_3250213","entityName":"小米","entityType":"company","entityUniqueId":"baike_3250213","finance":{"code":"HK:01810","name":"小米集团-W"}},{"weight":0,"nerName":"联想","entityId":"baike_1369017","entityName":"联想","entityType":"company","entityUniqueId":"baike_1369017","finance":null},{"weight":0.369394582404647,"nerName":"苹果","entityId":"baike_304038","entityName":"苹果","entityType":"company","entityUniqueId":"baike_304038","finance":{"code":"NASDAQ:AAPL","name":"苹果"}},{"weight":0,"nerName":"阿尔卡特","entityId":"baike_11901","entityName":"阿尔卡特","entityType":"company","entityUniqueId":"baike_11901","finance":null}]
         */

        private boolean state;
        private List<ResultBean> result;

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * weight : 0
             * nerName : LG
             * entityId : baike_1653878
             * entityName : LG
             * entityType : company
             * entityUniqueId : baike_1653878
             * finance : null
             */

            private double weight;
            private String nerName;
            private String entityId;
            private String entityName;
            private String entityType;
            private String entityUniqueId;
            private Object finance;

            public double getWeight() {
                return weight;
            }

            public void setWeight(double weight) {
                this.weight = weight;
            }

            public String getNerName() {
                return nerName;
            }

            public void setNerName(String nerName) {
                this.nerName = nerName;
            }

            public String getEntityId() {
                return entityId;
            }

            public void setEntityId(String entityId) {
                this.entityId = entityId;
            }

            public String getEntityName() {
                return entityName;
            }

            public void setEntityName(String entityName) {
                this.entityName = entityName;
            }

            public String getEntityType() {
                return entityType;
            }

            public void setEntityType(String entityType) {
                this.entityType = entityType;
            }

            public String getEntityUniqueId() {
                return entityUniqueId;
            }

            public void setEntityUniqueId(String entityUniqueId) {
                this.entityUniqueId = entityUniqueId;
            }

            public Object getFinance() {
                return finance;
            }

            public void setFinance(Object finance) {
                this.finance = finance;
            }
        }
    }

    public static class ExtraBean {
        /**
         * instantView : false
         */

        private boolean instantView;

        public boolean isInstantView() {
            return instantView;
        }

        public void setInstantView(boolean instantView) {
            this.instantView = instantView;
        }
    }

    public static class NewsArrayBean {
        /**
         * id : 1440249
         * url : https://tech.sina.cn/it/2019-03-04/detail-ihsxncvf9689307.d.html?from=wap
         * title : 2018四季度全球手机市场报告：三星苹果华为份额紧咬
         * siteName : 新浪科技
         * mobileUrl : https://tech.sina.cn/it/2019-03-04/detail-ihsxncvf9689307.d.html?from=wap
         * autherName : 新浪科技
         * duplicateId : 1
         * publishDate : 2019-03-04T08:01:04.000Z
         * language : zh-cn
         * statementType : 1
         */

        private int id;
        private String url;
        private String title;
        private String siteName;
        private String mobileUrl;
        private String autherName;
        private int duplicateId;
        private String publishDate;
        private String language;
        private int statementType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public String getMobileUrl() {
            return mobileUrl;
        }

        public void setMobileUrl(String mobileUrl) {
            this.mobileUrl = mobileUrl;
        }

        public String getAutherName() {
            return autherName;
        }

        public void setAutherName(String autherName) {
            this.autherName = autherName;
        }

        public int getDuplicateId() {
            return duplicateId;
        }

        public void setDuplicateId(int duplicateId) {
            this.duplicateId = duplicateId;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public int getStatementType() {
            return statementType;
        }

        public void setStatementType(int statementType) {
            this.statementType = statementType;
        }
    }
}
