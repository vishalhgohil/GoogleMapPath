package com.app.utils

import android.app.ActivityManager
import android.content.Context
import android.media.RingtoneManager
import android.net.Uri

class Utils {
    companion object{

        fun getNotificationURI(): Uri {
            return RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        }

        fun isMyServiceRunning(serviceClass: Class<*>, context: Context): Boolean {
            val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            return manager.getRunningServices(Integer.MAX_VALUE)
                .any { it.service.className == serviceClass.name }
        }
    }
}