package com.example.simmucheng.volleytest;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by simmucheng on 16/7/18.
 */
public class MyQueue extends Application{
    private static RequestQueue queues;
    @Override
    public void onCreate() {
        super.onCreate();
        queues=Volley.newRequestQueue(getApplicationContext());
    }
    public static RequestQueue getHttpQueues(){
        return queues;
    }
}
