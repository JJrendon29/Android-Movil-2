package com.example.medi1.api

import com.ejemplo.tuapp.model.Item
import com.example.medi1.model.MedicamentoResponse
import retrofit2.Call
import retrofit2.http.GET

interface MedicamentosApi {
    @GET("medi/medicamentos")
    fun getMedicamentos(): Call<List<MedicamentoResponse>>
}