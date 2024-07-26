package com.tec.scrumferre

class usuarios {

    data class Usuario(
        val ID_PRO: Int,
        val Nombre: String,
        val Apellido: String,
        val Correo: String,
        val Contrasena: String,
        val telefono: String?,
        val direccion: String?,
        val tipoUsuario: String
    )

    data class ProductoOf(
        val ID_PRO: Int,
        val Nombre: String,
        val Descripcion: String,
        val Precio: Float,
        val Stock: Int
    )

}