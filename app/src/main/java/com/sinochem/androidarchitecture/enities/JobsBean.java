package com.sinochem.androidarchitecture.enities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author jackydu
 * @date 2019/3/4
 */
public class JobsBean {

    /**
     * id : 18195
     * uuid : b699ed4e3ddc11e9bdc3
     * jobTitle : 开发工程师
     * jobCount : 590
     * companyCount : 494
     * salaryLower : 6
     * salaryUpper : 10
     * experienceLower : 1
     * experienceUpper : 3
     * cities : {"北京":93,"上海":66,"杭州":53}
     * sources : {"内推网":1,"拉勾网":97,"BOSS 直聘":491,"阿里巴巴":1}
     * jobsArray : [{"id":4267259,"url":"https://job.alibaba.com/zhaopin/position_detail.htm?positionId=64161","city":"北京","title":"阿里集团-数据技术工程师/数据技术专家-商业智能部","company":"阿里巴巴","sponsor":false,"siteName":"阿里巴巴","salaryLower":-1,"salaryUpper":-1,"experienceLower":3,"experienceUpper":-1},{"id":4267430,"url":"http://www.neitui.me/j/868951","city":"北京","title":"前端开发工程师","company":"地平线机器人","sponsor":false,"siteName":"内推网","salaryLower":10,"salaryUpper":15,"experienceLower":1,"experienceUpper":3},{"id":4277428,"url":"https://www.lagou.com/jobs/5642618.html","city":"上海","title":"Java软件开发工程师","company":"思创网络","sponsor":false,"siteName":"拉勾网","salaryLower":10,"salaryUpper":15,"experienceLower":1,"experienceUpper":3},{"id":4271079,"url":"https://www.zhipin.com/job_detail/d15ed8433d64be561HF509i7E1Q~.html","city":"北京","title":"Java开发工程师","company":"钛鑫科技","sponsor":false,"siteName":"BOSS 直聘","salaryLower":10,"salaryUpper":15,"experienceLower":1,"experienceUpper":3}]
     * publishDate : 2019-03-02T16:04:40.000Z
     * createdAt : 2019-03-03T09:50:06.489Z
     */

    private int id;
    private String uuid;
    private String jobTitle;
    private int jobCount;
    private int companyCount;
    private int salaryLower;
    private int salaryUpper;
    private int experienceLower;
    private int experienceUpper;
    private String publishDate;
    private String createdAt;
    private List<JobsArrayBean> jobsArray;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getJobCount() {
        return jobCount;
    }

    public void setJobCount(int jobCount) {
        this.jobCount = jobCount;
    }

    public int getCompanyCount() {
        return companyCount;
    }

    public void setCompanyCount(int companyCount) {
        this.companyCount = companyCount;
    }

    public int getSalaryLower() {
        return salaryLower;
    }

    public void setSalaryLower(int salaryLower) {
        this.salaryLower = salaryLower;
    }

    public int getSalaryUpper() {
        return salaryUpper;
    }

    public void setSalaryUpper(int salaryUpper) {
        this.salaryUpper = salaryUpper;
    }

    public int getExperienceLower() {
        return experienceLower;
    }

    public void setExperienceLower(int experienceLower) {
        this.experienceLower = experienceLower;
    }

    public int getExperienceUpper() {
        return experienceUpper;
    }

    public void setExperienceUpper(int experienceUpper) {
        this.experienceUpper = experienceUpper;
    }


    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<JobsArrayBean> getJobsArray() {
        return jobsArray;
    }

    public void setJobsArray(List<JobsArrayBean> jobsArray) {
        this.jobsArray = jobsArray;
    }



    public static class JobsArrayBean {
        /**
         * id : 4267259
         * url : https://job.alibaba.com/zhaopin/position_detail.htm?positionId=64161
         * city : 北京
         * title : 阿里集团-数据技术工程师/数据技术专家-商业智能部
         * company : 阿里巴巴
         * sponsor : false
         * siteName : 阿里巴巴
         * salaryLower : -1
         * salaryUpper : -1
         * experienceLower : 3
         * experienceUpper : -1
         */

        private int id;
        private String url;
        private String city;
        private String title;
        private String company;
        private boolean sponsor;
        private String siteName;
        private int salaryLower;
        private int salaryUpper;
        private int experienceLower;
        private int experienceUpper;

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

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public boolean isSponsor() {
            return sponsor;
        }

        public void setSponsor(boolean sponsor) {
            this.sponsor = sponsor;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public int getSalaryLower() {
            return salaryLower;
        }

        public void setSalaryLower(int salaryLower) {
            this.salaryLower = salaryLower;
        }

        public int getSalaryUpper() {
            return salaryUpper;
        }

        public void setSalaryUpper(int salaryUpper) {
            this.salaryUpper = salaryUpper;
        }

        public int getExperienceLower() {
            return experienceLower;
        }

        public void setExperienceLower(int experienceLower) {
            this.experienceLower = experienceLower;
        }

        public int getExperienceUpper() {
            return experienceUpper;
        }

        public void setExperienceUpper(int experienceUpper) {
            this.experienceUpper = experienceUpper;
        }
    }
}
