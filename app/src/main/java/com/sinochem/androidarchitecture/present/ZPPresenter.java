package com.sinochem.androidarchitecture.present;

import com.sinochem.corelibrary.mvp.BasePresenter;
import com.sinochem.corelibrary.mvp.multiState.IMultiView;

/**
 * @author jackydu
 * @date 2019/2/19
 */
public class ZPPresenter extends BasePresenter<IMultiView> {

    @Override
    public void start() {
        getMvpView().showLoading();

    }
}
