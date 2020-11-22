package com.example.vmac.WatBot;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;
import android.support.annotation.Nullable;
public class RadioSFXService extends Service {
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.radio_sfx);
        mediaPlayer.start();
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100, 100);
    }
    /*
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Toast.makeText(getApplicationContext(), "Playing Bohemian Rashpody in the Background",    Toast.LENGTH_SHORT).show();
        return startId;
    }
    public void onStart(Intent intent, int startId) {
    }
    */
    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }
    @Override
    public void onLowMemory() {
    }
}