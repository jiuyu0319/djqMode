package com.mode.djq.net.utils;


import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 项目名:    AppUpdate
 * 文件名:    ScreenUtil
 * 描述:     TODO 获取屏幕 宽 高
 *
 */


public final class ScreenUtil {

    /**
     * 获取屏幕宽度（像素）
     *
     * @param context 上下文
     * @return px
     */
    public static int getWith(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度（像素）
     *
     * @param context 上下文
     * @return px
     */
    public static int getHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
}
