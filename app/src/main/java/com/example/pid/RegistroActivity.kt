package com.example.pid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appproject.utils.ApiUtils
import com.example.pid.Entidad.ResponseMessage
import com.example.pid.Entidad.Usuario
import com.example.pid.service.ApiServiceUsuario
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

private lateinit var txtNombres: TextInputEditText
private lateinit var txtApellidos: TextInputEditText
private lateinit var txtDni: TextInputEditText
private lateinit var txtUserName: TextInputEditText
private lateinit var txtPassword: TextInputEditText
private lateinit var txtEmail: TextInputEditText
private lateinit var txtFecha: TextInputEditText
private lateinit var btnRegistro: Button
private lateinit var btnVolver: Button

private lateinit var api: ApiServiceUsuario

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.registro_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar campos
        txtNombres = findViewById(R.id.txtNombres)
        txtApellidos = findViewById(R.id.txtApellidos)
        txtDni = findViewById(R.id.txtDni)
        txtUserName = findViewById(R.id.txtUserName)
        txtPassword = findViewById(R.id.txtPassword)
        txtEmail = findViewById(R.id.txtEmail)
        txtFecha = findViewById(R.id.txtFecNac)
        btnRegistro = findViewById(R.id.btnRegistroReg)
        btnVolver = findViewById(R.id.btnVolverReg)

        btnRegistro.setOnClickListener { registro() }
        btnVolver.setOnClickListener { goLogin() }

        api = ApiUtils.getAPIServiceUsuario()
    }

    fun registro() {
        // Obtener los valores ingresados
        val nombres = txtNombres.text.toString()
        val apellidos = txtApellidos.text.toString()
        val dni = txtDni.text.toString()
        val user = txtUserName.text.toString()
        val password = txtPassword.text.toString()
        val email = txtEmail.text.toString()
        val fecha = txtFecha.text.toString()

        // *Validación de DNI*
        if (dni.length != 8 || !dni.all { it.isDigit() }) {
            showAlert("El DNI debe tener exactamente 8 dígitos y solo contener números.")
            return
        }

        // *Validación de Nombre de Usuario*
        if (user.length < 6 || user.length > 30) {
            showAlert("Tu nombre de usuario debe tener entre 6 y 30 caracteres.")
            return
        }

        // *Validación de Contraseña*
        val passwordRegex = Regex("^(?=.[A-Za-z])(?=.\\d)(?=.[!@#\$%^&])[A-Za-z\\d!@#\$%^&*]{8,}$")
        if (!password.matches(passwordRegex)) {
            showAlert("Elige una contraseña más segura. Prueba con una combinación de letras, números y símbolos.")
            return
        }

        // *Si todas las validaciones pasan, registrar al usuario*
        val bean = Usuario(nombres, apellidos, dni, user, password, email, fecha)
        api.save(bean).enqueue(object : Callback<ResponseMessage> {
            override fun onResponse(call: Call<ResponseMessage>, response: Response<ResponseMessage>) {
                if (response.isSuccessful) {
                    val obj1 = response.body()!!
                    Log.d("RegistroActivity", obj1.mensaje)
                    showAlert(obj1.mensaje)
                } else {
                    showAlert("Error en el registro. Intente nuevamente.")
                }
            }

            override fun onFailure(call: Call<ResponseMessage>, t: Throwable) {
                Log.e("RegistroActivity", "Error de red: ${t.localizedMessage}")
                showAlert("Error de red. Intente nuevamente.")
            }
        })
    }

    fun goLogin() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun showAlert(men: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Advertencia")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}