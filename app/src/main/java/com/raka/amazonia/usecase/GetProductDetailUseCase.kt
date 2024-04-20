package com.raka.amazonia.usecase

import com.raka.amazonia.model.ProductCompact
import com.raka.amazonia.repository.ProductRepository
import com.raka.amazonia.utils.RatingManager
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface GetProductDetailUseCase {

    /**
     * get a Product detail
     * @return Single of type ProductCompact
     */
    fun loadProductDetail(id: Int): Single<ProductCompact>
}

class GetProductDetailUseCaseImpl @Inject constructor(
    private val repository: ProductRepository,
    private val ratingManager: RatingManager
) :
    GetProductDetailUseCase {
    override fun loadProductDetail(id: Int): Single<ProductCompact> {
        return repository.getProductsByCategory(id)
            .map { ratingManager.getProductRank(id, it) }
    }
}