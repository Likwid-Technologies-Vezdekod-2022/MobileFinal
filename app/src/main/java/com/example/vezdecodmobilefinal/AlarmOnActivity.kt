package com.example.vezdecodmobilefinal

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AlarmOnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_on)
        val mediaPlayer = MediaPlayer.create(applicationContext, R.raw.ringtone)
        mediaPlayer.start()

        val stopButton: Button = findViewById(R.id.stopButton)
        stopButton.setOnClickListener{
            mediaPlayer.stop()
            super.onBackPressed()
        }
    }
}