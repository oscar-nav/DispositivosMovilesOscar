package com.dispositivosmoviles.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.dispositivosmoviles.model.Lugar
import com.dispositivosmoviles.data.LugarDatabase
import com.dispositivosmoviles.repository.LugarRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val obtenerLugares : LiveData<List<Lugar>>
    private val repository: LugarRepository

    init {
        val lugarDao = LugarDatabase.getDatabase(application).lugarDao()
        repository = LugarRepository(lugarDao)
        obtenerLugares = repository.obtenerLugar
    }

    fun guardarLugar(lugar: Lugar) {
        viewModelScope.launch { repository.guardarLugar(lugar) }
    }

    fun eliminarLugar(lugar: Lugar) {
        viewModelScope.launch { repository.eliminarLugar(lugar)}
    }


}