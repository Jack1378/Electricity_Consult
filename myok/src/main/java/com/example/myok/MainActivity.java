package com.example.myok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<HomeBean.DataBean> Home_List = new ArrayList<>();
    private ListView lv;
    private String str;
    private String url = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private String str1;
    private Button But_get;
    private Button But_post;
    private Button But_send;
    private Button But_download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
  /*      //异步GET请求
        getAsynHttp();*/
        //异步POST请求
        postAsynHttp();

    }

    private void downAsynFile() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String url = "http://news.op.wpscdn.cn/uploadfile/2017/0620/20170620101507878.jpeg";
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(new File("/sdcard/wangshu.jpg"));
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                } catch (IOException e) {
                    Log.i("wangshu", "IOException");
                    e.printStackTrace();
                }

                Log.d("wangshu", "文件下载成功");
            }
        });
    }

    private void postAsynHttp() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("size", "10")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                str1 = response.body().string();
                Log.i("wangshu", str1);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        HomeBean homeBean = gson.fromJson(str1, HomeBean.class);
                        List<HomeBean.DataBean> data = homeBean.getData();
                        LvAdapter lvAdapter = new LvAdapter(MainActivity.this, data);
                        lv.setAdapter(lvAdapter);
                        Toast.makeText(getApplicationContext(), "Post请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
        But_get = (Button) findViewById(R.id.But_get);
        But_get.setOnClickListener(this);
        But_post = (Button) findViewById(R.id.But_post);
        But_post.setOnClickListener(this);
        But_send = (Button) findViewById(R.id.But_send);
        But_send.setOnClickListener(this);
        But_download = (Button) findViewById(R.id.But_download);
        But_download.setOnClickListener(this);
    }

    /**
     * 异步GET请求
     */
    public void getAsynHttp() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                str = response.body().string();
                Log.i("wangshu", str);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        HomeBean homeBean = gson.fromJson(str, HomeBean.class);
                        List<HomeBean.DataBean> data = homeBean.getData();
                        LvAdapter lvAdapter = new LvAdapter(MainActivity.this, data);
                        lv.setAdapter(lvAdapter);
                        Toast.makeText(getApplication(), "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.But_get:

                break;
            case R.id.But_post:

                break;
            case R.id.But_send:

                break;
            case R.id.But_download:
//异步下载文件
                downAsynFile();

                break;
        }
    }
}
