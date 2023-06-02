package com.example.trastbank.rest.mapper;

import android.util.Log;

import com.example.trastbank.domain.SMS;

import org.json.JSONException;
import org.json.JSONObject;

public class SMSMapper {
    public static JSONObject toJsonObject(SMS sms){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", 0);
            jsonObject.put("text", sms.getText());
            jsonObject.put("info", sms.getInfo());
        } catch (JSONException e) {
            Log.e("API_TASK_ADD_PER", e.getMessage());
        }
        return jsonObject;
    }
}
