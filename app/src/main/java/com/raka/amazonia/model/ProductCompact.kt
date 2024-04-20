package com.raka.amazonia.model

data class ProductCompact(
    val id: Int,
    val thumbnail: String,
    val price: Int,
    val description: String,
    val category: String,
    val rating: Double,
    val title: String,
    var isFavorite: Boolean,
    var rank: Int = 0,
    var totalProduct: Int = 0
)