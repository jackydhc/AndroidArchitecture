package com.sinochem.corelibrary.mvp;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * presenter和view 进行绑定，然后presenter通过观察者模式观察所有
 * presenter中进行的异步操作，在view的生命周期结束的时候，强制终止
 * 所有被观测的对象，当然也可以通过被观察对象直接绑定具有主动周期属性
 * 的view（继承rxlifecycler）
 */
public abstract class BasePresenter<V extends MvpView> implements IPresenter<V> {

    private V mMvpView;
    private CompositeDisposable mCompositeSubscription;

    @Override public void attachView(V mvpView) {
        mMvpView = mvpView;
        mCompositeSubscription = new CompositeDisposable();
    }

    @Override public void detachView() {
        //mMvpView = null;
        if (mCompositeSubscription != null) {
            if (mCompositeSubscription.isDisposed()) mCompositeSubscription.dispose();
            mCompositeSubscription = null;
        }

    }

    protected void addSubscription(Disposable subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeDisposable();
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

