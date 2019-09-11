package com.trongdeptrai.soundcloud.utils;

public class Common {
    private static final String DEFAULT_IMAGE_FORMAT = "-large";
    private static final String BIG_IMAGE_FORMAT = "-t300x300";

    public static String getBigImageUrl(String url) {
        return url != null ? url.replace(DEFAULT_IMAGE_FORMAT, BIG_IMAGE_FORMAT) : "";
    }
}
