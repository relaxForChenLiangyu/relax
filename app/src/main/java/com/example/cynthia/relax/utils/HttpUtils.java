package com.example.cynthia.relax.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by 11022 on 2017/5/14.
 */
public class HttpUtils extends UploadFileUtils {
    protected static final String path = "http://quickcanteen-1252946747.cossh.myqcloud.com";

    /**
     * 通过web服务获取数据的主要函数
     *
     * @param Url  请求的url
     * @param para 参数列表，map方式
     * @return 返回http请求结果，一般为json
     * @throws IOException
     */
    public static String httpConnectByPost(String Url, Map<String, String> para) throws IOException {
        URL url = new URL(path + Url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //连接设置
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
        connection.setRequestProperty("Charset", "UTF-8");
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        StringBuilder stringBuilder = new StringBuilder();

        //生成参数
        for (Map.Entry<String, String> entry : para.entrySet()) {
            stringBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            stringBuilder.append("&");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        //发生数据
        out.writeBytes(stringBuilder.toString());
        out.flush();
        out.close();

        //读取返回值并return
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String result = reader.readLine();
        reader.close();
        connection.disconnect();
        return result;
    }

    /**
     * 通过web服务获取数据的主要函数
     *
     * @param Url  请求的url
     * @param para 参数列表，map方式
     * @return 返回http请求结果，一般为json
     * @throws IOException
     */
    public static String httpConnectByPost(String Url, Map<String, String> para,String token) throws IOException {
        URL url = new URL(Url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //连接设置
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
        connection.setRequestProperty("Charset", "UTF-8");
        connection.setRequestProperty("X-TOKEN",token);
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        StringBuilder stringBuilder = new StringBuilder();

        //生成参数
        for (Map.Entry<String, String> entry : para.entrySet()) {
            stringBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            stringBuilder.append("&");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        //发生数据
        out.writeBytes(stringBuilder.toString());
        out.flush();
        out.close();

        //读取返回值并return
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String result = reader.readLine();
        reader.close();
        connection.disconnect();
        return result;
    }

    public static Bitmap getBitmapByAddress(String address, int reqWidth, int reqHeight) throws IOException {
        URL url = new URL(path + address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
        connection.setConnectTimeout(6000);
        //连接设置获得数据流
        connection.setDoInput(true);
        //不使用缓存
        connection.setUseCaches(false);
        //得到数据流
        InputStream is = connection.getInputStream();
        byte[] data = readStream(is);
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        int inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        System.out.println(inSampleSize);
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            if (heightRatio < 0) {
                inSampleSize = widthRatio;
            } else if (widthRatio < 0) {
                inSampleSize = heightRatio;
            } else {
                inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            }
        }
        return inSampleSize;
    }

    public static byte[] readStream(InputStream inStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }
}
