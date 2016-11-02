package com.example.vinayak.okhttp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vinayak on 10/31/2016.
 */
public class Message {
    String messageText;
    Date chatDate;
    String fname;
    String lname;
    String thumbNail;

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(String thumbNail) {
        this.thumbNail = thumbNail;
    }

    public Date getChatDate() {
        return chatDate;
    }

    public void setChatDate(Date chatDate) {
        this.chatDate = chatDate;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public static Message getMessageFromJSON(JSONObject json) throws JSONException, ParseException {
        Message msg = new Message();
        msg.setFname(json.getString("UserFname"));
        msg.setLname(json.getString("UserLname"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        msg.setChatDate(format.parse(json.getString("CreatedAt")));
        msg.setMessageText(json.getString("Comment"));
        Log.d("message",msg.getMessageText());
        msg.setThumbNail(json.getString("FileThumbnailId"));
        return msg;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageText='" + messageText + '\'' +
                ", chatDate=" + chatDate +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", thumbNail='" + thumbNail + '\'' +
                '}';
    }
}
