package com.dispositivosmoviles.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dispositivosmoviles.R
import com.dispositivosmoviles.databinding.FragmentAddLugarBinding
import com.dispositivosmoviles.model.Lugar
import com.dispositivosmoviles.viewmodel.HomeViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [addLugarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class addLugarFragment : Fragment() {
    private var _binding: FragmentAddLugarBinding? = null
    private val binding get() = _binding!!
    private lateinit var lugarViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lugarViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentAddLugarBinding.inflate(inflater, container, false)

        binding.btAddLugar.setOnClickListener { agregarLugar() }

        return binding.root
    }

    //Efectivamente hace el registro del lugar en la base de datos
    private fun agregarLugar() {
        val nombre=binding.etNombre.text.toString()
        val correo=binding.etCorreoLugar.text.toString()
        val telefono=binding.etTelefono.text.toString()
        val web=binding.etWeb.text.toString()


        if (nombre.isNotEmpty()) {   //Al menos tenemos un nombre
            val lugar = Lugar(0,nombre,correo,web,telefono)
            lugarViewModel.guardarLugar(lugar)
            Toast.makeText(requireContext(),"Exito", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addLugarFragment_to_nav_home)
        } else {  //No hay info del lugar...
            Toast.makeText(requireContext(),getString(R.string.msg_data),
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}