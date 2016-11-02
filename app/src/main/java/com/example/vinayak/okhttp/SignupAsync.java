package com.example.vinayak.okhttp;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Vinayak on 11/1/2016.
 */
public class SignupAsync extends AsyncTask<String,Void,String> {

    String url;
    ISetupData activity;

    SignupAsync(ISetupData activity){
        this.url = "http://ec2-54-166-14-133.compute-1.amazonaws.com/api/signup";
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", params[0])
                .addFormDataPart("password", params[1])
                .addFormDataPart("fname", params[2])
                .addFormDataPart("lname", params[3])
                .build();

        Request request = new Request.Builder()
                .url(url)
                .method("POST", RequestBody.create(null, new byte[0]))
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return ParserUtil.getTokenForSignup(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        activity.setupData(s);
    }

    public interface ISetupData {
        void setupData(String token);
    }
}
