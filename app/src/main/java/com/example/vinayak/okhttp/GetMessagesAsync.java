package com.example.vinayak.okhttp;

import android.os.AsyncTask;

import com.example.vinayak.okhttp.Message;
import com.example.vinayak.okhttp.ParserUtil;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Vinayak on 10/31/2016.
 */
public class GetMessagesAsync extends AsyncTask<String,Void,ArrayList<Message>> {
    SetupData activity;

    GetMessagesAsync(SetupData activity){
        this.activity = activity;
    }

    @Override
    protected ArrayList<Message> doInBackground(String... params) {

        OkHttpClient client = new OkHttpClient();
        String token = params[0];

        Request request = new Request.Builder()
                .url("http://ec2-54-166-14-133.compute-1.amazonaws.com/api/messages")
                .header("Authorization", "BEARER " + token)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return ParserUtil.getMessagesForListView(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<Message> messages) {
        super.onPostExecute(messages);
        activity.setupData(messages);
    }

    public interface SetupData {
        void setupData(ArrayList<Message> messages);
    }
}