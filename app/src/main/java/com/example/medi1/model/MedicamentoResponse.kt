package com.example.medi1.model

import com.google.gson.annotations.SerializedName

data class MedicamentoResponse(

    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("createdAt")
    val createdAt: String
)