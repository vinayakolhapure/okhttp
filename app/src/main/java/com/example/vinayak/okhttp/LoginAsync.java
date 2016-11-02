package com.example.vinayak.okhttp;

import android.os.AsyncTask;

import com.example.vinayak.okhttp.com.example.vinayak.okhttp.model.LoginUser;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Vinayak on 11/1/2016.
 */
public class LoginAsync extends AsyncTask<String,Void,LoginUser> {

    String url;
    ISetupData activity;

    LoginAsync(ISetupData activity){
        this.url = "http://ec2-54-166-14-133.compute-1.amazonaws.com/api/login";
        this.activity = activity;
    }
    @Override
    protected LoginUser doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", params[0])
                .addFormDataPart("password", params[1])
                .build();

        Request request = new Request.Builder()
                .url(url)
                .method("POST", RequestBody.create(null, new byte[0]))
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return ParserUtil.parseUserLogin(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(LoginUser loginUser) {
        super.onPostExecute(loginUser);
        activity.setupData(loginUser);
    }

    public interface ISetupData {
        void setupData(LoginUser user);
    }
}
