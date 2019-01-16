package com.sinochem.corelibrary.mvp.multiState;

import com.sinochem.corelibrary.mvp.MvpView;

/**
 * @author jackydu
 * @date 2018/8/23
 */
public interface IMultiView extends MvpView{
    void showContent();
    void showLoading();
    void showError(String error);
    void showEmpty();
}
