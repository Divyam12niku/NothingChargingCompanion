package com.divyam.nothingchargingcompanion

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

object NotificationHelper {
    private val messages = mapOf(
        10 to "Charging like a sloth 🦥",
        20 to "Still sipping juice 🧃",
        30 to "Getting there 🔋",
        40 to "Leveling up ⚡",
        50 to "Halfway hero 🧠",
        60 to "Almost powerful 💪",
        70 to "Supercharged soon 🚀",
        80 to "Look at you go 😎",
        90 to "Final lap 🏁",
        100 to "I'm full! 🎉"
    )

    fun showFunnyNotification(context: Context, level: Int) {
        if (level % 10 == 0) {
            val text = messages[level] ?: "Charging... $level%"
            val notification = NotificationCompat.Builder(context, "charging_channel")
                .setContentTitle("Battery: $level%")
                .setContentText(text)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()

            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(level, notification)
        }
    }
}
