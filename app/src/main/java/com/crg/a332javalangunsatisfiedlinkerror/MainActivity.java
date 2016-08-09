package com.crg.a332javalangunsatisfiedlinkerror;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mButton;
    private ThreadPoolExecutor mThreadPoolExecutor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.bt_test);
        mThreadPoolExecutor = new ThreadPoolExecutor(2, 10, 20, TimeUnit.SECONDS, new ArrayBlockingQueue(10),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mThreadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {

                        //异常是由此行代码导致的
                        System.loadLibrary(" ");
                    }
                });
    }
}
