package com.example.pid.Entidad

class LoginResponse {
    var token:String=""
    var login:String=""
    var nombreCompleto:String=""
    var idUsuario:String=""
    //var authorities:String=""
    //var opciones:String=""

    constructor(token:String,
                login:String,
                nombreCompleto:String,
                idUsuario:String){
        this.token = token
        this.login = login
        this.nombreCompleto = nombreCompleto
        this.idUsuario = idUsuario
    }
}