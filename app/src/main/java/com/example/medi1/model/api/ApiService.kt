package com.example.medi1.model.api

import com.ejemplo.tuapp.model.Item
import com.example.medi1.model.MedicamentoResponse

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // Métodos para callbacks tradicionales
    @GET("medicamentos")
    fun getMedicamentos(@Query("page") page: Int): Call<MedicamentoResponse>

    @GET("medicamentos/{id}")
    fun getMedicamentoDetalle(@Path("id") id: Int): Call<Item>


}