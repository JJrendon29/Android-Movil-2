// app/src/main/java/com/example/medi1/model/ItemRepository.kt
package com.example.medi1.model

import com.ejemplo.tuapp.model.Item
import com.example.medi1.api.RetrofitClient
import retrofit2.Callback
import retrofit2.Response

class ItemRepository {
    // Método para obtener datos de la API
    fun getMedicamentosFromApi(callback: (List<Item>?) -> Unit) {
        RetrofitClient.medicamentosApi.getMedicamentos().enqueue(object : Callback<List<MedicamentoResponse>> {
            override fun onResponse(
                call: retrofit2.Call<List<MedicamentoResponse>>,
                response: Response<List<MedicamentoResponse>>
            ) {
                if (response.isSuccessful) {
                    // Mapear los datos de la API a nuestro modelo Item
                    val items = response.body()?.map { medicamento ->
                        Item(
                            id = medicamento.id.toIntOrNull() ?: 0,
                            titulo = medicamento.name,
                            descripcion = "Medicamento de la API",
                            descripcionlarge = "Este medicamento fue obtenido desde la API. Creado el: ${medicamento.createdAt}",
                            imagenUrl = medicamento.avatar
                        )
                    }
                    callback(items)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: retrofit2.Call<List<MedicamentoResponse>>, t: Throwable) {
                callback(null)
            }
        })
    }

    // Mantener el método original como fallback
    fun getAllItems(): List<Item> {
        // Tus datos originales
        return listOf(
            // ... tus items originales
        )
    }

    // Una función para obtener un ítem específico por su ID
    fun getItemById(id: Int): Item? {
        return getAllItems().find { it.id == id }
    }
}