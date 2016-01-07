package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mariosoberanis.jokes.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import sunshine.mariosoberanis.udacity.jokeandroid.JokeActivity;


/**
 * Created by mariosoberanis on 12/30/15.
 */
public class GetJokeAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private ProgressBar mProgressBar;
    private String mResult;

    public GetJokeAsyncTask(Context context, ProgressBar progressBar) {
        this.context = context;
        this.mProgressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    // .setRootUrl("http://10.0.2.2:8080/_ah/api/");
                    .setRootUrl("https://elmaso.appspot.com/_ah/api/");
            myApiService = builder.build();
        }
        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        mResult = result;
        if (mProgressBar != null){
            mProgressBar.setVisibility(View.GONE);
        }
        startJokeDisplay();

    }

    protected void startJokeDisplay() {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE, mResult);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);


    }

}