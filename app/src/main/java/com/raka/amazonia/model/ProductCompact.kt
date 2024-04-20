package com.raka.amazonia.model

data class ProductCompact(
    val id: Int,
    val thumbnail: String,
    val price: Int,
    val description: String,
    val title: String,
    var isFavorite: Boolean
)