package com.raka.amazonia.domain.usecase

import com.raka.amazonia.data.model.ProductCompact
import com.raka.amazonia.data.repository.ProductRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface GetAllProductsUseCase {
    /**
     * get a list of product
     * @param isFavorite status of product
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