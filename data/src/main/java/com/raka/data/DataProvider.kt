package com.raka.data

import javax.inject.Inject

/**
 * This class is the entry point to the module, it exposes the data calls to the app
 */
class DataProvider @Inject constructor(private val dataSource: DataSource) {
    fun getProducts() = dataSource.loadProductsLocalStorage()

    fun getProduct(id: Int) = dataSource.loadProduct(id)

    fun updateFavoriteStatus(id: Int, status: Boolean) =
        dataSource.updateFavoriteStatus(id = id, status = status)

    fun getBookmarkedProducts() = dataSource.getBookmarkedProducts()

    fun loadInitialData() = dataSource.loadInitialData()
}