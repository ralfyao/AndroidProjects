package com.example.d4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.SeekBar;

public class MyVolumnReceiver extends BroadcastReceiver {
    private SeekBar mSeekBar;
    public MyVolumnReceiver(SeekBar seekBar){
        this.mSeekBar = seekBar;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.media.VOLUMN_ACTION_CHANGED")){
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            int currVolumn = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            mSeekBar.setProgress(currVolumn);
        }
    }
}
