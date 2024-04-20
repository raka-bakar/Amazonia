package com.raka.amazonia.usecase

import com.raka.amazonia.repository.ProductRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

interface GetInitialDataUseCase {
    /**
     * get initial data of products
     * @return Completable
     */
    fun getInitialData(): Completable
}

class GetInitialDataUseCaseImpl @Inject constructor(private val productRepository: ProductRepository) :
    GetInitialDataUseCase {
    override fun getInitialData(): Completable {
        return productRepository.getInitialData()
    }
}