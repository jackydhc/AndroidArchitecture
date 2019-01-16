package com.sinochem.corelibrary.demo;

import com.sinochem.corelibrary.fragments.BaseMultiFragment;

/**
 * @author jackydu
 * @date 2019/1/14
 */
public class DemoFragment extends BaseMultiFragment<DemoPresent> {

    @Override
    public int provideLayoutId() {
        return 0;
    }

    @Override
    protected void initOnCreateView() {

    }

    @Override
    protected DemoPresent providePresent() {
        return new com.sinochem.corelibrary.demo.DemoPresent();
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void loginExpire() {

    }
}
