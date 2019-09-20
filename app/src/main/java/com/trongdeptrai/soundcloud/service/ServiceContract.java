package com.trongdeptrai.soundcloud.service;

import com.trongdeptrai.soundcloud.data.model.Track;

public interface ServiceContract {
    interface OnMediaPlayerChange {
        void onTrackChange(Track track);

        void onMediaPlayerStateChange(boolean isPlaying);
    }
}
