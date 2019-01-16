package com.sinochem.corelibrary.base.list;



import com.sinochem.corelibrary.mvp.BasePresenter;
import com.sinochem.corelibrary.mvp.MvpView;
import com.sinochem.corelibrary.mvp.multiState.IMultiView;

import java.util.List;

/**
 * author: jackydu
 * date: on 17/1/4 16:12
 * description:
 */

public interface BaseListContract {

    interface IListMvpView<C extends List> extends IMultiView {
        void showContent(C data, boolean refresh);
        void showComplete();
    }

    abstract class IRefreshPresenter<C extends List> extends BasePresenter<IListMvpView> implements IRefreshLoadMore {

    }

}
