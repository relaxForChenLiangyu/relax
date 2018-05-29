package com.example.cynthia.relax.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class AsyncBitmapLoader {
    /**
     * 内存图片软引用缓冲
     */
    private HashMap<String, SoftReference<Bitmap>> imageCache = null;

    public static AsyncBitmapLoader asyncBitmapLoader = new AsyncBitmapLoader();

    public AsyncBitmapLoader() {
        imageCache = new HashMap<String, SoftReference<Bitmap>>();
    }

    public Bitmap loadBitmap(final ImageView imageView, final String imageURL, final int reqWidth, final int reqHeight, final ImageCallBack imageCallBack) {
        if (imageURL == null || imageURL.equals("")) {
            return null;
        }
        final String path = Environment.getExternalStorageDirectory().getPath() + "/quickcanteen/";

        //在内存缓存中，则返回Bitmap对象    
        if (imageCache.containsKey(imageURL)) {
            SoftReference<Bitmap> reference = imageCache.get(imageURL);
            Bitmap bitmap = reference.get();
            if (bitmap != null) {
                return bitmap;
            }
        } else {
            /*
             * 加上一个对本地缓存的查找   
             */
            String bitmapName = imageURL.substring(imageURL.lastIndexOf("/") + 1);
            File cacheDir = new File(path);
            File[] cacheFiles = cacheDir.listFiles();
            int i = 0;
            if (null != cacheFiles) {
                for (; i < cacheFiles.length; i++) {
                    if (bitmapName.equals(cacheFiles[i].getName())) {
                        break;
                    }
                }

                if (i < cacheFiles.length) {
                    return BitmapFactory.decodeFile(path + bitmapName);
                }
            }
        }

        final Handler handler = new Handler() {
            /* (non-Javadoc)   
             * @see android.os.Handler#handleMessage(android.os.Message)   
             */
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub    
                imageCallBack.imageLoad(imageView, (Bitmap) msg.obj);
            }
        };

        //如果不在内存缓存中，也不在本地（被jvm回收掉），则开启线程下载图片    
        new Thread() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Bitmap bitmap;
                try {
                    bitmap = HttpUtils.getBitmapByAddress(imageURL, reqWidth, reqHeight);
                    imageCache.put(imageURL, new SoftReference<Bitmap>(bitmap));
                    Message msg = handler.obtainMessage(0, bitmap);
                    handler.sendMessage(msg);

                    File dir = new File(path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    File bitmapFile = new File(path +
                            imageURL.substring(imageURL.lastIndexOf("/") + 1));
                    if (!bitmapFile.exists()) {
                        try {
                            bitmapFile.createNewFile();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    FileOutputStream fos;
                    try {
                        fos = new FileOutputStream(bitmapFile);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    bitmap = null;
                }
            }
        }.start();

        return null;
    }

    public Bitmap loadBitmap(final ImageView imageView, final String imageURL, final ImageCallBack imageCallBack) {
        int reqWidth = imageView.getLayoutParams().width;
        int reqHeight = imageView.getLayoutParams().height;
        return loadBitmap(imageView, imageURL, reqWidth, reqHeight, imageCallBack);
    }

    public interface ImageCallBack {
        public void imageLoad(ImageView imageView, Bitmap bitmap);
    }
}  