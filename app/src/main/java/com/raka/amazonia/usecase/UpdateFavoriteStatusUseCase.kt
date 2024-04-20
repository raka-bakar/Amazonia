package com.raka.amazonia.usecase

import com.raka.amazonia.repository.ProductRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

interface UpdateFavoriteStatusUseCase {
    /**
     * Update favorite status of a product
     * @param id of Product
     * @param status of favorite
     * @return Completable type
     */
    fun updateFavoriteStatus(id: Int, status: Boolean): Completable
}

class UpdateFavoriteStatusUseCaseImpl
@Inject constructor(private val productRepository: ProductRepository) :
    UpdateFavoriteStatusUseCase {
    override fun updateFavoriteStatus(id: Int, status: Boolean): Completable {
        return productRepository.updateFavoriteStatus(id = id, status = !status)
    }
}