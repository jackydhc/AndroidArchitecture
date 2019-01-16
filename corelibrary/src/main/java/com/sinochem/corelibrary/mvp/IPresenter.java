package com.sinochem.corelibrary.mvp;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface IPresenter<V extends MvpView> {

    void start();

    void attachView(V mvpView);

    void detachView();
}

