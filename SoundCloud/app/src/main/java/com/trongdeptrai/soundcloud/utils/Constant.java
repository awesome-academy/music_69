package com.trongdeptrai.soundcloud.utils;

import com.trongdeptrai.soundcloud.BuildConfig;

public class Constant {
    public static final int REQUEST_TIMEOUT = 90000;
    public static final String GET_METHOD = "GET";
    public static final String COLLECTION = "collection";
    public static final String USER = "user";
    private static final String BASE_URL = "https://api-v2.soundcloud.com/";
    private static final String CHART = "charts?";
    private static final String KIND = "kind=top";
    public static final String GENRE = "&genre=soundcloud%3Agenres%3A";
    private static final String LIMIT = "&limit=";
    public static final String CLIENT_ID = "&client_id=" ;//+  BuildConfig.API_KEY;
    private static final int TRACK_LIMIT = 10;
    public static final String TRENDING_MUSIC_URL =
            BASE_URL + CHART + KIND + LIMIT + TRACK_LIMIT + CLIENT_ID;
    public static final String QUERY = "q=";
    public static final String SEARCH_TRACK_URL = BASE_URL + "search/tracks?";
    public static final String STREAM_BASE_URL = "https://api.soundcloud.com/tracks/";
    public static final String STREAM = "/stream?" + CLIENT_ID;
}
