package com.example.handlemsgpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

/*
 * 上面2个例子表面上看Handler使用了post方法启动了runnbale，其实启动的线程和activity主线程是同一个线程，因为它只是运行了线程的run方法，而不是start方法。实例3的目的是为了验证仅使用Handler的post方法是否处于同一个线程。*/
public class HandleYingYong3Activity extends AppCompatActivity {
    //新建一个handler
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_ying_yong3);


        //将runnable加载到handler的线程队列中去
        handler.post(r);
        //Thread t = new Thread(r);
        //t.start();
        //打印activtiy线程信息
        System.out.println("activity_id---->" + Thread.currentThread().getId());
        System.out.println("activity_name---->" + Thread.currentThread().getName());


    }

    Runnable r = new Runnable() {
        public void run() {
            //打印新建线程信息
            System.out.println("handler_id---->" + Thread.currentThread().getId());
            System.out.println("handler_name---->" + Thread.currentThread().getName());
            //延时10s，为了观察主界面中内容出现的时间
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    };



}
