package com.raka.amazonia.di

import android.content.Context
import com.raka.amazonia.AmazoniaApp
import com.raka.data.DataProvider
import com.raka.data.DataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun providesApplication(@ApplicationContext context: Context): AmazoniaApp =
        context as AmazoniaApp

    @Singleton
    @Provides
    fun provideDataProvider(dataSource: DataSource): DataProvider {
        return DataProvider(dataSource)
    }
}