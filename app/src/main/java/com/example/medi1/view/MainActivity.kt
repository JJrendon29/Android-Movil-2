package com.example.medi1.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medi1.R
import com.example.medi1.controller.ItemAdapter
import com.example.medi1.model.ItemRepository
import com.example.medi1.model.MedicamentoResponse

class MainActivity : Activity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private lateinit var repository: ItemRepository

    // Elementos de navegación
    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button
    private lateinit var tvPageInfo: TextView

    // Variables de control de paginación
    private var currentPage = 0
    private var totalPages = 0
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = ItemRepository()
        initViews()
        setupRecyclerView()
        setupNavigationButtons()
        loadFirstPage()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        btnPrevious = findViewById(R.id.btnPrevious)
        btnNext = findViewById(R.id.btnNext)
        tvPageInfo = findViewById(R.id.tvPageInfo)
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)

        // IMPORTANTE: Ya no necesitamos el scroll infinito, así que removemos onLoadMore
        adapter = ItemAdapter(
            onItemClick = { item ->
                val intent = Intent(this, DetalleActivity::class.java).apply {
                    putExtra("ITEM_ID", item.id.toIntOrNull() ?: 1)
                    putExtra("ITEM_TITULO", item.name)
                    putExtra("ITEM_DESCRIPCION", item.avatar)
                }
                startActivity(intent)
            },
            onLoadMore = { } // Vacío porque ahora usamos botones
        )
        recyclerView.adapter = adapter
    }

    private fun setupNavigationButtons() {
        btnPrevious.setOnClickListener {
            if (currentPage > 0) {
                currentPage--
                loadPage(currentPage)
            }
        }

        btnNext.setOnClickListener {
            if (currentPage < totalPages - 1) {
                currentPage++
                loadPage(currentPage)
            }
        }
    }

    private fun loadFirstPage() {
        // Primero cargamos todos los datos para calcular el total de páginas
        repository.getPagedMedicamentos(0) { items, hasMore ->
            runOnUiThread {
                if (items.isNotEmpty()) {
                    // Calculamos total de páginas basado en el total de items
                    val totalItems = repository.getTotalItemsCount()
                    totalPages = (totalItems + 9) / 10 // Redondeo hacia arriba

                    currentPage = 0
                    loadPage(currentPage)
                } else {
                    Toast.makeText(this, "No se pudieron cargar los medicamentos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loadPage(page: Int) {
        if (isLoading) return
        isLoading = true

        // Mostrar indicador de carga
        recyclerView.post {
            adapter.setLoading(true)
            adapter.clearItems()
        }

        repository.getPagedMedicamentos(page) { items, hasMore ->
            runOnUiThread {
                isLoading = false

                recyclerView.post {
                    adapter.setLoading(false)

                    if (items.isNotEmpty()) {
                        adapter.addItems(items)
                        adapter.setHasMore(false) // No necesitamos más carga automática
                        updateNavigationState()
                        updatePageInfo()
                    } else {
                        Toast.makeText(this@MainActivity, "Error cargando la página", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun updateNavigationState() {
        // Habilitar/deshabilitar botones según la página actual
        btnPrevious.isEnabled = currentPage > 0
        btnNext.isEnabled = currentPage < totalPages - 1
    }

    private fun updatePageInfo() {
        // Actualizar el texto de información de página
        val pageNumber = currentPage + 1 // Mostrar página base 1
        tvPageInfo.text = "Página $pageNumber de $totalPages"
    }

    // Método opcional para refresh manual
    private fun refreshData() {
        repository.resetPagination()
        currentPage = 0
        totalPages = 0
        loadFirstPage()
    }
}