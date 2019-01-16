package com.sinochem.corelibrary.ui.weidget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.sinochem.corelibrary.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by hk_jacky on 2018/4/16.
 */

public class JDHeader extends RelativeLayout implements RefreshHeader {

    protected ImageView mFishImageView;
    protected AnimationDrawable mFishSwimmingAnimationDrawable;
    protected int mFishWidth = 25; //
    protected int mFinishDuration = 300; // 延迟300毫秒之后再弹回
    protected int mDefaultPadding = 10; // 头部间距
    private DensityUtil density;

    public JDHeader(Context context) {
        this(context,null);
    }

    public JDHeader(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public JDHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }



    private void initView(Context mCxt) {
        density = new DensityUtil();
        mFishImageView = new ImageView(mCxt);
        mFishImageView.setImageResource(R.drawable.mexp_loading_drawalbe);
        mFishSwimmingAnimationDrawable = (AnimationDrawable) mFishImageView.getDrawable();
        int minimumHeight = mFishSwimmingAnimationDrawable.getMinimumWidth();
        LayoutParams fishLayoutParams = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        fishLayoutParams.addRule(CENTER_IN_PARENT);
        fishLayoutParams.width = density.dip2px(mFishWidth);
        fishLayoutParams.height = density.dip2px(mFishWidth);
        addView(mFishImageView, fishLayoutParams);
        setBackgroundColor(Color.parseColor("#f3f5f9"));
        setPadding(0, density.dip2px(mDefaultPadding), 0 , density.dip2px(mDefaultPadding));
    }

    @Override
    public void onPullingDown(float percent, int offset, int headerHeight, int extendHeight) {
        if (percent > 1.0f) {
            return;
        }
        float mPercent = percent >= 1.0f ? 1.0f : percent;
        setFishImageViewSizePx(density.dip2px(mPercent * mFishWidth));
    }

    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {
        float mPercent = percent <= 1.0f ? percent : 1.0f;
        setFishImageViewSizePx(density.dip2px(mPercent * mFishWidth));
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }


    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {
        if (mFishSwimmingAnimationDrawable != null) {
            mFishSwimmingAnimationDrawable.start();
        }
    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        if (mFishSwimmingAnimationDrawable != null) {
            mFishSwimmingAnimationDrawable.stop();
        }
        return mFinishDuration;
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    public void setFishImageViewSizePx(int px) {
        ViewGroup.LayoutParams lpArrow = mFishImageView.getLayoutParams();
        lpArrow.width = px;
        lpArrow.height = px;
        mFishImageView.setLayoutParams(lpArrow);
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState refreshState, RefreshState refreshState1) {

    }
}
