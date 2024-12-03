package com.example.pid

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Date

class RegistrarTareaActivity : AppCompatActivity() {
    private lateinit var fechaVencimiento: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.registro_tarea_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fechaVencimiento = findViewById(R.id.txtRegistrarFechaTarea)

        // Crear el MaterialDatePicker
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Selecciona una fecha")  // Título del DatePicker
                .build()

        // Mostrar el DatePicker cuando el usuario haga clic en el EditText
        fechaVencimiento.setOnClickListener {
            datePicker.show(supportFragmentManager, "DATE_PICKER")
        }

        // Configurar el Listener para cuando el usuario seleccione una fecha
        datePicker.addOnPositiveButtonClickListener { selection ->
            val selectedDateInMillis = selection as Long
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val selectedDate = sdf.format(Date(selectedDateInMillis))

            // Establecer la fecha seleccionada en el EditText
            fechaVencimiento.setText(selectedDate)

            // Hacer algo con la fecha seleccionada, como enviarla a un servidor
            Toast.makeText(this, "Fecha seleccionada: $selectedDate", Toast.LENGTH_SHORT).show()
        }
    }
}