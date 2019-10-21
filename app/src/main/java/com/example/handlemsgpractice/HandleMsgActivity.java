package com.example.handlemsgpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

/*主线程更新UI的方法
 *
 * 更新一个TextView。
 * 使用Handler消息传递机制
 * 使用AsyncTask异步任务
 *
 * */
public class HandleMsgActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView ivTheme;


    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
//            if (msg.what == 0x123) {
//                tv.setText("更新后的TextView");
//            }
//todo 以上第一种
            switch (msg.what) {
                case 0:
                    ivTheme.setImageResource(R.mipmap.icon_qq1);
                    break;
                case 1:
                    ivTheme.setImageResource(R.mipmap.icon_qq2);
                    break;
                case 2:
                    ivTheme.setImageResource(R.mipmap.icon_qq3);
                    break;
                case 3:
                    ivTheme.setImageResource(R.mipmap.icon_qq4);
                    break;
            }
            super.handleMessage(msg);
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv_rule);
        ivTheme = findViewById(R.id.iv_hh);
        //第一种
//        new MyThread().start();
//        new Yibu().execute();

        thread.start();
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            int what = 0;
            //延迟两秒更新
            try {

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0x123);
        }


    }


    int what = 0;
    Thread thread = new Thread(new Runnable() {
        public void run() {
            while (true) {
                handler.sendEmptyMessage((what++) % 4);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });


    //第二种 异步处理 （共四种，只列举2中了）
    class Yibu extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            tv.setText("更新后的TextView");
        }


    }
}