package com.raka.amazonia.di

import com.raka.amazonia.usecase.GetAllProductsUseCase
import com.raka.amazonia.usecase.GetAllProductsUsecaseImpl
import com.raka.amazonia.usecase.GetInitialDataUseCase
import com.raka.amazonia.usecase.GetInitialDataUseCaseImpl
import com.raka.amazonia.usecase.GetProductUseCase
import com.raka.amazonia.usecase.GetProductUseCaseImpl
import com.raka.amazonia.usecase.UpdateFavoriteStatusUseCase
import com.raka.amazonia.usecase.UpdateFavoriteStatusUseCaseImpl
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
    fun bindGetProductUseCase(getProductUseCaseImpl: GetProductUseCaseImpl): GetProductUseCase

    @Binds
    fun bindGetInitialDataUseCase(
        getInitialDataUseCaseImpl: GetInitialDataUseCaseImpl
    ): GetInitialDataUseCase

    @Binds
    fun bindUpdateFavoriteStatusUseCase(
        updateFavoriteStatusUseCaseImpl: UpdateFavoriteStatusUseCaseImpl
    ): UpdateFavoriteStatusUseCase
}