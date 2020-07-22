package com.weather.lite.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kongzue.dialog.v3.WaitDialog;
import com.weather.lite.MyApplication;
import com.weather.lite.R;
import com.weather.lite.adapter.DailyAdapter;
import com.weather.lite.adapter.IndexAdapter;
import com.weather.lite.model.City;
import com.weather.lite.model.Daily;
import com.weather.lite.model.Index;
import com.weather.lite.model.Now;
import com.weather.lite.util.Common;
import com.weather.lite.util.HttpUrl;
import com.weather.lite.util.OkHttpUtil;
import com.weather.lite.util.SharedPrefsUtil;
import com.weather.lite.util.ToastUtil;
import com.weather.lite.util.UIUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ImageView ivSearch;
    private TextView tvCity;
    private TextView tvTemp;
    private TextView tvText;
    private TextView tvFeelsLike;
    private TextView tvWindScale;
    private TextView tvWindDir;
    private TextView tvHumidity;
    private TextView tvPressure;
    private TextView tvPrecip;
    private TextView tvVis;
    private TextView tvCloud;
    private TextView tvDew;
    private RecyclerView rvDaily;
    private RecyclerView rvIndex;

    private City city;
    private Now now;

    private ImageView backGround;

    private List<Daily> dailyList = new ArrayList<>();
    private List<Index> indexList = new ArrayList<>();
    private IndexAdapter indexAdapter;
    private DailyAdapter dailyAdapter;

    public MainActivity() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findView() {
        ivSearch = findViewById(R.id.iv_search);
        tvCity = findViewById(R.id.tv_city);
        tvTemp = findViewById(R.id.tv_temp);
        tvText = findViewById(R.id.tv_text);
        tvFeelsLike = findViewById(R.id.tv_feels_like);
        tvWindScale = findViewById(R.id.tv_wind_scale);
        tvWindDir = findViewById(R.id.tv_wind_dir);
        tvHumidity = findViewById(R.id.tv_humidity);
        tvPressure = findViewById(R.id.tv_pressure);
        tvPrecip = findViewById(R.id.tv_precip);
        tvVis = findViewById(R.id.tv_vis);
        tvCloud = findViewById(R.id.tv_cloud);
        tvDew = findViewById(R.id.tv_dew);
        rvDaily = findViewById(R.id.rv_daily);
        rvIndex = findViewById(R.id.rv_index);

        backGround = findViewById(R.id.iv_background);


    }

    @Override
    protected void initView() {
        ivSearch.setOnClickListener(view ->
                startActivityForResult(new Intent(MainActivity.this,
                                ChooseCityActivity.class),
                        Common.REQ_CHOOSE_CITY));

        String cityName = SharedPrefsUtil.getString("city_name", null);
        String cityCenter = SharedPrefsUtil.getString("city_center", null);
        if (TextUtils.isEmpty(cityName) || TextUtils.isEmpty(cityCenter)) {
            startActivityForResult(new Intent(MainActivity.this,
                    ChooseCityActivity.class), Common.REQ_CHOOSE_CITY);
        } else {
            city = new City();
            city.setName(cityName);
            city.setCenter(cityCenter);
            tvCity.setText(city.getName());
            requestData();
        }

        rvDaily.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
        dailyAdapter = new DailyAdapter(dailyList);
        rvDaily.setAdapter(dailyAdapter);
        rvIndex.setLayoutManager(new LinearLayoutManager(MyApplication.getContext()));
        indexAdapter = new IndexAdapter(indexList);
        rvIndex.setAdapter(indexAdapter);

    }

    private void requestData() {
        WaitDialog.show(this, "正在获取天气信息");
        OkHttpUtil.getWithCode(this, HttpUrl.WEATHER_NOW
                        + "?location=" + city.getCenter()
                        + "&key=" + Common.WEATHER_KEY,
                200, "code", "fxLink", "now",
                new OkHttpUtil.Callback() {
                    @Override
                    public void success(String msg, String data) {
                        UIUtil.runOnUIThread(WaitDialog::dismiss);
                        try {
                            now = new Gson().fromJson(data, Now.class);
                            UIUtil.runOnUIThread(() -> setDataView());
                        } catch (Exception e) {
                            UIUtil.runOnUIThread(() -> ToastUtil.showError("数据解析异常"));
                        }
                    }

                    @Override
                    public void fail(int code, String msg) {
                        UIUtil.runOnUIThread(() -> {
                            WaitDialog.dismiss();
                            ToastUtil.showErrorLong(msg);
                        });
                    }
                });
        OkHttpUtil.getWithCode(this, HttpUrl.BING_PIC, 200, "code",
                "code", "data", new OkHttpUtil.Callback() {
                    @Override
                    public void success(String msg, String data) {
                        try {
                            UIUtil.runOnUIThread(()->Glide.with(MyApplication.
                                    getContext()).load(data).into(backGround));
                        } catch (Exception e) {
                            UIUtil.runOnUIThread(() -> ToastUtil.showErrorLong("图片解析异常"));
                        }
                    }

                    @Override
                    public void fail(int code, String msg) {
                        UIUtil.runOnUIThread(() -> ToastUtil.showErrorLong("图片加载失败"));
                    }
                });
        OkHttpUtil.getWithCode(this, HttpUrl.WEATHER_3
                        + "?location=" + city.getCenter()
                        + "&key=" + Common.WEATHER_KEY,
                200, "code", "fxLink", "daily",
                new OkHttpUtil.Callback() {
                    @Override
                    public void success(String msg, String data) {
                        UIUtil.runOnUIThread(WaitDialog::dismiss);
                        try {
                            List<Daily> list = new Gson().fromJson(data, new TypeToken<List<Daily>>() {
                            }.getType());
                            for (int i = 0; i < 3; i++) {
                                Daily daily = list.get(i);
                                switch (i) {
                                    case 0:
                                        daily.setDate("今天");
                                        break;
                                    case 1:
                                        daily.setDate("明天");
                                        break;
                                    case 2:
                                        daily.setDate("后天");
                                        break;
                                }
                            }
                            dailyList.clear();
                            dailyList.addAll(list);
                            UIUtil.runOnUIThread(() -> dailyAdapter.notifyDataSetChanged());
                        } catch (Exception e) {
                            UIUtil.runOnUIThread(() -> ToastUtil.showError("数据解析异常"));
                        }
                    }

                    @Override
                    public void fail(int code, String msg) {
                        UIUtil.runOnUIThread(() -> {
                            WaitDialog.dismiss();
                            ToastUtil.showErrorLong(msg);
                        });
                    }
                });
        OkHttpUtil.getWithCode(this, HttpUrl.WEATHER_INDEX
                        + "?location=" + city.getCenter()
                        + "&key=" + Common.WEATHER_KEY
                        + "&type=1,2,3,5",
                200, "code", "fxLink", "daily", new OkHttpUtil.Callback() {
                    @Override
                    public void success(String msg, String data) {
                        UIUtil.runOnUIThread(WaitDialog::dismiss);
                        try {
                            List<Index> list = new Gson().fromJson(data,
                                    new TypeToken<List<Index>>() {
                                    }.getType());
                            indexList.clear();
                            indexList.addAll(list);
                            UIUtil.runOnUIThread(() -> indexAdapter.notifyDataSetChanged());
                        } catch (Exception e) {
                            UIUtil.runOnUIThread(() -> ToastUtil.showError("数据解析异常"));
                        }
                    }

                    @Override
                    public void fail(int code, String msg) {
                        UIUtil.runOnUIThread(() -> {
                            WaitDialog.dismiss();
                            ToastUtil.showErrorLong(msg);
                        });
                    }
                });

    }

    @SuppressLint("SetTextI18n")
    private void setDataView() {
        if (now == null) {
            ToastUtil.showError("数据异常");
            return;
        }
        tvCity.setText(city.getName());
        tvTemp.setText(now.getTemp());
        tvText.setText(now.getText());
        tvFeelsLike.setText(now.getFeelsLike() + "℃");
        tvWindScale.setText(now.getWindScale() + "级");
        tvWindDir.setText(now.getWindDir());
        tvHumidity.setText(now.getHumidity());
        tvPressure.setText(now.getPressure());
        tvPrecip.setText(now.getPrecip());
        tvVis.setText(now.getVis());
        tvCloud.setText(now.getCloud() + "%");
        tvDew.setText(now.getDew());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Common.REQ_CHOOSE_CITY) {
            if (resultCode == RESULT_FIRST_USER) {
                if (data == null) {
                    finish();
                    return;
                }
                city = (City) data.getSerializableExtra("city");
                if (city == null) {
                    ToastUtil.showError("数据异常");
                    return;
                }
                SharedPrefsUtil.putString("city_name", city.getName());
                SharedPrefsUtil.putString("city_center", city.getCenter());
                requestData();
            } else {
                if (city == null) {
                    finish();
                }
            }
        }
    }

}