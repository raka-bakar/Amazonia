package com.raka.data.di

import android.content.Context
import androidx.room.Room
import com.raka.data.database.AmazoniaDatabase
import com.raka.data.database.ProductDao
import com.raka.data.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {
    /**
     * provides room database instance
     */
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): AmazoniaDatabase {
        return Room.databaseBuilder(
            context,
            AmazoniaDatabase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesProductsDao(amazoniaDatabase: AmazoniaDatabase): ProductDao {
        return amazoniaDatabase.productDao()
    }
}