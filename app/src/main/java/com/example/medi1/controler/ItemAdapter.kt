package com.example.medi1.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ejemplo.tuapp.model.Item
import com.example.medi1.R

class ItemAdapter(
    private val items: List<Item>,
    private val onItemClick: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    // Define la estructura de cada elemento de la vista
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.findViewById(R.id.itemImage)
        val itemTitle: TextView = view.findViewById(R.id.itemTitle)
        val itemDescription: TextView = view.findViewById(R.id.itemDescription)
    }

    // Crea nuevas vistas (invocado por el layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Infla la vista desde el XML
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    // Reemplaza el contenido de una vista (invocado por el layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]

        // Asigna los valores a las vistas
        holder.itemTitle.text = currentItem.titulo
        holder.itemDescription.text = currentItem.descripcion

        // Aquí podrías cargar la imagen con Glide o Picasso si tienes una URL
        // Por ejemplo, con Glide:
        // Glide.with(holder.itemView.context).load(currentItem.imagenUrl).into(holder.itemImage)

        // Maneja el clic en el elemento (solo una vez)
        holder.itemView.setOnClickListener {
            onItemClick(currentItem)
        }
    }

    // Devuelve el tamaño de tu dataset
    override fun getItemCount() = items.size
}