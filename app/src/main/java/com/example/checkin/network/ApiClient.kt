package com.example.checkin.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ApiClient {
    // Emulador: 10.0.2.2. Se for celular físico, troque para o IP da sua máquina.
    private const val BASE_URL = "http://10.0.2.2:8080/api/"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            // Primeiro scalars (para String pura do token)
            .addConverterFactory(ScalarsConverterFactory.create())
            // Depois gson (para JSON das outras rotas)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
}
