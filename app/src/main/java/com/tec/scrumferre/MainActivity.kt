package com.tec.scrumferre

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tec.scrumferre.databinding.ActivityMainBinding
import com.tec.scrumferre.productos.ProductoAdapterOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import com.tec.scrumferre.usuarios.Usuario // Aquí agregas la importación
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var service: ProducService
    private lateinit var adapter: ProductoAdapter
    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Retrofit
        setupAdapter()
        setupRecyclerView()
        setupRetrofit()
        getUsuarios()
        binding.btnProductos.setOnClickListener {
            val intent = Intent(this, MainActivityProductos::class.java)
            startActivity(intent)


        }

        // Inicializar el adaptador con una lista vacía


        // Obtener la lista de productos y mostrarlos en la UI

    }

    private fun setupAdapter() {
        adapter = ProductoAdapter()
    }

    private fun setupRetrofit() {
        // Configurar Retrofit
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants. BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()

        service = retrofit.create(ProducService::class.java)


    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(  2, RecyclerView.VERTICAL)
            adapter = this@MainActivity.adapter}}
    private fun getUsuarios() {
        // Utilizar una corrutina para realizar la llamada al servicio en un hilo de fondo
        lifecycleScope.launch(Dispatchers.IO) {

                // Realizar la llamada al servicio para obtener la lista de usuarios
            val usuarios = service.getUsuarios()
            //Log.i("sms", usuarios.toString())
            withContext(Dispatchers.Main){
                adapter.submitList(usuarios)
            }

        }
    }
}
