package com.example.medi1.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medi1.R
import com.example.medi1.model.MedicamentoResponse

class ItemAdapter(
    private val onItemClick: (MedicamentoResponse) -> Unit,
    private val onLoadMore: () -> Unit // Mantener para compatibilidad, pero no usaremos
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_ITEM = 0
        private const val TYPE_LOADING = 1
    }

    private val items = mutableListOf<MedicamentoResponse>()
    private var isLoading = false
    private var hasMore = true

    // ViewHolder para items normales
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: ImageView = view.findViewById(R.id.itemImage)
        val itemTitle: TextView = view.findViewById(R.id.itemTitle)
        val itemDescription: TextView = view.findViewById(R.id.itemDescription)
    }

    // ViewHolder para el indicador de carga
    class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemViewType(position: Int): Int {
        return if (position == items.size && isLoading) TYPE_LOADING else TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_LOADING -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_loading, parent, false)
                LoadingViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_layout, parent, false)
                ItemViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                val currentItem = items[position]

                // Asignar valores a las vistas
                holder.itemTitle.text = currentItem.name
                holder.itemDescription.text = "ID: ${currentItem.id}"

                // Manejar clic en el item
                holder.itemView.setOnClickListener {
                    onItemClick(currentItem)
                }

                // YA NO NECESITAMOS trigger de load more automático
                // porque ahora usamos botones de navegación
            }
            is LoadingViewHolder -> {
                // El loading layout se muestra automáticamente
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size + if (isLoading) 1 else 0
    }

    // Métodos para manejar los datos
    fun addItems(newItems: List<MedicamentoResponse>) {
        val startPosition = items.size
        items.addAll(newItems)
        notifyItemRangeInserted(startPosition, newItems.size)
    }

    fun setLoading(loading: Boolean) {
        val wasLoading = isLoading
        isLoading = loading

        if (wasLoading != loading) {
            if (loading) {
                notifyItemInserted(items.size)
            } else if (!loading && wasLoading) {
                notifyItemRemoved(items.size)
            }
        }
    }

    fun setHasMore(hasMore: Boolean) {
        this.hasMore = hasMore
    }

    fun clearItems() {
        val itemCount = items.size
        items.clear()
        notifyItemRangeRemoved(0, itemCount)
    }

    fun getItemsCount(): Int = items.size
}