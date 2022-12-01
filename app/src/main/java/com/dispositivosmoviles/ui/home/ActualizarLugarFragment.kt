package com.dispositivosmoviles.ui.home

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dispositivosmoviles.R
import com.dispositivosmoviles.ui.home.ActualizarLugarFragmentArgs
import com.dispositivosmoviles.databinding.FragmentActualizarLugarBinding
import com.dispositivosmoviles.model.Lugar
import com.dispositivosmoviles.viewmodel.HomeViewModel

class ActualizarLugarFragment : Fragment() {

    //recupere argumentos
    private val args by navArgs<ActualizarLugarFragmentArgs>()
    private var _binding : FragmentActualizarLugarBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentActualizarLugarBinding.inflate(inflater, container, false)

        binding.etNombre.setText(args.lugar.nombre)
        binding.etCorreoLugar.setText(args.lugar.correo)
        binding.etTelefono.setText(args.lugar.telefono)
        binding.etWeb.setText(args.lugar.web)

        binding.btUpdateLugar.setOnClickListener{
            actualizarLugar()
        }
        binding.btnEliminarLugar.setOnClickListener{
            eliminarLugar()
        }

        if(args.lugar.rutaAudio?.isNotEmpty()==true){
            mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(args.lugar.rutaAudio)
            mediaPlayer.prepare()
            binding.btPlay.isEnabled = true

        } else {
            binding.btPlay.isEnabled = false
        }

        binding.btPlay.setOnClickListener{ mediaPlayer.start() }


        if(args.lugar.rutaImagen?.isNotEmpty()==true){
         Glide.with(requireContext())
             .load(args.lugar.rutaImagen)
             .into(binding.imagen)
        }


        return binding.root
    }

    private fun actualizarLugar(){
        val nombre = binding.etNombre.text.toString()
        val correo = binding.etCorreoLugar.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()

        if (nombre.isEmpty()){
            Toast.makeText(requireContext(), getString(R.string.msg_data), Toast.LENGTH_LONG)

        } else if (correo.isEmpty()){
            Toast.makeText(requireContext(), getString(R.string.msg_data), Toast.LENGTH_LONG)
        }
        else{
            val lugar = Lugar(args.lugar.id, nombre, correo, telefono, web, args.lugar.rutaAudio, args.lugar.rutaImagen )
            homeViewModel.agregarLugar(lugar)
            Toast.makeText(requireContext(), getString(R.string.msg_lugar_updated), Toast.LENGTH_LONG)
            findNavController().navigate(R.id.action_actualizarLugarFragment_to_nav_home)
        }


    }

    private fun eliminarLugar(){
        val nombre = binding.etNombre.text.toString()
        val correo = binding.etCorreoLugar.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()

        if (nombre.isEmpty()){
            Toast.makeText(requireContext(), getString(R.string.msg_data), Toast.LENGTH_LONG)

        } else if (correo.isEmpty()){
            Toast.makeText(requireContext(), getString(R.string.msg_data), Toast.LENGTH_LONG)
        }
        else{
            val lugar = Lugar(args.lugar.id, nombre, correo, telefono, web, args.lugar.rutaAudio, args.lugar.rutaImagen)
            homeViewModel.eliminarLugar(lugar)
            Toast.makeText(requireContext(), getString(R.string.msg_lugar_updated), Toast.LENGTH_LONG)
            findNavController().navigate(R.id.action_actualizarLugarFragment_to_nav_home)
        }


    }

    }
