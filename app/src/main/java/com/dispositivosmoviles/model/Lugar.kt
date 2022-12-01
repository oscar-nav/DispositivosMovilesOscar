package com.dispositivosmoviles.model

import android.os.Parcelable
//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize

data class Lugar(

    var id: String,
    val nombre: String,
    val correo: String?,
    val web: String?,
    val telefono: String?,
    val rutaAudio: String?,
    val rutaImagen: String?
) : Parcelable {
    constructor():
            this("", "", "", "", "", "", "")
}