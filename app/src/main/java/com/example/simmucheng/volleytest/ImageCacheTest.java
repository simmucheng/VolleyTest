package com.example.simmucheng.volleytest;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by simmucheng on 16/7/18.
 */
public class ImageCacheTest implements ImageLoader.ImageCache{
    private LruCache<String,Bitmap>lruCacheMap;
    private int max=10*1024*1024;//ImageCache需要分配的缓存大小
    public ImageCacheTest() {
        lruCacheMap=new LruCache<String, Bitmap>(max){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //返回bitmap（每张图）的大小
                return value.getRowBytes()*value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String s) {
        return lruCacheMap.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        if(getBitmap(s)==null)
        lruCacheMap.put(s,bitmap);
    }
}
