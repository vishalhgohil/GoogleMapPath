package com.app.di

import android.app.Application
import android.media.MediaPlayer
import android.os.Vibrator
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.app.core.Session
import com.app.db.database.AppDatabase
import javax.inject.Inject

class App : Application() {

    private val TAG = App::class.java.simpleName

    companion object {
        private var instance: App? = null

        fun applicationContext(): App {
            return instance as App
        }

        var mediaPlayer: MediaPlayer? = null
        var mvibrator: Vibrator? = null

    }

    @Inject
    lateinit var session: Session

    override fun onCreate() {
        super.onCreate()
        instance = this
        Injector.INSTANCE.initAppComponent(this, "MYTRAVELBOOK")
        Injector.INSTANCE.applicationComponent.inject(this)
        setUpFirebaseInstanceId()
    }

    private fun setUpFirebaseInstanceId() {
        FirebaseMessaging.getInstance().token.addOnSuccessListener { token ->
            session.deviceToken = token
            Log.d(TAG, "setUpFirebaseInstanceId: Token ${session.deviceToken}")
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        AppDatabase.destroyDbObj()
    }
}
