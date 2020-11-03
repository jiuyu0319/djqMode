package com.mode.djq.net;

import android.app.Activity;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.mode.djq.net.upapp.listener.OkGoNetCallBack;
import com.mode.djq.net.upapp.manage.DownloadManager;


import static com.lzy.okgo.cache.CacheMode.NO_CACHE;

public class OKGO {


    public OKGO() {
    }
    public void Get(String netUrl, Context context,CacheMode cacheMode , OkGoNetCallBack okGoNetCallBack){
        OkGo.<String>get(netUrl).tag(context).cacheMode(cacheMode)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        okGoNetCallBack.onSuccess(body);
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        okGoNetCallBack.onError(response.getException());

                    }
                });
    }
    public void Get(String netUrl, Context context , OkGoNetCallBack okGoNetCallBack){
        Get(netUrl,context, NO_CACHE,okGoNetCallBack);
    }

    public void Post(String netUrl, Context context, HttpParams params , OkGoNetCallBack okGoNetCallBack){
        Post(netUrl,context, NO_CACHE,params,okGoNetCallBack);
    }
    public void Post(String netUrl, Context context, CacheMode cacheMode , HttpParams params, OkGoNetCallBack okGoNetCallBack){
        OkGo.<String>get(netUrl).tag(context).params(params).cacheMode(cacheMode)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        okGoNetCallBack.onSuccess(body);
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        okGoNetCallBack.onError(response.getException());

                    }
                });
    }

    public void LoadApk(String apkurl,int iconid,Activity activity){
        DownloadManager manager = DownloadManager.getInstance(activity);
        manager.setApkName("appupdate.apk")
                .setApkUrl(apkurl)
                .setSmallIcon(iconid)
                .download();
    }
    public void LoadApk(String apkUrl,String apkName,int iconId,Activity activity){
        DownloadManager manager = DownloadManager.getInstance(activity);
        manager.setApkName(apkName)
                .setApkUrl(apkUrl)
                .setSmallIcon(iconId)
                .download();
    }
}
