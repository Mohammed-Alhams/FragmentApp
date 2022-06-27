package com.example.fragmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragmentapp.adapter.MyListAdapter;
import com.example.fragmentapp.fragment.MasterListFragment;

public class MainActivity extends AppCompatActivity{

    Button btnSendBroadCast;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MasterListFragment masterListFragment = new MasterListFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_view, masterListFragment)
                .commit();

        btnSendBroadCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("MyBroadCast");
                intent.putExtra("message", "welcome");
                sendBroadcast(intent);// for all receivers
//                sendOrderedBroadcast(); for single receiver at a time
                
            }
        });

        registerReceiver(new MyReceiver(), new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));

    }

    class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if("MyBroadCast".equals(intent.getAction())){
                String message = intent.getStringExtra("message");
                tv.setText(message);
            }

            if (intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)){

            }
        }
    }
}