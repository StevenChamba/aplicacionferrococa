package com.tec.scrumferre

import org.intellij.lang.annotations.MagicConstant
import retrofit2.http.GET

interface ProducService {
    @GET(Constants.PATH_usarios)
    suspend fun getUsuarios() : List<usuarios.Usuario>

}