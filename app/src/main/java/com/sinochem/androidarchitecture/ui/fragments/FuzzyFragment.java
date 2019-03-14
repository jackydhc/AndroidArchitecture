package com.sinochem.androidarchitecture.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sinochem.androidarchitecture.R;
import com.sinochem.androidarchitecture.contracts.FuzzyContract;
import com.sinochem.androidarchitecture.enities.FuzzyData;
import com.sinochem.androidarchitecture.present.FuzzyPresent;
import com.sinochem.corelibrary.fragments.BaseFragment;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * @author jackydu
 * @date 2019/3/7
 */
public class FuzzyFragment extends BaseFragment<FuzzyContract.IPresent> implements FuzzyContract.View, TextView.OnEditorActionListener, TextWatcher {

    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private BaseQuickAdapter<FuzzyData, BaseViewHolder> adapter;


    @Override
    public int provideLayoutId() {
        return R.layout.layout_fuzzy_search;
    }

    @Override
    protected void initOnCreateView() {
        adapter = new BaseQuickAdapter<FuzzyData, BaseViewHolder>(R.layout.layout_fuzzy_search_item) {
            @Override
            protected void convert(BaseViewHolder helper, FuzzyData item) {
                helper.setText(R.id.txt_search_content, item.entName);
            }

        };
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        recycler.addItemDecoration(getDefaultItemDecoration());
        editSearch.setOnEditorActionListener(this);
        editSearch.addTextChangedListener(this);
    }

    protected RecyclerView.ItemDecoration getDefaultItemDecoration() {
        return new HorizontalDividerItemDecoration.Builder(recycler.getContext())
                .colorResId(com.sinochem.corelibrary.R.color.default_divider_color)
                .sizeResId(com.sinochem.corelibrary.R.dimen.default_divider_height)
                .build();
    }

    @Override
    protected FuzzyPresent providePresent() {
        return new FuzzyPresent();
    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void loginExpire() {

    }

    @Override
    public void showData(List<FuzzyData> dataList) {
        adapter.setNewData(dataList);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH && !TextUtils.isEmpty(searchKey)){
            mPresenter.doSearch(searchKey);

            return true;
        }
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (count >0) searchKey = s.toString().trim();
        else searchKey = "";
    }

    private String searchKey;

    @Override
    public void afterTextChanged(Editable s) {

    }
}
