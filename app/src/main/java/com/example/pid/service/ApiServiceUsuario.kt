package com.example.pid.service

import com.example.pid.Entidad.LoginRequest
import com.example.pid.Entidad.Proyecto
import com.example.pid.Entidad.ResponseMessage
import com.example.pid.Entidad.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServiceUsuario {
    //Aquí deben ir los métodos del servicio
    @POST("api/usuario/registrar")
    fun save(@Body us:Usuario): Call<ResponseMessage>


    //Login
    @POST("auth/")
    fun postLogin(@Body lr: LoginRequest): Call<ResponseMessage>

    @POST("proyecto/")
    fun registrarProyecto(@Body proyecto: Proyecto): Call<Proyecto>
}