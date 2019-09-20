package com.trongdeptrai.soundcloud.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.utils.LoopType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrackService extends Service
        implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    private final static String EXTRA_TRACK_LIST = "EXTRA_TRACK_LIST";
    private final static String EXTRA_TRACK_POSITION = "EXTRA_TRACK_POSITION";
    private final int DEFAULT_POSITION = 0;
    private final int DEFAULT_VALUE_ONE = 1;
    private List<Track> mTracks;
    private int mPosition;
    private MediaPlayer mMediaPlayer;
    private ServiceContract.OnMediaPlayerChange mOnMediaPlayerChange;
    private TrackBinder mTrackBinder;
    private int mLoopType;

    public static Intent getServiceIntent(Context context, List<Track> tracks, int position) {
        Intent intent = new Intent(context, TrackService.class);
        intent.putParcelableArrayListExtra(EXTRA_TRACK_LIST,
                (ArrayList<? extends Parcelable>) tracks);
        intent.putExtra(EXTRA_TRACK_POSITION, position);
        return intent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mTrackBinder = new TrackBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            List<Track> tracks = intent.getParcelableArrayListExtra(EXTRA_TRACK_LIST);
            if (tracks != null) {
                mPosition = intent.getIntExtra(EXTRA_TRACK_POSITION, DEFAULT_POSITION);
                mTracks = tracks;
                mLoopType = LoopType.LOOP_ALL;
                play();
            }
        }
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mTrackBinder;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (mOnMediaPlayerChange != null) {
            mOnMediaPlayerChange.onMediaPlayerStateChange(false);
        }
        switch (mLoopType) {
            case LoopType.LOOP_ALL:
                skipNext();
                break;
            case LoopType.LOOP_ONE:
                play();
                break;
            case LoopType.NO_LOOP:
                if (mPosition != mTracks.size() - DEFAULT_VALUE_ONE) {
                    skipNext();
                }
                break;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(this);
    }

    public void setOnMediaPlayerChange(ServiceContract.OnMediaPlayerChange onMediaPlayerChange) {
        mOnMediaPlayerChange = onMediaPlayerChange;
    }

    public void play() {
        Track track = mTracks.get(mPosition);
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mMediaPlayer.setDataSource(track.getStreamUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.prepareAsync();
        mMediaPlayer.setOnPreparedListener(this);
        if (mOnMediaPlayerChange != null) {
            mOnMediaPlayerChange.onTrackChange(track);
        }
    }

    public void skipNext() {
        mPosition++;
        if (mPosition == mTracks.size()) {
            mPosition = DEFAULT_POSITION;
        }
        play();
    }

    public class TrackBinder extends Binder {
        public TrackService getService() {
            return TrackService.this;
        }
    }
}
