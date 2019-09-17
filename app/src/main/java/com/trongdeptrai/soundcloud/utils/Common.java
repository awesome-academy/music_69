package com.trongdeptrai.soundcloud.utils;

import com.trongdeptrai.soundcloud.data.source.remote.GetImageAsyncTask;

public class Common {
    private static final String DEFAULT_IMAGE_FORMAT = "-large";
    private static final String BIG_IMAGE_FORMAT = "-t300x300";

    public static String getBigImageUrl(String url) {
        return url != null ? url.replace(DEFAULT_IMAGE_FORMAT, BIG_IMAGE_FORMAT) : "";
    }

    public static void getImageUrl(String url, OnGetImageFromUrlListener listener){
        new GetImageAsyncTask(listener).execute(url);
    }
}
