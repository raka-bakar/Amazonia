package com.raka.amazonia.usecase

import com.raka.amazonia.model.ProductCompact
import com.raka.amazonia.repository.ProductRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface GetAllProductsUseCase {
    /**
     * get a list of product
     * @return Single of List<ProductCompact>
     */
    fun getProducts(): Single<List<ProductCompact>>
}

class GetAllProductsUsecaseImpl @Inject constructor(
    private val productRepository: ProductRepository
) : GetAllProductsUseCase {

    override fun getProducts(): Single<List<ProductCompact>> {
        return productRepository.getProducts()
    }
}