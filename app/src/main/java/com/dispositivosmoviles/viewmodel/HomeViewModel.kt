package com.dispositivosmoviles.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.dispositivosmoviles.data.LugarDao
import com.dispositivosmoviles.model.Lugar
import com.dispositivosmoviles.repository.LugarRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val obtenerLugares : MutableLiveData<List<Lugar>>
    private val repository: LugarRepository

    init {
        repository = LugarRepository(LugarDao())
        obtenerLugares = repository.obtenerLugar
    }

    fun agregarLugar(lugar: Lugar) {
        repository.agregarLugar(lugar)
    }

    fun eliminarLugar(lugar: Lugar) {
        repository.eliminarLugar(lugar)
    }


}