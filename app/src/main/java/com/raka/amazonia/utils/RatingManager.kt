package com.raka.amazonia.utils

import com.raka.amazonia.model.ProductCompact
import javax.inject.Inject

interface RatingManager {
    /**
     *  calculate a product rank among other products in the same category
     *  @param id of the Product
     *  @param listProductCompact List of Products which have the same category
     *  @return ProductCompact
     */
    fun getProductRank(id: Int, listProductCompact: List<ProductCompact>): ProductCompact
}

class RatingManagerImpl @Inject constructor() : RatingManager {
    override fun getProductRank(id: Int, listProductCompact: List<ProductCompact>):
        ProductCompact {
        val product = listProductCompact.find { it.id == id }
        val totalProduct = listProductCompact.size
        // sort the product list by its rating descending
        val index = listProductCompact.sortedByDescending { it.rating }.indexOf(product) + 1
        product?.rank = index
        product?.totalProduct = totalProduct
        return product!!
    }
}