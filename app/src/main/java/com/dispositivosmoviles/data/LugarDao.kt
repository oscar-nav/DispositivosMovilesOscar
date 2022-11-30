package com.dispositivosmoviles.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dispositivosmoviles.model.Lugar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.DocumentReference

//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import androidx.room.Update
//import com.dispositivosmoviles.model.Lugar


class LugarDao {
//firebase variables

    private var codigoUsuario: String
    private var firestore: FirebaseFirestore

    init{
        val usuario = Firebase.auth.currentUser?.email
        codigoUsuario = "$usuario"
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

//Insertar
fun agregarLugar(lugar: Lugar){
    val document: DocumentReference

    if (lugar.id.isEmpty()){
    //proceso de agregar
        document = firestore.
                collection("lugaresMiercoles").
                document(codigoUsuario).
                collection("misLugares").
                document()
        lugar.id = document.id

    } else {
        //modificar

        document = firestore.collection("lugaresMiercoles").
        document(codigoUsuario).
        collection("misLugares").document(lugar.id)
        lugar.id = document.id
    }

    document.set(lugar).addOnCompleteListener{
        Log.d("AgregarLugar", "Guardado exitosamente")
    }
        .addOnCanceledListener { Log.e("AgregarLugar", "Error al guardar") }
}

    //Modificar

fun modificarLugar(lugar: Lugar){

}

//Delete
fun eliminarLugar(lugar: Lugar){

 if(lugar.id.isNotEmpty()){
     firestore.collection("lugaresMiercoles").document(codigoUsuario).
             collection("misLugares").
             document(lugar.id).delete()

         .addOnCompleteListener{
         Log.d("EliminarLugar", "Eliminado exitosamente")
     }
         .addOnCanceledListener { Log.e("EliminarLugar", "Error al eliminar") }
 }


}

    fun ObtenerLugares(): MutableLiveData<List<Lugar>>{
        val listaLugares = MutableLiveData<List<Lugar>>()

        firestore
            .collection("lugaresMiercoles").
            document(codigoUsuario).
            collection("misLugares").addSnapshotListener { snapshot, e->
                if (e != null){
                    return@addSnapshotListener
                }
                if(snapshot != null) {
                    val lista = ArrayList<Lugar>()
                    val lugares = snapshot.documents
                    lugares.forEach {
                        val lugar = it.toObject(Lugar::class.java)
                        if (lugar != null) {
                            lista.add(lugar)
                        }
                    }
                    listaLugares.value = lista
                }
            }
        return listaLugares
    }
}