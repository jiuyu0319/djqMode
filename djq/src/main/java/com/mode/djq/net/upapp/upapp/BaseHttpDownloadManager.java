package com.mode.djq.net.upapp.upapp;

import com.mode.djq.net.upapp.listener.OnDownloadListener;

public abstract class BaseHttpDownloadManager {

    /**
     * 下载apk
     *
     * @param apkUrl   apk下载地址
     * @param apkName  apk名字
     * @param listener 回调
     */
    public abstract void download(String apkUrl, String apkName, OnDownloadListener listener);

    /**
     * 取消下载apk
     */
    public abstract void cancel();

    /**
     * 释放资源
     */
    public abstract void release();
}