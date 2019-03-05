package com.sinochem.corelibrary.base.list;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.WindowManager;

import com.sinochem.corelibrary.R;
import com.sinochem.corelibrary.base.BaseActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatDialogFragment;

/**
 * @author jackydu
 * @date 2019/3/4
 */
public class BaseDialogFragment extends RxAppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setStyle(STYLE_NO_TITLE, R.style.BottomDialog);
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        WindowManager.LayoutParams params = getDialog().getWindow()
                .getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow()
                .setAttributes(params);
    }

    public void show(FragmentManager manager) {
        if (manager == null) return;
        if (this.isAdded()) {
            return;
        }

        manager.beginTransaction()
                .add(this, getClass().getName())
                .commitAllowingStateLoss();
    }

    /**
     * Can not perform this action after onSaveInstanceState
     */
    public void show(AppCompatActivity activity) {
        if (activity == null || activity.isFinishing()) return;
        if (!isAdded()) {
            if (activity instanceof BaseActivity) {
                BaseActivity baseActivity = (BaseActivity) activity;
                if (baseActivity.isOnPause) return;
            }
            show(activity.getSupportFragmentManager());
        }
    }
}
