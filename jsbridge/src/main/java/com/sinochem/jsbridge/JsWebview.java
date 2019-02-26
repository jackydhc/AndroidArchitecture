package com.sinochem.jsbridge;

import android.content.Context;
import android.util.AttributeSet;

import com.tencent.smtt.sdk.WebView;

/**
 * @author jackydu
 * @date 2019/2/25
 */
public class JsWebview extends WebView{

    public JsWebview(Context context) {
        this(context,null);
    }

    public JsWebview(Context context, AttributeSet attributeSet) {
        this(context, attributeSet,-1);
    }

    public JsWebview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    protected void init(){

    }

}
