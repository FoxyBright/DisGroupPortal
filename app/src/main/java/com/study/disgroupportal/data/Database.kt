package com.study.disgroupportal.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.study.disgroupportal.DisGroupPortalApp
import com.study.disgroupportal.model.news.New
import com.study.disgroupportal.model.requests.Request
import com.study.disgroupportal.model.employee.Employee

@Database(
    entities = [
        New::class,
        Request::class,
        Employee::class],
    version = 11
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao(): Dao

    companion object {
        lateinit var dbDao: Dao

        fun initDatabase() {
            dbDao = Room.databaseBuilder(
                context = DisGroupPortalApp.instance,
                klass = AppDatabase::class.java,
                name = "database"
            ).fallbackToDestructiveMigration()
                .build()
                .getDao()
        }
    }
}