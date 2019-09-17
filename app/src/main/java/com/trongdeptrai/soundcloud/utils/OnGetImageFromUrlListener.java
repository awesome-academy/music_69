package com.trongdeptrai.soundcloud.utils;

import android.graphics.Bitmap;

public interface OnGetImageFromUrlListener {
    void onGetImageSucceed(Bitmap bitmap);
    void onGetImageFailed();
}
