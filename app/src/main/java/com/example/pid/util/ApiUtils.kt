package com.example.appproject.utils

import com.example.pid.service.ApiServiceUsuario

class ApiUtils {
    companion object {
        const val BASE_URL = "http://todo-list-api-net.eba-tmtufgav.us-east-2.elasticbeanstalk.com/"

        const val BASE_URL_LOGIN = "http://todo-list-api-net.eba-tmtufgav.us-east-2.elasticbeanstalk.com/url/"

        fun getAPIServiceUsuario(): ApiServiceUsuario {
            return RetrofitClient.getClient(BASE_URL).create(ApiServiceUsuario::class.java)
        }

        fun getAPIServiceUsuarioLogin(): ApiServiceUsuario {
            return RetrofitClient.getClient(BASE_URL_LOGIN).create(ApiServiceUsuario::class.java)
        }
    }
}


