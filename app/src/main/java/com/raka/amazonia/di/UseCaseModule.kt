package com.raka.amazonia.di

import com.raka.amazonia.domain.usecase.GetAllProductsUseCase
import com.raka.amazonia.domain.usecase.GetAllProductsUsecaseImpl
import com.raka.amazonia.domain.usecase.GetInitialDataUseCase
import com.raka.amazonia.domain.usecase.GetInitialDataUseCaseImpl
import com.raka.amazonia.domain.usecase.GetProductDetailUseCase
import com.raka.amazonia.domain.usecase.GetProductDetailUseCaseImpl
import com.raka.amazonia.domain.usecase.UpdateFavoriteStatusUseCase
import com.raka.amazonia.domain.usecase.UpdateFavoriteStatusUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {
    @Binds
    fun bindGetAllProductsUseCase(
        getAllProductsUsecaseImpl: GetAllProductsUsecaseImpl
    ): GetAllProductsUseCase

    @Binds
    fun bindGetInitialDataUseCase(
        getInitialDataUseCaseImpl: GetInitialDataUseCaseImpl
    ): GetInitialDataUseCase

    @Binds
    fun bindUpdateFavoriteStatusUseCase(
        updateFavoriteStatusUseCaseImpl: UpdateFavoriteStatusUseCaseImpl
    ): UpdateFavoriteStatusUseCase

    @Binds
    fun bindGetProductsByCategoryUseCase(
        getProductsByCategoryImpl: GetProductDetailUseCaseImpl
    ): GetProductDetailUseCase
}