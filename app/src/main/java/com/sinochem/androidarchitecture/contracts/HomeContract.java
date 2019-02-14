package com.sinochem.androidarchitecture.contracts;

import com.sinochem.corelibrary.mvp.multiState.IMultiView;

/**
 * @author jackydu
 * @date 2019/2/1
 */
public interface HomeContract {

    interface IView extends IMultiView{
        void showAD();
        void showMenu();
    }
}
