package com.example.handlemsgpractice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/*主线程更新UI的方法
*
利用Handler刷新界面
*
* */
public class HandleMsg2Activity extends AppCompatActivity {

    private static final int REFRESH = 00001;
    private TextView tv;
    // 实例化一个handler
    Handler myHandler = new Handler() {
        //接收到消息后处理
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HandleMsg2Activity.REFRESH:
//                    mGameView.invalidate();//刷新界面
//                    tv.setText("ss");
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

       // 在onCreate()中开启线程
        new Thread(new GameThread()).start();

    }



    class GameThread implements Runnable {
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                Message message = new Message();
                message.what = HandleMsg2Activity.REFRESH;
                //发送消息
                HandleMsg2Activity.this.myHandler.sendMessage(message);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}