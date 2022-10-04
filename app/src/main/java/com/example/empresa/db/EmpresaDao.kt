package com.example.empresa.db

import androidx.room.*


@Dao
interface EmpresaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(empresa: Empresa)

    @Delete
    fun delete(empresa: Empresa)

    @Query("SELECT * from empresa")
    fun getBirds() :List<Empresa>
}