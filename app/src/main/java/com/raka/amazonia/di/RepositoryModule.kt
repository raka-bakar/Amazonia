package com.raka.amazonia.di

import com.raka.amazonia.data.repository.ProductRepository
import com.raka.amazonia.data.repository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindProductsRepository(repository: ProductRepositoryImpl): ProductRepository
}