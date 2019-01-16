package com.sinochem.corelibrary.base;

import android.view.LayoutInflater;
import android.view.View;

import com.sinochem.corelibrary.SwipeBackLayout;


/**
 * author: jackydu
 * date: on 17/6/19 14:45
 * description:
 */
public abstract class SwipeBackActivity extends BaseActivity implements SwipeBackLayout.SwipeBackListener {

    private static final SwipeBackLayout.DragEdge DEFAULT_DRAG_EDGE = SwipeBackLayout.DragEdge.LEFT;

    private SwipeBackLayout swipeBackLayout;

    @Override public void setContentView(int layoutResID) {
        super.setContentView(getContainer());
        View view = LayoutInflater.from(this)
                .inflate(layoutResID, null);
        getSwipeBackLayout().addView(view);
    }

    private View getContainer() {
        getSwipeBackLayout().setDragEdge(DEFAULT_DRAG_EDGE);
        getSwipeBackLayout().setOnSwipeBackListener(this);
        return getSwipeBackLayout();
    }

    public void setDragEdge(SwipeBackLayout.DragEdge dragEdge) {
        getSwipeBackLayout().setDragEdge(dragEdge);
    }

    public void setSwipeBackEnabled(boolean enabled) {
        getSwipeBackLayout().setEnabled(enabled);
    }

    public SwipeBackLayout getSwipeBackLayout() {
        if (swipeBackLayout == null) {
            swipeBackLayout = new SwipeBackLayout(this);
        }
        return swipeBackLayout;
    }

    @Override public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
    }

}
