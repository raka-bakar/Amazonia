package com.raka.amazonia.domain.usecase

import com.raka.amazonia.data.model.ProductCompact
import com.raka.amazonia.data.repository.ProductRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface GetProductDetailUseCase {

    /**
     * get detail information of the selected product
     * @param id of the selected ProductCompact
     * @return Single of type ProductCompact
     */
    fun getProductDetail(id: Int): Single<ProductCompact>
}

class GetProductDetailUseCaseImpl @Inject constructor(
    private val repository: ProductRepository
) :
    GetProductDetailUseCase {
    override fun getProductDetail(id: Int): Single<ProductCompact> {
        return repository.getProductsByCategory(id = id)
    }
}