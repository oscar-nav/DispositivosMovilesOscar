package com.dispositivosmoviles.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispositivosmoviles.model.Lugar
import com.dispositivosmoviles.data.LugarDao

class LugarRepository(private val lugarDao: LugarDao) {

    fun agregarLugar(lugar: Lugar) {
lugarDao.agregarLugar(lugar)
    }

     fun eliminarLugar(lugar: Lugar) {
lugarDao.eliminarLugar(lugar)
    }

    val obtenerLugar : MutableLiveData<List<Lugar>> = lugarDao.ObtenerLugares()

    }
