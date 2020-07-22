package com.weather.lite.util;

import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtil {

    private static final String TAG = "OkHttpUtil";

    private static final MediaType TYPE_JSON = MediaType.parse("application/json;charset=utf-8");

    private static final int READ_TIMEOUT = 60;
    private static final int WEITE_TIMEOUT = 60;
    private static final int CONNECT_TIMEOUT = 60;
    private static final int CALL_TIMEOUT = 60;

    private static OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WEITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .callTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
            .build();

    public static void post(FragmentActivity activity, final String url, HashMap<String, ?> bodyMap,
                            final Callback callback) {
        RequestBody requestBody = RequestBody.create(new Gson().toJson(bodyMap), TYPE_JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                    return;
                }
                String es = e.toString();
                if (es.contains("Canceled") || es.contains("closed")) {
                    return;
                }
                callback.fail(Common.CODE_NET, "网络连接异常");
                LogUtil.loge(TAG, "POST " + url);
                LogUtil.loge(TAG, e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                    return;
                }
                try {
                    String body = Objects.requireNonNull(response.body()).string();
                    LogUtil.logd(TAG, "POST " + url);
                    LogUtil.logd(TAG, body);
                    JSONObject bodyJSON = new JSONObject(body);
                    int code = bodyJSON.getInt(Common.DATA_CODE);
                    String msg = bodyJSON.getString(Common.DATA_MSG);
                    if (code == Common.CODE_SUCCESS) {
                        String data = bodyJSON.getString(Common.DATA_DATA);
                        callback.success(msg, data);
                        LogUtil.logd(TAG, "POST " + url);
                        LogUtil.logd(TAG, data);
                    } else {
                        callback.fail(code, msg);
                        LogUtil.loge(TAG, "POST " + url);
                        LogUtil.loge(TAG, "code: " + code + "  msg: " + msg);
                    }
                } catch (Exception e) {
                    callback.fail(Common.CODE_DATA, "数据解析异常");
                    LogUtil.loge(TAG, "POST " + url);
                    LogUtil.loge(TAG, e.toString());
                }
            }
        });
        LogUtil.logd(TAG, "POST " + url);
        LogUtil.logd(TAG, bodyMap.toString());
    }

    public static void delete(FragmentActivity activity, final String url, HashMap<String, ?> bodyMap,
                              final Callback callback) {
        RequestBody requestBody = RequestBody.create(new Gson().toJson(bodyMap), TYPE_JSON);
        Request request = new Request.Builder()
                .url(url)
                .delete(requestBody)
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                    return;
                }
                String es = e.toString();
                if (es.contains("Canceled") || es.contains("closed")) {
                    return;
                }
                callback.fail(Common.CODE_NET, "网络连接异常");
                LogUtil.loge(TAG, "DELETE " + url);
                LogUtil.loge(TAG, e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                    return;
                }
                try {
                    String body = Objects.requireNonNull(response.body()).string();
                    LogUtil.logd(TAG, "DELETE " + url);
                    LogUtil.logd(TAG, body);
                    JSONObject bodyJSON = new JSONObject(body);
                    int code = bodyJSON.getInt(Common.DATA_CODE);
                    String msg = bodyJSON.getString(Common.DATA_MSG);
                    if (code == Common.CODE_SUCCESS) {
                        String data = bodyJSON.getString(Common.DATA_DATA);
                        callback.success(msg, data);
                        LogUtil.logd(TAG, "DELETE " + url);
                        LogUtil.logd(TAG, data);
                    } else {
                        callback.fail(code, msg);
                        LogUtil.loge(TAG, "DELETE " + url);
                        LogUtil.loge(TAG, "code: " + code + "  msg: " + msg);
                    }
                } catch (Exception e) {
                    callback.fail(Common.CODE_DATA, "数据解析异常");
                    LogUtil.loge(TAG, "DELETE " + url);
                    LogUtil.loge(TAG, e.toString());
                }
            }
        });
        LogUtil.logd(TAG, "DELETE " + url);
        LogUtil.logd(TAG, bodyMap.toString());
    }

    public static void put(FragmentActivity activity, final String url, HashMap<String, ?> bodyMap,
                           final Callback callback) {
        RequestBody requestBody = RequestBody.create(new Gson().toJson(bodyMap), TYPE_JSON);
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody)
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                    return;
                }
                String es = e.toString();
                if (es.contains("Canceled") || es.contains("closed")) {
                    return;
                }
                callback.fail(Common.CODE_NET, "网络连接异常");
                LogUtil.loge(TAG, "PUT " + url);
                LogUtil.loge(TAG, e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                    return;
                }
                try {
                    String body = Objects.requireNonNull(response.body()).string();
                    LogUtil.logd(TAG, "PUT " + url);
                    LogUtil.logd(TAG, body);
                    JSONObject bodyJSON = new JSONObject(body);
                    int code = bodyJSON.getInt(Common.DATA_CODE);
                    String msg = bodyJSON.getString(Common.DATA_MSG);
                    if (code == Common.CODE_SUCCESS) {
                        String data = bodyJSON.getString(Common.DATA_DATA);
                        callback.success(msg, data);
                        LogUtil.logd(TAG, "PUT " + url);
                        LogUtil.logd(TAG, data);
                    } else {
                        callback.fail(code, msg);
                        LogUtil.loge(TAG, "PUT " + url);
                        LogUtil.loge(TAG, "code: " + code + "  msg: " + msg);
                    }
                } catch (Exception e) {
                    callback.fail(Common.CODE_DATA, "数据解析异常");
                    LogUtil.loge(TAG, "PUT " + url);
                    LogUtil.loge(TAG, e.toString());
                }
            }
        });
        LogUtil.logd(TAG, "PUT " + url);
        LogUtil.logd(TAG, bodyMap.toString());
    }

    public static void get(FragmentActivity activity, final String url, final Callback callback) {
        getWithCode(activity, url, Common.CODE_SUCCESS, Common.DATA_CODE, Common.DATA_MSG, Common.DATA_DATA, callback);
    }

    public static void getForAMap(FragmentActivity activity, final String url, final Callback callback)
    {
        getWithCode(activity, url, 1, "status", "info", "regeocode", callback);
    }

    public static void getWithCode(FragmentActivity activity, final String url, final int successCode,
                                   final String codeName, final String msgName, final String dataName,
                                   final Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                    return;
                }
                String es = e.toString();
                if (es.contains("Canceled") || es.contains("closed")) {
                    return;
                }
                callback.fail(Common.CODE_NET, "网络连接异常");
                LogUtil.loge(TAG, "GET " + url);
                LogUtil.loge(TAG, e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                    return;
                }
                try {
                    String body = Objects.requireNonNull(response.body()).string();
                    LogUtil.logd(TAG, "GET " + url);
                    LogUtil.logd(TAG, body);
                    JSONObject bodyJSON = new JSONObject(body);
                    int code = bodyJSON.getInt(codeName);
                    String msg = bodyJSON.getString(msgName);
                    if (code == successCode) {
                        String data = bodyJSON.getString(dataName);
                        callback.success(msg, data);
                        LogUtil.logd(TAG, "GET " + url);
                        LogUtil.logd(TAG, data);
                    } else {
                        callback.fail(code, msg);
                        LogUtil.loge(TAG, "GET " + url);
                        LogUtil.loge(TAG, "code: " + code + "  msg: " + msg);
                    }
                } catch (Exception e) {
                    callback.fail(Common.CODE_DATA, "数据解析异常");
                    LogUtil.loge(TAG, "GET " + url);
                    LogUtil.loge(TAG, e.toString());
                }
            }
        });
        LogUtil.logd(TAG, "GET " + url);
    }

    public interface Callback {
        void success(String msg, String data);

        void fail(int code, String msg);
    }

    public static void download(FragmentActivity activity, final String url,
                                final String fileName, final String pathName,
                                final DownloadListener listener) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                    return;
                }
                String es = e.toString();
                if (es.contains("Canceled") || es.contains("closed")) {
                    return;
                }
                listener.onFail("网络连接异常");
                LogUtil.loge(TAG, "DOWNLOAD " + url);
                LogUtil.loge(TAG, e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                    return;
                }
                InputStream is = null;
                FileOutputStream fos = null;
                try {
                    byte[] buf = new byte[2048];
                    int len;
                    String filePath = getFilePathByName(pathName);
                    is = Objects.requireNonNull(response.body()).byteStream();
                    long total = Objects.requireNonNull(response.body()).contentLength();
                    File file = new File(filePath, fileName);
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        listener.onProgress(progress);
                        LogUtil.logd(TAG, "DOWNLOADING " + fileName + "  " + progress + " %");
                    }
                    fos.flush();
                    listener.onSuccess(file.getPath());
                    LogUtil.logd(TAG, "DOWNLOAD " + fileName + " 下载成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onFail("数据解析异常");
                    LogUtil.loge(TAG, "DOWNLOAD " + fileName + " 下载失败 数据解析异常  " + e.toString());
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        LogUtil.loge(TAG, "DOWNLOAD " + fileName + " 输入/输出流关闭异常  " + e.toString());
                    }
                }
            }
        });
        LogUtil.logd(TAG, "DOWNLOAD " + url);
        LogUtil.logd(TAG, "文件名: " + fileName + "  路径名: " + pathName);
    }

    public interface DownloadListener {

        void onSuccess(String filePath);

        void onProgress(int progress);

        void onFail(String msg);

    }

    private static String getFilePathByName(String pathName) throws Exception {
        String filePath = "";
        File downloadFile = new File(Environment.getExternalStorageDirectory(), "\\" + pathName + "\\");
        if (downloadFile.mkdirs()) {
            filePath = downloadFile.getAbsolutePath();
        } else {
            if (downloadFile.createNewFile()) {
                filePath = downloadFile.getAbsolutePath();
            }
        }
        return filePath;
    }

    public static void upload(FragmentActivity activity, final String url, List<String> filePathList,
                              boolean isMulti, final Callback callback) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (!isMulti) {
            File file = new File(filePathList.get(0));
            builder.addFormDataPart("file",
                    file.getName(),
                    RequestBody.create(file, MediaType.parse("multipart/form-data")));
        } else {
            for (int i = 0; i < filePathList.size(); i++) {
                File file = new File(filePathList.get(i));
                builder.addFormDataPart("files[" + i + "]",
                        file.getName(),
                        RequestBody.create(file, MediaType.parse("multipart/form-data")));
            }
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Client-ID " + UUID.randomUUID())
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                    return;
                }
                String es = e.toString();
                if (es.contains("Canceled") || es.contains("closed")) {
                    return;
                }
                callback.fail(Common.CODE_NET, "网络连接异常");
                LogUtil.loge(TAG, "UPLOAD " + url);
                LogUtil.loge(TAG, e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                    return;
                }
                try {
                    String body = Objects.requireNonNull(response.body()).string();
                    LogUtil.logd(TAG, "UPLOAD " + url);
                    LogUtil.logd(TAG, body);
                    JSONObject bodyJSON = new JSONObject(body);
                    int code = bodyJSON.getInt(Common.DATA_CODE);
                    String msg = bodyJSON.getString(Common.DATA_MSG);
                    if (code == Common.CODE_SUCCESS) {
                        String data = bodyJSON.getString(Common.DATA_DATA);
                        callback.success(msg, data);
                        LogUtil.logd(TAG, "UPLOAD " + url);
                        LogUtil.logd(TAG, data);
                    } else {
                        callback.fail(code, msg);
                        LogUtil.loge(TAG, "UPLOAD " + url);
                        LogUtil.loge(TAG, "code: " + code + "  msg: " + msg);
                    }
                } catch (Exception e) {
                    callback.fail(Common.CODE_DATA, "数据解析异常");
                    LogUtil.loge(TAG, "UPLOAD " + url);
                    LogUtil.loge(TAG, e.toString());
                }
            }
        });
        LogUtil.logd(TAG, "UPLOAD " + url);
        LogUtil.logd(TAG, "上传文件: " + filePathList.toString());
    }

}