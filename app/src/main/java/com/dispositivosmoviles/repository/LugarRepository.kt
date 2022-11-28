package com.dispositivosmoviles.repository

import androidx.lifecycle.LiveData
import com.dispositivosmoviles.model.Lugar
import com.dispositivosmoviles.data.LugarDao

class LugarRepository(private val lugarDao: LugarDao) {

    suspend fun guardarLugar(lugar: Lugar) {
        if (lugar.id==0) {  //Es un lugar nuevo
            lugarDao.agregarLugar(lugar)
        } else {  //El lugar existe... entonces se actualiza...
            lugarDao.modificarLugar(lugar)
        }
    }

    suspend fun eliminarLugar(lugar: Lugar) {
        lugarDao.eliminarLugar(lugar)
    }

    val obtenerLugar : LiveData<List<Lugar>> = lugarDao.ObtenerLugares()
}