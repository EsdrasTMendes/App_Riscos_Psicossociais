package com.example.checkin.data

import android.content.Context
import android.util.Log
import com.example.checkin.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmpresaRepository(private val context: Context) {
    private val api = ApiClient.instance.create(ApiService::class.java)

    fun login(username: String, password: String, onResult: (String?) -> Unit) {
        val call = api.login(LoginRequest(username, password))
        call.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful) {
                    val token = response.body()
                    if (!token.isNullOrBlank()) {
                        // Salva o token no SharedPreferences
                        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                            .edit()
                            .putString("jwt_token", token)
                            .apply()
                        onResult(token)
                    } else {
                        Log.e("EmpresaRepository", "Token vazio ou nulo")
                        onResult(null)
                    }
                } else {
                    Log.e("EmpresaRepository", "Erro HTTP: ${response.code()} - ${response.errorBody()?.string()}")
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("EmpresaRepository", "Falha na requisição de login", t)
                onResult(null)
            }
        })
    }

    fun getEmpresas(onResult: (List<Empresa>?) -> Unit) {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val token = prefs.getString("jwt_token", null) ?: return onResult(null)

        val call = api.getEmpresas("Bearer $token")
        call.enqueue(object : Callback<List<Empresa>> {
            override fun onResponse(
                call: Call<List<Empresa>>,
                response: Response<List<Empresa>>
            ) {
                if (response.isSuccessful) {
                    onResult(response.body())
                } else {
                    Log.e("EmpresaRepository", "Erro HTTP: ${response.code()} - ${response.errorBody()?.string()}")
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<List<Empresa>>, t: Throwable) {
                Log.e("EmpresaRepository", "Falha ao buscar empresas", t)
                onResult(null)
            }
        })
    }
}
