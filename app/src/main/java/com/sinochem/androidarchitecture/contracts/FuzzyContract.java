package com.sinochem.androidarchitecture.contracts;

import com.sinochem.androidarchitecture.enities.FuzzyData;
import com.sinochem.corelibrary.mvp.BasePresenter;
import com.sinochem.corelibrary.mvp.MvpView;

import java.util.List;

/**
 * @author jackydu
 * @date 2019/3/7
 */
public interface FuzzyContract {
    interface View extends MvpView{
        void showData(List<FuzzyData> dataList);
    }

    abstract class IPresent extends BasePresenter<FuzzyContract.View>{
        public abstract void doSearch(String searchWorld);
    }
}
