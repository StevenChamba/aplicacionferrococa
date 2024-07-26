package com.tec.scrumferre

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tec.scrumferre.R

class AgregarUsuarioActivity : AppCompatActivity() {

    private val listaUsuarios = mutableListOf<usuarios.Usuario>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_usuario)

        val buttonAgregarUsuario = findViewById<Button>(R.id.buttonAgregarUsuario)
        val buttonListarUsuarios = findViewById<Button>(R.id.buttonListarUsuarios)

        buttonAgregarUsuario.setOnClickListener {
            agregarUsuario()
        }

        buttonListarUsuarios.setOnClickListener {
            startActivity(Intent(this, AgregarUsuarioActivity::class.java))
        }
        // En tu actividad actual (AgregarUsuarioActivity):

        buttonListarUsuarios.setOnClickListener {
            val intent = Intent(this,  AgregarUsuarioActivity::class.java)
            intent.putExtra("usuarios", listaUsuarios.toTypedArray())
            startActivity(intent)
        }

    }

    private fun agregarUsuario() {
        val nombre = findViewById<EditText>(R.id.editTextNombre).text.toString()
        val apellido = findViewById<EditText>(R.id.editTextApellido).text.toString()
        val correo = findViewById<EditText>(R.id.editTextCorreo).text.toString()
        val contrasena = findViewById<EditText>(R.id.editTextContrasena).text.toString()
        val telefono = findViewById<EditText>(R.id.editTextTelefono).text.toString()
        val direccion = findViewById<EditText>(R.id.editTextDireccion).text.toString()
        val tipoUsuario = findViewById<EditText>(R.id.editTextTipoUsuario).text.toString()

        if (nombre.isNotBlank() && apellido.isNotBlank() && correo.isNotBlank() && contrasena.isNotBlank()) {
            val nuevoUsuario = usuarios.Usuario(0, nombre, apellido, correo, contrasena, telefono, direccion, tipoUsuario)
            listaUsuarios.add(nuevoUsuario)
            Toast.makeText(this, "Usuario '$nuevoUsuario' agregado correctamente", Toast.LENGTH_SHORT).show()
            limpiarCampos()
        } else {
            Toast.makeText(this, "Por favor, completa todos los campos obligatorios", Toast.LENGTH_SHORT).show()
        }
    }

    private fun limpiarCampos() {
        findViewById<EditText>(R.id.editTextNombre).text.clear()
        findViewById<EditText>(R.id.editTextApellido).text.clear()
        findViewById<EditText>(R.id.editTextCorreo).text.clear()
        findViewById<EditText>(R.id.editTextContrasena).text.clear()
        findViewById<EditText>(R.id.editTextTelefono).text.clear()
        findViewById<EditText>(R.id.editTextDireccion).text.clear()
        findViewById<EditText>(R.id.editTextTipoUsuario).text.clear()
    }
}
