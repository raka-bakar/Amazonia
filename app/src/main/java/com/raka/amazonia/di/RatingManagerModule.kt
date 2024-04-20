package com.raka.amazonia.di

import com.raka.amazonia.utils.RatingManager
import com.raka.amazonia.utils.RatingManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RatingManagerModule {

    @Binds
    fun bindRatingManager(
        ratingManagerImpl: RatingManagerImpl
    ): RatingManager
}