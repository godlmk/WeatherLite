package com.weather.lite.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.weather.lite.MyApplication;
import com.weather.lite.R;
import com.weather.lite.adapter.CityAdapter;
import com.weather.lite.model.City;
import com.weather.lite.util.Common;
import com.weather.lite.util.HttpUrl;
import com.weather.lite.util.OkHttpUtil;
import com.weather.lite.util.ToastUtil;
import com.weather.lite.util.ToolbarUtil;
import com.weather.lite.util.UIUtil;

import java.util.ArrayList;
import java.util.List;

public class ChooseCityActivity extends BaseActivity
        implements OnRefreshListener, CityAdapter.OnItemClickListener {

    private Toolbar toolbar;
    private EditText edtSearch;
    private TextView tvSearch;
    private SmartRefreshLayout srl;
    private RecyclerView rvPoi;

    private List<City> dataList = new ArrayList<>();
    private CityAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_city;
    }

    @Override
    protected void findView() {
        toolbar = findViewById(R.id.toolbar);
        edtSearch = findViewById(R.id.edt_search);
        tvSearch = findViewById(R.id.tv_search);
        srl = findViewById(R.id.srl);
        rvPoi = findViewById(R.id.rv_poi);
    }

    @Override
    protected void initView() {
        toolbar.setTitle("选择城市");
        ToolbarUtil.setCenter(this, toolbar);

        tvSearch.setOnClickListener(view -> srl.autoRefresh());

        rvPoi.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
        adapter = new CityAdapter(dataList, this);
        rvPoi.setAdapter(adapter);

        srl.setOnRefreshListener(this);
    }



    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        requestData();
    }

    private void requestData() {
        String key = edtSearch.getText().toString();
        if (TextUtils.isEmpty(key)) {
            return;
        }
        OkHttpUtil.getWithCode(this, HttpUrl.SELECT_DISTRICT
                        + "?key=" + Common.AMAP_WEB_KEY
                        + "&keywords=" + key
                        + "&subdistrict=0",
                10000, "infocode", "info", "districts", new OkHttpUtil.Callback() {
                    @Override
                    public void success(String msg, String data) {
                        try {
                            List<City> cityList = new Gson().fromJson(data, new TypeToken<List<City>>() {
                            }.getType());
                            dataList.clear();
                            dataList.addAll(cityList);
                            UIUtil.runOnUIThread(() -> {
                                adapter.notifyDataSetChanged();

                                srl.finishRefresh(true);
                            });
                        } catch (Exception e) {
                            UIUtil.runOnUIThread(() -> {
                                ToastUtil.showError("数据解析异常");
                                srl.finishRefresh(false);
                            });
                        }
                    }

                    @Override
                    public void fail(int code, String msg) {
                        UIUtil.runOnUIThread(() -> {
                            ToastUtil.showErrorLong(msg);
                            srl.finishRefresh(false);
                        });
                    }
                });
    }

    @Override
    public void onItemClick(int position, City data) {
        Intent intent = new Intent();
        intent.putExtra("city", data);
        setResult(RESULT_FIRST_USER, intent);
        finish();
    }

}