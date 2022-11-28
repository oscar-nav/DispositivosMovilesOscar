package com.dispositivosmoviles.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dispositivosmoviles.model.Lugar

@Dao
interface LugarDao {

    //Select
@Query("Select * from lugar")
fun ObtenerLugares(): LiveData<List<Lugar>>

//Insertar
@Insert(onConflict = OnConflictStrategy.IGNORE)
suspend fun agregarLugar(lugar: Lugar)

    //Modificar
@Update(onConflict = OnConflictStrategy.IGNORE)
suspend fun modificarLugar(lugar: Lugar)

//Delete
@Delete
suspend fun eliminarLugar(lugar: Lugar)
}