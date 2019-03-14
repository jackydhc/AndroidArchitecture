package com.sinochem.androidarchitecture.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.sinochem.androidarchitecture.R;
import com.sinochem.corelibrary.base.SwipeBackActivity;
import com.sinochem.jsbridge.BridgeWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author jackydu
 * @date 2019/3/5
 */
public class WebDetailAct extends SwipeBackActivity {
    @BindView(R.id.web_view)
    BridgeWebView webView;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.right_bar)
    TextView rightBar;

    @Override
    public int provideLayoutId() {
        return R.layout.layout_web_detail;
    }

    public static void toWebDetail(String url, String name, Context context) {
        Intent intent = new Intent(context, WebDetailAct.class);
        intent.putExtra("title", name);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    public static void toWebDetailWithID(String id, String type, Context context) {
        Intent intent = new Intent(context, WebDetailAct.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
        if (url != null) {
            setTitle(title);
            webView.loadUrl(url);
            toolbar.setVisibility(View.GONE);
        } else {
            String id = intent.getStringExtra("id");
            String type = intent.getStringExtra("type");
            String url1 = "https://readhub.cn/" + type + "/" + id;
            LogUtils.d("webact", "url:L" + url1);
            toolbar.setVisibility(View.VISIBLE);
            webView.loadUrl(url1);
        }
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void loginExpire() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.right_bar)
    public void onViewClicked() {

    }
}
