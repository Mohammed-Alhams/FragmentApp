package com.example.fragmentapp.fragment;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragmentapp.R;

public class MasterListFragment extends Fragment {

    TextView tvSSID;
    TextView tvIP;
    Button btn;


    public MasterListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_master_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvSSID = getView().findViewById(R.id.tvSSID);
        tvIP = getView().findViewById(R.id.tvIP);
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 404);





        IntentFilter filter = new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION);

        final BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                ConnectivityManager connectivityManager = (ConnectivityManager) getActivity()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                WifiManager wifiManager = (WifiManager) getActivity()
                        .getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                if (wifiManager.isWifiEnabled()
                        && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {

                    tvSSID.setText(wifiManager.getConnectionInfo().getSSID());
                    String ip = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
                    tvIP.setText(ip);
                } else {
                    Log.d("DDDD", "Not connected");
                    tvIP.setText("");
                    tvSSID.setText("");
                    Toast.makeText(context, "Not connected to wifi", Toast.LENGTH_SHORT).show();
                }
            }
        };

        getActivity().registerReceiver(wifiReceiver, filter);


    }
}


