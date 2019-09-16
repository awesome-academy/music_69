package com.trongdeptrai.soundcloud.data.source.remote;

import java.util.List;

public interface OnFetchDataListener<T> {
    void onSucceed(List<T> data);

    void onFailed(Exception e);
}
