package com.sinochem.androidarchitecture.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.sinochem.androidarchitecture.R;
import com.sinochem.corelibrary.base.SwipeBackActivity;
import com.sinochem.jsbridge.BridgeWebView;

import butterknife.BindView;

/**
 * @author jackydu
 * @date 2019/3/5
 */
public class WebDetailAct extends SwipeBackActivity {
    @BindView(R.id.web_view)
    BridgeWebView webView;

    @Override
    public int provideLayoutId() {
        return R.layout.layout_web_detail;
    }

    public static void toWebDetail(String url, String name, Context context){
        Intent intent = new Intent(context,WebDetailAct.class);
        intent.putExtra("title",name);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
        setTitle(title);
        webView.loadUrl(url);
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void loginExpire() {

    }

}
