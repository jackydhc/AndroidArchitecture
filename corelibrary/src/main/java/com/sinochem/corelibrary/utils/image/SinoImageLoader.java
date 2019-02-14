package com.sinochem.corelibrary.utils.image;


import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.sinochem.corelibrary.BuildConfig;
import com.sinochem.corelibrary.R;
import com.sinochem.corelibrary.utils.UIUtil;


import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by wentao *_*
 * on 15/12/11
 */
public class SinoImageLoader {

    private static volatile SinoImageLoader instance;
    private CropCircleTransformation circleCrop;
    private RoundedCornersTransformation roundCrop;
    private CenterCrop centerCrop;

    private SinoImageLoader() {

    }

    public static SinoImageLoader instance() {
        if (instance == null) {
            synchronized (SinoImageLoader.class) {
                if (instance == null) {
                    instance = new SinoImageLoader();
                }
            }
        }
        return instance;
    }

    private static boolean isNull(ImageView imageView) {
        if (BuildConfig.DEBUG && imageView == null) {
            throw new NullPointerException("target is null");
        }

        return imageView == null;
    }

    public void displayImageArticle(Context context, String url, ImageView target) {
        if (isNull(target)) {
            return;
        }

        Glide.with(context)
                .load(url)
                .placeholder(R.color.white)
                .fitCenter()
                .animate(R.anim.no_thing)
                .into(target);
    }

    public void displayImage(Context context, String url, ImageView target) {
        if (isNull(target)) {
            return;
        }

        Glide.with(context)
                .load(url)
                .placeholder(R.color.white)
                .fitCenter()
                .animate(R.anim.no_thing)
                .into(target);
    }

    public void disCenterCrop(Context context, String url, ImageView target) {
        if (isNull(target)) {
            return;
        }

        Glide.with(context)
                .load(url)
                .placeholder(R.color.white)
                .centerCrop()
                .animate(R.anim.no_thing)
                .into(target);
    }

    public void disCenterCropDEFResId(Context context, String url, int resId, ImageView target) {
        if (isNull(target)) {
            return;
        }

        Glide.with(context)
                .load(url)
                .placeholder(resId)
                .centerCrop()
                .animate(R.anim.no_thing)
                .into(target);
    }

    public void disCenterCropDefBlack(Context context, String url, ImageView target) {
        if (isNull(target)) {
            return;
        }

        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.color.white)
                .animate(R.anim.no_thing)
                .into(target);
    }

    public void disCropCircle(Context context, String url, ImageView target, @DrawableRes int holderId) {
        if (isNull(target)) {
            return;
        }

        if (circleCrop == null) {
            circleCrop = new CropCircleTransformation(Glide.get(context)
                                                              .getBitmapPool());
        }
        Glide.with(context)
                .load(url)
                .placeholder(holderId)
                .bitmapTransform(circleCrop)
                .animate(R.anim.no_thing)
                .into(target);
    }




    public void disCropRound(Context context, String url, ImageView imageView, int radius,
            @DrawableRes int placeholder) {
        if (isNull(imageView)) {
            return;
        }

        if (roundCrop == null) {
            BitmapPool bitmapPool = Glide.get(context)
                    .getBitmapPool();
            roundCrop = new RoundedCornersTransformation(bitmapPool, UIUtil.dp(radius),0);
            centerCrop = new CenterCrop(bitmapPool);
        }

        Glide.with(context)
                .load(url)
                .placeholder(placeholder)
                .bitmapTransform(centerCrop, roundCrop)
                .into(imageView);
    }


    public CenterCrop getCenterCrop(Context context) {
        if (centerCrop == null) {
            centerCrop = new CenterCrop(Glide.get(context)
                                                .getBitmapPool());
        }
        return centerCrop;
    }

}
