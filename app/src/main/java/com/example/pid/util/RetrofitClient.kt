import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        fun getClient(URL: String): Retrofit {
            // Crear el interceptor para el logging
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)  // Nivel de log, puedes cambiarlo si es necesario

            // Crear un OkHttpClient y añadir el interceptor
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)  // Añadir el interceptor al cliente
                .build()

            // Construir Retrofit con el cliente configurado
            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .client(client)  // Asociar el cliente con el interceptor
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }
    }
}