package com.example.flowz.boundservicelesson;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.IBinder;
import android.content.Intent;
import android.content.ComponentName;
import android.content.ServiceConnection;
import com.example.flowz.boundservicelesson.MyService.MyLocalBinder;

public class MainActivity extends AppCompatActivity {

    MyService flowzServices;
    boolean isBound = false;

    public void ShowTime(View view) {
        String currentTime = flowzServices.getCurrentTime();
        TextView show = (TextView)findViewById(R.id.flowzText);
        show.setText(currentTime);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent service1 = new Intent(this, MyService.class);
        bindService(service1,flowzConnection,Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection flowzConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyLocalBinder binder =(MyLocalBinder)iBinder;
            flowzServices = binder.getService();
            isBound = true;

         }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;

        }
    };
}
