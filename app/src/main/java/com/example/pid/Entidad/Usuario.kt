package com.example.pid.Entidad

import java.util.Date

class Usuario {
    //var idUsuario:Int=0
    var nombres:String=""
    var apellidos:String=""
    var dni:String=""
    var login:String=""
    var password:String=""
    var email:String=""
    //var fechaNacimiento: Date? = null
    var fechaNacimiento: String = ""

    //idUsuario:Int,
    //Se genera constructor para incializar los datos
    constructor(nombres:String, apellidos:String, dni:String,
                login:String, password:String, email:String, fechaNacimiento: String
    ){
        //this.idUsuario       = idUsuario
        this.nombres         = nombres
        this.apellidos       = apellidos
        this.dni             = dni
        this.login           = login
        this.password        = password
        this.email           = email
        this.fechaNacimiento = fechaNacimiento
    }
    constructor(){
    }
}