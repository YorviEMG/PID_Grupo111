package com.example.pid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.appproject.utils.ApiUtils
import com.example.pid.Entidad.LoginRequest
import com.example.pid.Entidad.ResponseMessage
import com.example.pid.Entidad.Usuario
import com.example.pid.service.ApiServiceUsuario
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

private lateinit var btnRegistro: Button
private lateinit var btnLogin: Button
private lateinit var txtUsuarioLogin: TextInputEditText
private lateinit var txtUsuarioContrasena: TextInputEditText

private lateinit var api: ApiServiceUsuario


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnRegistro = findViewById(R.id.btnRegistro)
        btnRegistro.setOnClickListener{ goRegistro()}

        txtUsuarioLogin = findViewById(R.id.txtUsuarioLogin)
        txtUsuarioContrasena = findViewById(R.id.txtUsuarioContrasena)
        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener{login()}

        api = ApiUtils.getAPIServiceUsuarioLogin()
    }
    fun goRegistro(){
        var intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }


    fun goToMenu(){
        var intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }


    fun showAlert(men:String){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Información")
        builder.setMessage(men)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }

    fun login(){
        val login = txtUsuarioLogin.text.toString()
        val password = txtUsuarioContrasena.text.toString()

        if (login.isEmpty()) {
            showAlert("Ingrese su correo.")
            return
        }
        else if (password.isEmpty()) {
            showAlert("Ingrese su contraseña.")
            return
        }


        // Falta arreglar el login
        val bean = LoginRequest(password, login)
        api.postLogin(bean).enqueue(object : retrofit2.Callback<ResponseMessage> {
            override fun onResponse(call: Call<ResponseMessage>, response: Response<ResponseMessage>) {
                if (response.isSuccessful) {
                    val obj1 = response.body()!!
                    Log.d("MainActivity", obj1.mensaje)
                    showAlert(obj1.mensaje)
                    goToMenu();
                } else {
                    showAlert("Error en el ingreso. Intente nuevamente.")
                    //goToMenu();
                }
            }

            override fun onFailure(call: Call<ResponseMessage>, t: Throwable) {
                Log.e("MainActivity", "Error de red: ${t.localizedMessage}")
                showAlert("Error de red. Intente nuevamente.")
            }
        })
    }
}