package com.example.pid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MenuActivity : AppCompatActivity() {
    private lateinit var rvProyecto: RecyclerView
    private lateinit var btnNuevoProyecto: Button
    private lateinit var btnLogout: ImageButton
    private lateinit var tvLogin:TextView
    private lateinit var tvWorkspace:TextView
    companion object{
        var idUsu:Int = 0
        var nombre:String = ""
        var login:String = ""
    }

    //private lateinit var apiProyecto: ApiServicesProyecto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.menu_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvLogin = findViewById(R.id.tvLoginPerfil)
        tvWorkspace = findViewById(R.id.tvWorkspace)
        btnNuevoProyecto = findViewById(R.id.btnNuevoProyecto)
        btnNuevoProyecto.setOnClickListener{ goNuevoProyecto() }
        btnLogout = findViewById(R.id.btnLogoutMain)
        datos()
        tvLogin.text = login
        tvWorkspace.text = "Workspace de " + nombre
        showAlert("Usuario número: ${idUsu}\nBienvenido ${nombre}")
    }
    fun datos(){
        //var info=intent.extras
        val extras = intent.extras
        if (extras != null) {
            idUsu = extras.getString("idUsuario").toString().toInt()
            nombre = extras.getString("nombreCompleto").toString()
            login = extras.getString("login").toString()
        }

    }
    fun goNuevoProyecto(){
        var intent = Intent(this@MenuActivity, RegistrarProyectoActivity::class.java)
        startActivity(intent)
    }
    fun logout(){
        var intent = Intent(this@MenuActivity, MainActivity::class.java)
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
}