package com.nyxwolves.wannabuy.RestApiHelper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class CustomRequestQueue {

    private static CustomRequestQueue instance;
    private RequestQueue requestQueue;
    private Context ctx;

    private CustomRequestQueue(Context ctx) {
        this.ctx = ctx;
    }

    public static synchronized CustomRequestQueue getInstance(Context ctx) {
        if (instance == null) {
            instance = new CustomRequestQueue(ctx);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public void addRequest(Request request){
        getRequestQueue().add(request);
    }

}
