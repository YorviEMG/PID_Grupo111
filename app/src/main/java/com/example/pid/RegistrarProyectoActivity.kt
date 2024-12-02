package com.example.pid

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appproject.utils.ApiUtils
import com.example.pid.Entidad.Proyecto
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrarProyectoActivity : AppCompatActivity() {
    private lateinit var nombreProyecto: TextInputEditText
    private lateinit var spinnerColor: Spinner
    private lateinit var spinnerTema: Spinner
    private lateinit var btnRegistrar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.registro_proyecto_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inicializar las vistas
        nombreProyecto = findViewById(R.id.txtRegistrarNombreProyecto)
        spinnerColor = findViewById(R.id.spnColor)
        spinnerTema = findViewById(R.id.spnTema)
        btnRegistrar = findViewById(R.id.btnRegistrarProyecto)
        btnCancelar = findViewById(R.id.btnVolverProyecto)

        // Configuración de los spinners (colores y temas)
        configurarSpinners()

        // Acción del botón Registrar
        btnRegistrar.setOnClickListener {
            registrarProyecto()
        }

        // Acción del botón Cancelar
        btnCancelar.setOnClickListener {
            finish() // Cierra la actividad y regresa a la anterior

    }
}
    private fun configurarSpinners() {
        val colores = arrayOf("Rojo", "Verde", "Azul", "Amarillo")
        val temas = arrayOf("Tema 1", "Tema 2", "Tema 3", "Tema 4")

        val adapterColores = ArrayAdapter(this, android.R.layout.simple_spinner_item, colores)
        adapterColores.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerColor.adapter = adapterColores

        val adapterTemas = ArrayAdapter(this, android.R.layout.simple_spinner_item, temas)
        adapterTemas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTema.adapter = adapterTemas
    }

    private fun registrarProyecto() {
        val nombre = nombreProyecto.text.toString().trim()
        val colorSeleccionado = spinnerColor.selectedItem.toString()
        val temaSeleccionado = spinnerTema.selectedItem.toString()

        // Validación de campos vacíos
        if (nombre.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa un nombre de proyecto", Toast.LENGTH_SHORT).show()
            return
        }


        // Crear el objeto de solicitud con los datos del formulario
        val proyecto = Proyecto(
            nombre = nombre,
            color = colorSeleccionado,
            tema = temaSeleccionado
        )

        // Llamada a la API para registrar el proyecto
        val apiService = ApiUtils.getAPIServiceProyecto()
        apiService.registrarProyecto(proyecto).enqueue(object : Callback<Proyecto> {
            override fun onResponse(call: Call<Proyecto>, response: Response<Proyecto>) {
                if (response.isSuccessful) {
                    val proyectoRegistrado = response.body()
                    if (proyectoRegistrado != null) {
                        Toast.makeText(this@RegistrarProyectoActivity, "Proyecto registrado exitosamente", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                } else {
                    Toast.makeText(this@RegistrarProyectoActivity, "Error en la respuesta de la API", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Proyecto>, t: Throwable) {
                // Manejo de errores de red
                Toast.makeText(this@RegistrarProyectoActivity, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show()
            }
        })
    }
}