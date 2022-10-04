package com.example.empresa.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Empresa::class), version = 1)
//@TypeConverters(Converters::class)
abstract class EmpresaDataBase : RoomDatabase() {

    abstract fun birdsDao(): EmpresaDao
    companion object {
        private var INSTANCE: EmpresaDataBase? = null

        fun getInstance(context: Context): EmpresaDataBase? {
            if (INSTANCE == null) {
                synchronized(EmpresaDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        EmpresaDataBase::class.java, "birdsdata.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}