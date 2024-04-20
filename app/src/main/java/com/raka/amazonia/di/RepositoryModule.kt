package com.raka.amazonia.di

import com.raka.amazonia.repository.ProductRepository
import com.raka.amazonia.repository.ProductRepositoryImpl
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