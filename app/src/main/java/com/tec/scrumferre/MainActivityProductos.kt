package com.tec.scrumferre

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tec.scrumferre.databinding.ActivityMainProductosBinding
import com.tec.scrumferre.productos.ProductService
import com.tec.scrumferre.productos.ProductoAdapterOf
import com.tec.scrumferre.usuarios.ProductoOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityProductos : AppCompatActivity() {

    private lateinit var adapter: ProductoAdapterOf
    private lateinit var service: ProductService
    private lateinit var binding: ActivityMainProductosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ProductoAdapterOf(mutableListOf()) { producto ->
            realizarPedido(producto)
        }
        setupRecyclerView()
        setupRetrofit()
        obtenerProductos()
    }

    private fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        service = retrofit.create(ProductService::class.java)
    }

    private fun setupRecyclerView() {
        binding.recyclerViewProductos.apply {
            layoutManager = StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)
            adapter = this@MainActivityProductos.adapter
        }
    }

    private fun obtenerProductos() {
        lifecycleScope.launch(Dispatchers.IO) {
            val productos = service.getProducto()
            withContext(Dispatchers.Main) {
                adapter.submitList(productos)
            }
        }
    }

    private fun realizarPedido(producto: ProductoOf) {
        Toast.makeText(this, "Pedido realizado para ${producto.Nombre}", Toast.LENGTH_SHORT).show()
        // Implementa aquí la lógica adicional para realizar el pedido
    }
}
