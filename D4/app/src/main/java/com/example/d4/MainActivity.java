package com.example.d4;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private SeekBar mSeekBar;
    private TextView txv_progress;
    public AudioManager audioManager;
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        voiceRegisterReceiver();
        txv_progress = findViewById(R.id.txv_progress);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mSeekBar = findViewById(R.id.seekBar);
        mSeekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
                txv_progress.setText("目前音量："+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "滑動", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "停止", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private void voiceRegisterReceiver() {
        try {

            MyVolumnReceiver myVolumnReceiver = new MyVolumnReceiver(mSeekBar);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.media.VOLUMN_ACTION_CHANGED");
//            get.registerReceiver(broadcastReceiver, intentFilter, RECEIVER_EXPORTED);
            registerReceiver(myVolumnReceiver, intentFilter,  RECEIVER_EXPORTED);
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(), ex.getMessage()+ex.getStackTrace(), Toast.LENGTH_SHORT);
            Log.e("Error", ex.getMessage()+ex.getStackTrace());
        }
    }
}