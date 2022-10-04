package com.example.empresa.db

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "empresa")
data class Empresa(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_empresa") var id_bird: Int,
    @Nullable @ColumnInfo(name = "Nombre") var name: String,
    @Nullable @ColumnInfo(name = "url") var image: String,
    @Nullable @ColumnInfo(name = "telefono") var specie: String,
    @Nullable @ColumnInfo(name = "email") var dni_bird: String,
    @Nullable @ColumnInfo(name = "productos_y_servicios") var state: String,
    @Nullable @ColumnInfo(name = "clasificacion") var gender: String
) : Parcelable