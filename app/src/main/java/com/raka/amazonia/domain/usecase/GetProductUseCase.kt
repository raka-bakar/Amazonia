package com.raka.amazonia.domain.usecase

import com.raka.amazonia.data.model.ProductCompact
import com.raka.amazonia.data.repository.ProductRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface GetProductUseCase {
    /**
     * get a detail information of  a product
     * @return Single of ProductCompact
     */
    fun getProduct(id: Int): Single<ProductCompact>
}

class GetProductUseCaseImpl @Inject constructor(private val productRepository: ProductRepository) :
    GetProductUseCase {
    override fun getProduct(id: Int): Single<ProductCompact> {
        return productRepository.getProduct(id)
    }
}