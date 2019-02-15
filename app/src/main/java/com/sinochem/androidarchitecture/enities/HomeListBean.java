package com.sinochem.androidarchitecture.enities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author jackydu
 * @date 2019/2/1
 */
public class HomeListBean implements Parcelable{


    /**
     * data : [{"summary":"比特币官方论坛Bitcoin.org持有人眼镜蛇Cobra在推特上表示，BTC不需要更多的分叉来减少区块块的大小，任何区块大小的改变都会分裂社区。","id":3905785,"title":"眼镜蛇Cobra：BTC不需要进行更多的硬分叉","summaryAuto":"比特币官方论坛Bitcoin.org持有人眼镜蛇Cobra在推特上表示，BTC不需要更多的分叉来减少区块块的大小，任何区块大小的改变都会分裂社区。","url":"https://www.jinse.com/lives/80167.htm","mobileUrl":"https://www.jinse.com/lives/80167.htm","siteName":"金色财经","language":"zh-cn","authorName":"","publishDate":"2019-02-13T01:25:23.000Z"}]
     * pageSize : 1
     * totalItems : 8320
     * totalPages : 8320
     */

    private int pageSize;
    private int totalItems;
    private int totalPages;
    private List<HomeListDataBean> data;

    protected HomeListBean(Parcel in) {
        pageSize = in.readInt();
        totalItems = in.readInt();
        totalPages = in.readInt();
    }

    public static final Creator<HomeListBean> CREATOR = new Creator<HomeListBean>() {
        @Override
        public HomeListBean createFromParcel(Parcel in) {
            return new HomeListBean(in);
        }

        @Override
        public HomeListBean[] newArray(int size) {
            return new HomeListBean[size];
        }
    };

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<HomeListDataBean> getData() {
        return data;
    }

    public void setData(List<HomeListDataBean> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pageSize);
        dest.writeInt(totalItems);
        dest.writeInt(totalPages);
    }

}
