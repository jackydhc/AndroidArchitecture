package com.sinochem.corelibrary.mvp;

import com.sinochem.corelibrary.fragments.BaseFragment;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public abstract class BasePresenter<V extends MvpView> implements IPresenter<V> {

    private V mMvpView;
    private CompositeSubscription mCompositeSubscription;

    @Override public void attachView(V mvpView) {
        mMvpView = mvpView;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override public void detachView() {
        //mMvpView = null;
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription = null;
        }

    }

    protected void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    /** 尽量可以不用， 当没有使用SimpleSubscribe时使用 */
    public boolean isViewNotAttached() {
        return mMvpView == null || !mMvpView.isAlive();
    }

    public V getMvpView() {
        //checkViewAttached();
        return mMvpView;
    }

    public void checkViewAttached() {
        if (isViewNotAttached()) throw new MvpViewNotAttachedException();
    }



    private static class MvpViewNotAttachedException extends RuntimeException {
        MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter");
        }
    }
}

