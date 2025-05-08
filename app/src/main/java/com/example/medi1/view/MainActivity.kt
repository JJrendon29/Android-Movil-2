package com.example.medi1.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medi1.R
import com.example.medi1.api.MedicamentosApi
import com.example.medi1.api.RetrofitClient
import com.example.medi1.controller.ItemAdapter
import com.example.medi1.model.ItemRepository
import com.example.medi1.model.MedicamentoResponse
import retrofit2.Callback
import retrofit2.Response

class MainActivity : Activity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter

    // Instanciamos el repositorio
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Intentamos cargar desde la API
        getMedicamentosFromApi()
    }

    fun getMedicamentosFromApi() {
        val call = RetrofitClient.retrofit.create(MedicamentosApi::class.java)
        call.getMedicamentos().enqueue(object : Callback<List<MedicamentoResponse>> {
            override fun onResponse(
                call: retrofit2.Call<List<MedicamentoResponse>>,
                response: Response<List<MedicamentoResponse>>
            ) {
                if (response.isSuccessful) {
                    // Mapear los datos de la API a nuestro modelo Item
                    val items = response.body()
                    setupRecyclerView(items!!)
                } else {
                }
            }

            override fun onFailure(call: retrofit2.Call<List<MedicamentoResponse>>, t: Throwable) {
            }
        })
    }

    private fun setupRecyclerView(items: List<MedicamentoResponse>) {
        adapter = ItemAdapter(items) { item ->
            val intent = Intent(this, DetalleActivity::class.java).apply {
                putExtra("ITEM_ID", item.id)
                putExtra("ITEM_TITULO", item.name)
                putExtra("ITEM_DESCRIPCION", item.avatar)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }
}