package com.example.handlemsgpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*
*Handle 主要用途也
*
* */
public class HandleYongFaActivity extends AppCompatActivity {
    private TextView text_view = null;
    private Button start = null;
    private Button end = null;
    //使用handler时首先要创建一个handler
    Handler handler = new Handler();
    //线程中运行该接口的run函数
    Runnable update_thread = new Runnable() {
        public void run() {
            //线程每次执行时输出"UpdateThread..."文字,\n为自动换行
            text_view.append("\nUpdateThread...");
            //延时1s后又将线程加入到线程队列中
            handler.postDelayed(update_thread, 1000);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_yong_fa);


        text_view = (TextView) findViewById(R.id.text_view);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new StartClickListener());
        end = (Button) findViewById(R.id.end);
        end.setOnClickListener(new EndClickListener());


    }

    private class StartClickListener implements View.OnClickListener {
        public void onClick(View v) {
            // TODO Auto-generated method stub
            //将线程接口立刻送到线程队列中
            handler.post(update_thread);
        }
    }

    private class EndClickListener implements View.OnClickListener {

        public void onClick(View v) {
            // TODO Auto-generated method stub
            //将接口从线程队列中移除
            handler.removeCallbacks(update_thread);
        }

    }

}
