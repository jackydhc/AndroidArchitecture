package com.sinochem.corelibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.sinochem.corelibrary.CoreApplication;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by L on 2016/5/10.
 */
public class UIUtil {
    public static final String CONFIRM = "确定";
    public static final String CANCEL = "取消";
    public final static DisplayMetrics metrics = UIUtil.getContext()
            .getResources()
            .getDisplayMetrics();

    public static final float H_240DP_FLOAT = dp(240);

    /**
     * 获取到上下文对象
     **/
    public static Context getContext() {
        return CoreApplication.getBaseApplication();
    }

    public static Handler mMainHandler;

    public static Animation getAnim(int id) {
        if (id <= 0) return null;
        return AnimationUtils.loadAnimation(getContext(), id);
    }

    /**
     * 获取到主线程Handler对象
     **/
    public static Handler getMainThreadHandler() {
        if (mMainHandler == null) {
            mMainHandler = new Handler(Looper.getMainLooper());
        }
        return mMainHandler;
    }

    /**
     * 延时在主线程执行runnable
     */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getMainThreadHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * 在主线程执行runnable
     */
    public static boolean post(Runnable runnable) {
        return getMainThreadHandler().post(runnable);
    }

    /**
     * 从主线程looper里面移除runnable
     */
    public static void removeCallbacks(Runnable runnable) {
        getMainThreadHandler().removeCallbacks(runnable);
    }

    /**
     * 从主线程looper里面移除runnable
     */
    public static void removeCallbacks() {
        getMainThreadHandler().removeCallbacksAndMessages(null);
    }

    /**
     * 获取布局
     **/
    public static View inflate(Context context, @LayoutRes int layoutId) {
        return inflate(context, layoutId, null);
    }

    public static View inflate(Context context, @LayoutRes int layoutId, ViewGroup parent) {
        return inflate(context, layoutId, parent, false);
    }

    public static View inflate(Context context, @LayoutRes int layoutId, ViewGroup parent, boolean attatch) {
        return getInflater(context).inflate(layoutId, parent, attatch);
    }


    public static LayoutInflater getInflater(Context context) {
        return LayoutInflater.from(context);
    }

    /**
     * 获取资源
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取文字
     */
    public static String getString(@StringRes int resId) {
        return getResources().getString(resId);
    }

