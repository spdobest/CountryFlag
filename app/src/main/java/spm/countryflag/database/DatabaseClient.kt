package spm.countryflag.database

import android.content.Context
import androidx.room.Room


/**
 * Created by Sibaprasad Mohanty on 29/06/21.
 * sp.dobest@gmail.com
 */

class DatabaseClient(context: Context) {

    private var appDatabase: AppDatabase =
        Room
            .databaseBuilder(context.applicationContext, AppDatabase::class.java, "countries")
            .fallbackToDestructiveMigration()
            .build()


    companion object {
        private var mInstance: DatabaseClient? = null

        @Synchronized
        fun getInstance(mCtx: Context): DatabaseClient? {
            if (mInstance == null) {
                mInstance = DatabaseClient(mCtx)
            }
            return mInstance
        }
    }

    fun getAppDatabase() = appDatabase
}