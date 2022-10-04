package com.example.empresa.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface EmpresaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(empresa: Empresa)

    @Query("SELECT * from empresa")
    fun getBirds() :List<Empresa>
}