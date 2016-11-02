package com.example.vinayak.okhttp;

import android.os.*;

import com.example.vinayak.okhttp.com.example.vinayak.okhttp.model.LoginUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Vinayak on 10/31/2016.
 */
public class ParserUtil {

    public static String getTokenForSignup(String response) throws JSONException {
        JSONObject root = new JSONObject(response);
        String statusFromJSON = root.getString("status");
        String token = null;
        if(statusFromJSON.equals("ok"))
            token = root.getString("token");
        else
            token = null;

        return token;
    }

    public static LoginUser parseUserLogin(String result) throws JSONException, ParseException {
        JSONObject root = new JSONObject(result);
        if(root.getString("status").equals("ok")){
            LoginUser loginUser = new LoginUser();
            loginUser.setToken(root.getString("token"));
            loginUser.setUserEmail(root.getString("userEmail"));
            loginUser.setUserID(root.getString("userId"));
            loginUser.setUserFname(root.getString("userFname"));
            loginUser.setUserLname(root.getString("userLname"));
            loginUser.setUserRole(root.getString("userRole"));
            return loginUser;
        }
        return null;
    }

    public static ArrayList<Message> getMessagesForListView(String response) throws JSONException, ParseException {
        JSONObject root = new JSONObject(response);
        JSONArray messagesArray = root.getJSONArray("messages");
        ArrayList<Message> messages = new ArrayList<Message>();
        for(int i = 0; i<messagesArray.length(); i++) {
            Message msg = new Message();
            JSONObject arrayObj = new JSONObject(String.valueOf(messagesArray.getJSONObject(i)));
            msg = Message.getMessageFromJSON(arrayObj);
            messages.add(msg);
        }
        return messages;
    }
}
