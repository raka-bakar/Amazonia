package com.raka.data

import com.raka.data.api.ApiService
import com.raka.data.database.DBProduct
import com.raka.data.database.ProductDao
import com.raka.data.utils.toDBProduct
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface DataSource {

    /**
     * get a detail information of a product,
     * @param id the ID of a product
     * @return Single of DBProduct
     */
    fun loadProduct(id: Int): Single<DBProduct>

    /**
     * update favorite status of a product
     * @param product type of DBProduct
     * @return Completable type
     */
    fun updateFavoriteStatus(id: Int, status: Boolean): Completable

    /**
     * get a list of bookmarked products
     *  @return a Single of list of DBProduct
     */
    fun getBookmarkedProducts(): Single<List<DBProduct>>

    /**
     * load initial data from remote server and save it into local file and database
     * @return Completable
     */
    fun loadInitialData(): Completable

    /**
     * get list of DBProduct
     * @return Single of ProductResponse
     */
    fun loadProductsLocalStorage(): Single<List<DBProduct>>
}

class DataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao
) : DataSource {
    override fun loadProductsLocalStorage(): Single<List<DBProduct>> {
        return productDao.loadProducts()
    }

    override fun loadProduct(id: Int): Single<DBProduct> {
        return productDao.loadProduct(id)
    }

    override fun updateFavoriteStatus(id: Int, status: Boolean): Completable {
        return productDao.updateProduct(id = id, status = status)
    }

    override fun getBookmarkedProducts(): Single<List<DBProduct>> {
        TODO("Not yet implemented")
    }

    override fun loadInitialData(): Completable {
        // hit api and save to database and to local file
        return apiService.getAllProducts()
            .flatMapCompletable {
                val dbList = it.productResponses.map { product -> product.toDBProduct(product) }
                productDao.insertProducts(dbList)
            }
    }
}