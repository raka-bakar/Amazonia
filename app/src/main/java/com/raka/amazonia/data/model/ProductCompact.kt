package com.raka.amazonia.data.model

data class ProductCompact(
    val id: Int,
    val thumbnail: String = "",
    val price: Int = 0,
    val description: String = "",
    val category: String = "",
    val rating: Double = 0.0,
    val title: String = "",
    var isFavorite: Boolean = false,
    var rank: Int = 0,
    var totalProduct: Int = 0
)