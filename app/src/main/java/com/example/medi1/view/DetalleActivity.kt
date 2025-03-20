package com.example.medi1.view

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.medi1.R
import com.example.medi1.model.ItemRepository

class DetalleActivity : Activity() {

    private val itemRepository = ItemRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        // Obtenemos los datos directamente del intent
        val itemId = intent.getIntExtra("ITEM_ID", 1)
        val item = itemRepository.getItemById(itemId) ?: itemRepository.getAllItems().first()

        // Referenciamos las vistas en el layout
        val tituloTextView: TextView = findViewById(R.id.detalleTitulo)
        val descripcionTextView: TextView = findViewById(R.id.detalleDescripcion)
        val imagenView: ImageView = findViewById(R.id.detalleImagen)

        // Establecemos los valores en las vistas
        tituloTextView.text = item.titulo
        descripcionTextView.text = item.descripcionlarge

        // No podemos usar supportActionBar con Activity regular
        // pero podemos usar actionBar si lo necesitamos
        actionBar?.title = item.titulo
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Para manejar el botón de regreso usamos onBackPressed directamente
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}