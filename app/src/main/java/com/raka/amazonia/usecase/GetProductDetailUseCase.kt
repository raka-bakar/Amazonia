package com.raka.amazonia.usecase

import com.raka.amazonia.model.ProductCompact
import com.raka.amazonia.repository.ProductRepository
import com.raka.amazonia.utils.Constants
import com.raka.amazonia.utils.RatingManager
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
    private val repository: ProductRepository,
    private val ratingManager: RatingManager
) :
    GetProductDetailUseCase {
    override fun getProductDetail(id: Int): Single<ProductCompact> {
        return repository.getProductsByCategory(id)
            .flatMap { listProductCompact ->
                getRankProduct(id, listProductCompact)
            }
    }

    private fun getRankProduct(id: Int, list: List<ProductCompact>): Single<ProductCompact> {
        return Single.create { emitter ->
            val product = ratingManager.getProductRank(id, list)
            if (product.id != Constants.PRODUCT_NOT_FOUND_ID) {
                emitter.onSuccess(product)
            } else {
                emitter.onError(Throwable("Data not found"))
            }
        }
    }
}