package com.app.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.db.dao.LocationDao
import com.app.db.entity.LocationDetailsRoom


@Database(
    entities = [LocationDetailsRoom::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val locationDao: LocationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = androidx.room.Room.databaseBuilder(
                        context,
                        AppDatabase::class.java, "googlemappath"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }


        //Do it when clearing DB so Db get created again in same session if user do not close app
        fun destroyDbObj(){
            INSTANCE = null
        }
    }
}


