package com.example.pid.Entidad

class LoginRequest {

    var password:String=""
    var login:String=""

    constructor( password:String, login:String){

        this.password        = password
        this.login           = login
    }
    constructor(){
    }
}