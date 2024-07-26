package com.tec.scrumferre.productos

import com.tec.scrumferre.Constants
import com.tec.scrumferre.usuarios
import org.intellij.lang.annotations.MagicConstant
import retrofit2.http.GET

interface ProductService {
    @GET(Constants.PATH_productos)
    suspend fun getProducto() : List<usuarios.ProductoOf>

}