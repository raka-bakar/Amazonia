package com.raka.amazonia.utils

import com.raka.amazonia.data.model.ProductCompact
import com.raka.amazonia.utils.Constants.PRODUCT_NOT_FOUND_ID
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
        // if the list empty return product not found
        if (listProductCompact.isEmpty()) {
            return ProductCompact(id = PRODUCT_NOT_FOUND_ID)
        }

        val product = listProductCompact.find { it.id == id }
        val totalProduct = listProductCompact.size

        val index = getIndexOfSortedList(product, listProductCompact)
        product?.rank = index
        product?.totalProduct = totalProduct
        return product ?: ProductCompact(id = PRODUCT_NOT_FOUND_ID)
    }

    /**
     * Sort the list of products by its rating descending
     * and get the index of the selected product
     * @param product selected product
     * @param listProductCompact list of product compact which has the same category
     * @return index of the selected product in the sorted list
     */
    private fun getIndexOfSortedList(
        product: ProductCompact?,
        listProductCompact: List<ProductCompact>
    ): Int {
        return (listProductCompact.sortedByDescending { it.rating }.indexOf(product)) + 1
    }
}