    public static String getString(@StringRes int resId, Object... formatArgs) {
        return getResources().getString(resId, formatArgs);
    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取整型数组
     */
    public static int[] getIntArray(int resId) {
        return getResources().getIntArray(resId);
    }

    /**
     * 根据图片名获取图片id
     */
    public static int getMipmapId(String name) {
        return getResources().getIdentifier(name, "mipmap", getContext().getPackageName());
    }

    /**
     * 获取dimen
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /**
     * 获取颜色
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获取颜色选择器
     */
    public static ColorStateList getColorStateList(int resId) {
        return getResources().getColorStateList(resId);
    }


    public static float getDimension(int resId) {
        return getResources().getDimension(resId);
    }


    /**
     * 判断当前的线程是不是在主线程
     **/
    public static boolean isRunInMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * 在主线程执行
     **/
    public static void runInMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    /**
     * 传入dp，即输出dp值
     */
    public static int dp(int dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics) + 0.5F);
    }

    public static float dp(float dp) {
        return (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics) + 0.5F);
    }

    /**
     * 传入sp值，即输入sp值
     */
    public static int sp(int sp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, metrics) + 0.5F);
    }

    public static void textViewVisOrGone(TextView textView, String text) {
        if (textView == null) return;
        if (TextUtils.isEmpty(text)) {
            if (textView.getVisibility() != View.GONE) textView.setVisibility(View.GONE);
            return;
        }
        textView.setText(text);
        if (textView.getVisibility() != View.VISIBLE) textView.setVisibility(View.VISIBLE);
    }

    public static void cancelView(TextView textView, ImageView viewLine, boolean needShow,
            View.OnClickListener onClickListener) {
        if (textView == null) return;

        textView.setOnClickListener(onClickListener);

        if (!needShow) {
            if (textView.getVisibility() != View.GONE) textView.setVisibility(View.GONE);
            if (viewLine != null && viewLine.getVisibility() != View.GONE) {
                viewLine.setVisibility(View.GONE);
            }
            return;
        }
        textView.setText(CANCEL);

        if (textView.getVisibility() != View.VISIBLE) textView.setVisibility(View.VISIBLE);
    }


    public static void cancelView(TextView textView, boolean needShow, View.OnClickListener onClickListener) {
        cancelView(textView, null, needShow, onClickListener);
    }


    public static void confirmView(TextView view, String text) {
        confirmView(view, text, null);
    }

    public static void confirmView(TextView view, String text, View.OnClickListener onClickListener) {
        if (view == null) return;

        if (view.getVisibility() != View.VISIBLE) view.setVisibility(View.VISIBLE);

        view.setOnClickListener(onClickListener);

        if (TextUtils.isEmpty(text)) {
            view.setText(CONFIRM);
            return;
        }
        view.setText(text);
    }

    public static boolean isKeyboardShown(View rootView) {
        final int softKeyboardHeight = 100;
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources()
                .getDisplayMetrics();
        int heightDiff = rootView.getBottom() - r.bottom;
        return heightDiff > softKeyboardHeight * dm.density;
    }

    public static Drawable getDrawable(Context context, int res) {
        if (context == null || res <= 0) return null;
        Drawable topDrawable = context.getResources()
                .getDrawable(res);
        if (topDrawable == null) return null;
        topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
        return topDrawable;
    }

    public synchronized static void setCompoundDrawTop(TextView v, int drawableId) {
        if (v == null) return;

        if (drawableId <= 0) {
            v.setCompoundDrawables(null, null, null, null);
            return;
        }

        Drawable drawable = getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        v.setCompoundDrawables(null, drawable, null, null);
    }

    public synchronized static void setCompoundDrawRight(TextView v, int drawableId) {
        if (v == null) return;

        if (drawableId <= 0) {
            v.setCompoundDrawables(null, null, null, null);
            return;
        }

        Drawable drawable = getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        v.setCompoundDrawables(null, null, drawable, null);
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    private static int mStatusHeight = -1;

    public static int getStatusHeight() {
        if (mStatusHeight != -1) {
            return mStatusHeight;
        }
        int resourceId = CoreApplication.getBaseApplication()
                .getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            mStatusHeight = CoreApplication.getBaseApplication()
                    .getResources()
                    .getDimensionPixelSize(resourceId);
        }
        return mStatusHeight;
    }

    public static boolean canActionHandlable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list != null && list.size() > 0;
    }

    public static String get4032String(int code) {
        switch (code) {
            case 1:
                return "登录态已自然失效";
            case 2:
                return "在其他设备登录相同账号，被踢下线";
            case 3:
                return "账号态被强制下线";
            case 4:
                return "密码修改，请重新登录";
        }
        return null;
    }

    /**
     * 程序是否在前台运行
     */
    public static boolean mainActIsRun(Context context) {
        if (context == null) return false;
        Intent intent = new Intent();
        intent.setClassName("com.android36kr.investment", "MainActivity");
        if (intent.resolveActivity(context.getPackageManager()) == null) {
            return false;
        }
        return true;
    }

    public synchronized static void cropBitmap(Activity context, Uri mUri, int callBackCode) {
        if (null == mUri) return;
        Intent intent = new Intent();
        intent.setAction("com.android.camera.action.CROP");
        intent.setDataAndType(mUri, "image/*");// mUri是已经选择的图片Uri
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);// 裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);// 输出图片大小
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        context.startActivityForResult(intent, callBackCode);
    }

    /**
     * 回收ImageView占用的图像内存;
     */
    public static void recycleImageView(View view) {
        if (view == null) return;
        if (view instanceof ImageView) {
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable instanceof BitmapDrawable) {
                Bitmap bmp = ((BitmapDrawable) drawable).getBitmap();
                if (bmp != null && !bmp.isRecycled()) {
                    ((ImageView) view).setImageBitmap(null);
                    bmp.recycle();
                    bmp = null;
                }
            }
        }
    }

    /**
     * 高亮
     */
    public synchronized static void textHighlight(TextView textView, String string, String mark, int color) {
        if (TextUtils.isEmpty(string) || textView == null) {
            return;
        }

        textView.setText(string);

        if (TextUtils.isEmpty(mark)) {
            return;
        }

        String newString = fullWidth2halfWidth(string);
        SpannableStringBuilder styled = new SpannableStringBuilder(string);
        String newMark = mark.replaceAll("-", "\\\\-"); // 解决'-'引起的崩溃
        Pattern p = Pattern.compile("[" + newMark + "]"); // TODO 有待修正匹配算法
        Matcher m = p.matcher(newString);
        while (m.find()) {
            int start = m.start();
            styled.setSpan(new ForegroundColorSpan(getResources().getColor(color)), start, start + 1,
                           Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(styled);
    }

    /**
     * 全角字符串转换半角字符串
     *
     * @param fullWidthStr 非空的全角字符串
     * @return 半角字符串
     */
    private static String fullWidth2halfWidth(String fullWidthStr) {
        if (null == fullWidthStr || fullWidthStr.length() <= 0) {
            return "";
        }
        char[] charArray = fullWidthStr.toCharArray();
        //对全角字符转换的char数组遍历
        for (int i = 0; i < charArray.length; ++i) {
            int charIntValue = (int) charArray[i];
            //如果符合转换关系,将对应下标之间减掉偏移量65248;如果是空格的话,直接做转换
            if (charIntValue >= 65281 && charIntValue <= 65374) {
                charArray[i] = (char) (charIntValue - 65248);
            } else if (charIntValue == 12288) {
                charArray[i] = (char) 32;
            }
        }
        return new String(charArray);
    }

    // TODO: 2017/3/1 还需要简化，目前只是收敛，保持逻辑不变

    /**
     * 隐藏输入法
     */
    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 隐藏输入法
     */
    public static void hideKeyboard(Activity context) {
        if (context == null) {
            return;
        }

        View view = context.getCurrentFocus();
        if (view == null) {
            return;
        }

        IBinder iBinder = view.getApplicationWindowToken();
        if (iBinder == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(iBinder, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示输入法
     */
    public static void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    /**
     * 强制显示输入法
     */
    public static void forceShowKeyboard(Context context) {
        if (context == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}
