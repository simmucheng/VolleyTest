package com.example.simmucheng.volleytest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private NetworkImageView networkImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();
//        volleyGet_RequestString();
//        volleyPost_RequestString();
//        volleyGet_JsonObject();
//        volleyPost_JsonObject();
//        volley_ImageRequest();
//        volley_ImageLoader();
        volley_NetworkImageView();
//        volleyImg();

    }

    private void volley_NetworkImageView() {
        String url="http://pic20.nipic.com/20120409/9188247_091601398179_2.jpg";
        ImageLoader loader=new ImageLoader(MyQueue.getHttpQueues(), new ImageCacheTest());
        networkImageView.setDefaultImageResId(R.drawable.ic_launcher);
        networkImageView.setErrorImageResId(R.drawable.ic_launcher);
        networkImageView.setImageUrl(url,loader);
    }

    private void InitView() {
        image= (ImageView) findViewById(R.id.image);
        networkImageView= (NetworkImageView) findViewById(R.id.network_imageview);
    }

    private void volley_ImageRequest() {
        String url="http://pic20.nipic.com/20120409/9188247_091601398179_2.jpg";
        ImageRequest imgrequest=new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                image.setImageBitmap(bitmap);
            }
            //0，0表示不进行压缩，Config.RGB_565表示每个像素占2个字节，Config.ARGB_8888表示每个像素占4个字节等。
            //第三第四个参数(maxWidth,maxHeight)指定允许图片最大的宽度和高度，如果指定的网络图片的宽度或高度大于这里的最大值
            // ，则会对图片进行压缩，指定为0即表示不管图片多大都不压缩。

        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                image.setImageResource(R.drawable.ic_launcher);
            }
        });
        MyQueue.getHttpQueues().add(imgrequest);
    }

    private void volley_ImageLoader() {
        String url="http://pic20.nipic.com/20120409/9188247_091601398179_2.jpg";
        //ImageCacheTest需要实现ImageCache引用，并实现其中的两个方法
        //ImageCache是内存缓存对象，也叫L1缓存，L2缓存是磁盘缓存，内存缓存就是将展示过的图片放入内存进行缓存，
        // 磁盘缓存就是在没有网络的时候调出来使用
        ImageLoader loader=new ImageLoader(MyQueue.getHttpQueues(),new ImageCacheTest());
        //三个参数分别为控件的id，默认的图片，发生错误时展示的图片
        ImageLoader.ImageListener listener=ImageLoader.getImageListener(image,R.drawable.ic_launcher,R.drawable.ic_launcher);
        loader.get(url,listener);
    }

    private void volleyGet_JsonObject() {
        String url="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.4.255.255";
        JsonObjectRequest jsrequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Toast.makeText(MainActivity.this,jsonObject.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this,volleyError.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        jsrequest.setTag("TestTag");
        MyQueue.getHttpQueues().add(jsrequest);
    }

    private void volleyPost_JsonObject() {
        String url="http://apis.juhe.cn/mobile/get?";
        Map<String,String>map=new HashMap<>();
        map.put("phone","13429667914");
        map.put("key", "335adcc4e891ba4e4be6d7534fd54c5d");
        JSONObject jsobject=new JSONObject(map);
        JsonObjectRequest jsrequest=new JsonObjectRequest(Request.Method.POST, url, jsobject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Toast.makeText(MainActivity.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this,volleyError.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        jsrequest.setTag("TestTag");
        MyQueue.getHttpQueues().add(jsrequest);
    }

    private void volleyPost_RequestString() {
        String url1="https://tcc.taobao.com/cc/json/mobile_tel_segment.htm";
        StringRequest request=new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this,volleyError.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map=new HashMap<String, String>();
                map.put("tel","15850781443");
                return map;
            }
        };
        request.setTag("TestTag");
        MyQueue.getHttpQueues().add(request);
    }

    private void volleyImg() {
    }

    private void volleyGet_RequestString() {
        String url="https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=15850781443";
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this,volleyError.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        request.setTag("TestTag");
        MyQueue.getHttpQueues().add(request);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyQueue.getHttpQueues().cancelAll("TestTag");
    }
}
