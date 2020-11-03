package com.mode.djq.net.utils;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 项目名:    AppUpdate
 * 文件名:    Httputil
 * 描述:     TODO
 */

public class HttpUtil {
    private final static String API = "http://azhong.tk:8081/api/";

    /**
     * 上报信息
     */
    public static void postUsage(Context context) {
        if (context == null) return;
        try {
            JSONObject params = new JSONObject();
            params.put("applicationId", context.getPackageName());
            params.put("appName", ApkUtil.getAppName(context));
            params.put("versionCode", ApkUtil.getVersionCode(context));
            params.put("versionName", ApkUtil.getVersionName(context));
            params.put("appUpdateVersion", "3.0.2");
            params.put("time", yyyyMMddHHmmss());
            post("usage/add", params.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 上报信息
     */
    public static void postException(Context context, String url, String exceptionTitle, String exception) {
        if (context == null) return;
        try {
            JSONObject params = new JSONObject();
            params.put("applicationId", context.getPackageName());
            params.put("appName", ApkUtil.getAppName(context));
            params.put("versionCode", ApkUtil.getVersionCode(context));
            params.put("versionName", ApkUtil.getVersionName(context));
            params.put("appUpdateVersion", "3.0.2");
            params.put("notification", NotificationUtil.notificationEnable(context) ? 1 : 0);
            params.put("apkUrl", url);
            params.put("title", exceptionTitle);
            params.put("message", exception);
            params.put("time", yyyyMMddHHmmss());
            post("exception/add", params.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void post(final String path, final String body) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(API + path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    connection.connect();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                    writer.write(body);
                    writer.close();
                    int responseCode = connection.getResponseCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private static String yyyyMMddHHmmss() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }
}
