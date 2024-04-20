package com.raka.amazonia.repository

import com.raka.amazonia.model.ProductCompact
import com.raka.amazonia.utils.toProductCompact
import com.raka.data.DataProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Repository to provide Product data
 */
interface ProductRepository {

    /**
     * get a detail information of a product,
     * @param id the ID of a product
     * @return Single of DBProduct
     */
    fun getProduct(id: Int): Single<ProductCompact>

    /**
     * Update favorite status of a product
     * @param id of Product
     * @param status of favorite
     * @return Completable type
     */
    fun updateFavoriteStatus(id: Int, status: Boolean): Completable

    /**
     * get list of product
     * @return Single of List<ProductCompact
     */
    fun getProducts(): Single<List<ProductCompact>>

    /**
     * load initial data from remote server and save it into local file and database
     * @return Completable
     */
    fun getInitialData(): Completable

    /**
     * get a list of products by category
     * @return Single of List<ProductCompact>
     */
    fun getProductsByCategory(id: Int): Single<List<ProductCompact>>
}

class ProductRepositoryImpl @Inject constructor(private val dataProvider: DataProvider) :
    ProductRepository {
    override fun getProduct(id: Int): Single<ProductCompact> {
        return dataProvider.getProduct(
            id
        ).map { dbProduct -> dbProduct.toProductCompact(dbProduct) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateFavoriteStatus(id: Int, status: Boolean): Completable {
        return dataProvider.updateFavoriteStatus(id = id, status = status)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getProducts(): Single<List<ProductCompact>> {
        return dataProvider.getProducts()
            .map { list -> list.map { dbProduct -> dbProduct.toProductCompact(dbProduct) } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getInitialData(): Completable {
        return dataProvider.loadInitialData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getProductsByCategory(id: Int): Single<List<ProductCompact>> {
        return dataProvider.loadProductsByCategory(id)
            .map { list -> list.map { dbProduct -> dbProduct.toProductCompact(dbProduct) } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}