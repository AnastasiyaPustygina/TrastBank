package com.example.trastbank.rest;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.trastbank.domain.SMS;
import com.example.trastbank.rest.mapper.SMSMapper;

import org.json.JSONObject;

public class AppApiVolley implements AppApi{

    private final String BASE_URL = "http://192.168.56.1:8080";
    private final Context context;

    public AppApiVolley(Context context) {
        this.context = context;
    }

    @Override
    public void insertSMS(SMS sms, long userId) {
        String url = BASE_URL + "/sms/?user_id=" + userId;

        JSONObject params = SMSMapper.toJsonObject(sms);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("API_ADD_SMS", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("API_ADD_SMS", error.toString());
            }
        }
        );

        RequestQueue referenceQueue = Volley.newRequestQueue(context);
        referenceQueue.add(jsonObjectRequest);
    }
}
