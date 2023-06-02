package com.example.trastbank;

import  androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 1);
        new MyThread(MainActivity.this).start();
    }

    class MyThread extends Thread{
        private final Context context;
        public MyThread(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            super.run();
            try {
                sleep(5 *  1000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Dialog dialog = new Dialog(context);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_fragment);
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                        dialog.getWindow().setGravity(Gravity.CENTER);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.getWindow().findViewById(R.id.tv_tryAgain)
                                .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onStart();
                            }
                        });
                        dialog.show();
                    }
                });

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
