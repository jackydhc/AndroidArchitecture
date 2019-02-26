package com.sinochem.androidarchitecture.contracts;

import com.sinochem.corelibrary.mvp.multiState.IMultiView;

/**
 * @author jackydu
 * @date 2019/2/22
 */
public interface Detail {
    interface View extends IMultiView{
        void commentSuccess();
    }
}
