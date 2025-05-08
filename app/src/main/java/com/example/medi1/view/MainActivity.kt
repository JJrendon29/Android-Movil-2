package com.example.medi1.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medi1.R
import com.example.medi1.controller.ItemAdapter
import com.example.medi1.model.ItemRepository

class MainActivity : Activity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter

    // Instanciamos el repositorio
    private val itemRepository = ItemRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Intentamos cargar desde la API
        itemRepository.getMedicamentosFromApi { items ->
            if (items != null && items.isNotEmpty()) {
                setupRecyclerView(items)
            } else {
                // Si falla, usamos los datos locales
                val localItems = itemRepository.getAllItems()
                setupRecyclerView(localItems)
                Toast.makeText(this, "Usando datos locales", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView(items: List<com.ejemplo.tuapp.model.Item>) {
        adapter = ItemAdapter(items) { item ->
            val intent = Intent(this, DetalleActivity::class.java).apply {
                putExtra("ITEM_ID", item.id)
                putExtra("ITEM_TITULO", item.titulo)
                putExtra("ITEM_DESCRIPCION", item.descripcion)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }
}