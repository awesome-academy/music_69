package com.trongdeptrai.soundcloud.data.source.remote;

import android.os.AsyncTask;
import com.trongdeptrai.soundcloud.data.model.Track;
import com.trongdeptrai.soundcloud.utils.Constant;
import com.trongdeptrai.soundcloud.utils.QueryType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.trongdeptrai.soundcloud.utils.Constant.COLLECTION;
import static com.trongdeptrai.soundcloud.utils.Constant.GET_METHOD;
import static com.trongdeptrai.soundcloud.utils.Constant.REQUEST_TIMEOUT;
import static com.trongdeptrai.soundcloud.utils.Constant.USER;

public class GetTracksAysncTask extends AsyncTask<String, Void, List<Track>> {
    private OnFetchDataListener<Track> mListener;
    private Exception mException;
    private int mQueryType;

    public GetTracksAysncTask(OnFetchDataListener<Track> listener, int queryType) {
        mListener = listener;
        mQueryType = queryType;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Track> doInBackground(String... strings) {
        List<Track> tracks = null;
        try {
            String json = getDataFromUrl(strings[0]);
            if (mQueryType == QueryType.GET_TRACKS) {
                tracks = readTrackDataFromJson(json);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            mException = e;
        }
        return tracks;
    }

    @Override
    protected void onPostExecute(List<Track> tracks) {
        super.onPostExecute(tracks);
        if (mException != null) {
            mListener.onFailed(mException);
        }
        mListener.onSucceed(tracks);
    }

    private String getDataFromUrl(String url) throws IOException {
        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod(GET_METHOD);
        connection.setReadTimeout(REQUEST_TIMEOUT);
        connection.setConnectTimeout(REQUEST_TIMEOUT);
        connection.connect();

        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }
        connection.disconnect();
        return builder.toString();
    }

    private List<Track> readTrackDataFromJson(String json) throws JSONException {
        List<Track> tracks = new ArrayList<>();
        JSONObject rootObject = new JSONObject(json);
        JSONArray jsonArray = rootObject.getJSONArray(COLLECTION);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i).getJSONObject(Track.TrackEntry.TRACK);
            Track track = new Track.TrackBuilder().setId(object.getLong(Track.TrackEntry.ID))
                    .setTitle(object.getString(Track.TrackEntry.TITLE))
                    .setArtworkUrl(object.getString(Track.TrackEntry.ARTWORK_URL))
                    .setArtistName(
                            object.getJSONObject(USER).getString(Track.TrackEntry.ARTIST_NAME))
                    .setAvatarUrl(object.getJSONObject(USER).getString(Track.TrackEntry.AVATAR_URL))
                    .setDownloadable(object.getBoolean(Track.TrackEntry.DOWNLOADABLE))
                    .setDownloadUrl(object.getString(Track.TrackEntry.DOWNLOAD_URL))
                    .setDuration(object.getInt(Track.TrackEntry.DURATION))
                    .setStreamUrl(Constant.STREAM_BASE_URL
                            + object.getLong(Track.TrackEntry.ID)
                            + Constant.STREAM)
                    .build();
            tracks.add(track);
        }
        return tracks;
    }
}
