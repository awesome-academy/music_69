package com.trongdeptrai.soundcloud.data.source.remote;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import com.trongdeptrai.soundcloud.utils.OnGetImageFromUrlListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private OnGetImageFromUrlListener mListener;
    private Exception mException;

    public GetImageAsyncTask(OnGetImageFromUrlListener listener) {
        mListener = listener;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        return loadImageUrl(strings[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap == null) {
            mListener.onGetImageFailed();
        }
        mListener.onGetImageSucceed(bitmap);
    }

    private Bitmap loadImageUrl(String url) {
        try {
            URL urlAPI = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlAPI.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            mException = e;
            e.printStackTrace();
            return null;
        }
    }
}
