package com.example.vezdecodmobilefinal

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val intent = Intent(p0, AlarmOnActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        p0?.startActivity(intent)
    }

}