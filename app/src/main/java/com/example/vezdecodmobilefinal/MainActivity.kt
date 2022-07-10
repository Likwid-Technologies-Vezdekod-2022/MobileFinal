package com.example.vezdecodmobilefinal

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import java.util.*

class MainActivity : AppCompatActivity() {
    private var day: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun resetEvent(v: View) {
        val timePicker: TimePicker = findViewById(R.id.timePicker)
        timePicker.minute = 0
        timePicker.hour = 12
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun set(v: View) {
        val timePicker: TimePicker = findViewById(R.id.timePicker)
        val minute = timePicker.minute
        val hour = timePicker.hour
        val c = Calendar.getInstance()
        val day_int = c.get(Calendar.DAY_OF_WEEK)
        println(day_int)
        println(this.day)
        var diff = 0
        if (this.day >= day_int) {
            diff = this.day - day_int
        }
        else {
            diff = this.day - day_int + 7
        }
        println(diff)
        val additionalMilliSeconds = ((((diff * 24) + hour) * 60) * 60 + minute * 60 - c.get(Calendar.HOUR)*60*60 - c.get(Calendar.MINUTE)*60 - c.get(Calendar.SECOND)) * 1000
        println(additionalMilliSeconds/1000)
        val milliseconds = System.currentTimeMillis() + additionalMilliSeconds
        val intent = Intent(applicationContext, MyBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 111, intent, 0)
        val alarmService : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmService.set(AlarmManager.RTC_WAKEUP, milliseconds, pendingIntent)
    }

    @SuppressLint("ResourceAsColor")
    fun chooseDay(v: View) {
        val bn1: Button = findViewById(R.id.mnButton)
        val bn2: Button = findViewById(R.id.tuButton)
        val bn3: Button = findViewById(R.id.wnButton)
        val bn4: Button = findViewById(R.id.thButton)
        val bn5: Button = findViewById(R.id.frButton)
        val bn6: Button = findViewById(R.id.stButton)
        val bn7: Button = findViewById(R.id.snButton)
        val currentButton: Button = v as Button

        bn1.getBackground().setColorFilter(R.color.purple_200, PorterDuff.Mode.DARKEN);
        bn2.getBackground().setColorFilter(R.color.purple_200, PorterDuff.Mode.DARKEN);
        bn3.getBackground().setColorFilter(R.color.purple_200, PorterDuff.Mode.DARKEN);
        bn4.getBackground().setColorFilter(R.color.purple_200, PorterDuff.Mode.DARKEN);
        bn5.getBackground().setColorFilter(R.color.purple_200, PorterDuff.Mode.DARKEN);
        bn6.getBackground().setColorFilter(R.color.purple_200, PorterDuff.Mode.DARKEN);
        bn7.getBackground().setColorFilter(R.color.purple_200, PorterDuff.Mode.DARKEN);
        currentButton.getBackground().setColorFilter(R.color.purple_700, PorterDuff.Mode.DARKEN);
        println(currentButton.text)
        this.day = decodeDayFromStr(currentButton.text as String)
    }

    private fun decodeDayFromStr(day: String): Int {
        return when (day) {
            "ПН" -> 2
            "ВТ" -> 3
            "СР" -> 4
            "ЧТ" -> 5
            "ПТ" -> 6
            "СБ" -> 7
            "ВС" -> 1
            else -> 1
        }
    }
}