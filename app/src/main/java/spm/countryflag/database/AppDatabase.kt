package spm.countryflag.database

import androidx.room.Database
import androidx.room.RoomDatabase
import spm.countryflag.Country

@Database(entities = [Country::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}