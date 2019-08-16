package com.lz.library.component;

import android.content.Context;
import android.widget.ImageView;

import com.lz.library.R;
import com.lz.library.config.GlideApp;


public class ImageLoader {

    private static ImageLoader mInstance;

    //单例模式，节省资源
    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    public void withPlaceholder(Context context, String imageUrl, int placeholder, ImageView imageView) {
        GlideApp.with(context).load(imageUrl).placeholder(placeholder).into(imageView);
    }

    public void with(Context context, String imageUrl, ImageView imageView) {
        GlideApp.with(context).load(imageUrl).placeholder(R.mipmap.ic_launcher).into(imageView);
    }


}
