package com.raka.data.di

import com.raka.data.BuildConfig
import com.raka.data.api.ApiService
import com.raka.data.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    /**
     * provides okHttp instance
     */
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            Interceptor { chain ->
                val request = chain.request()
                val reqBuilder = request.newBuilder()
                    .header(
                        "User-Agent:",
                        "${BuildConfig.APP_NAME} : ${BuildConfig.APP_VERSION}"
                    )
                chain.proceed(reqBuilder.build())
            }
        ).build()
    }

    /**
     * provides Moshi instance configured with Json converters
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    /**
     * provides retrofit Api for network calls
     */
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    /**
     * provides retrofit instance
     */
    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
}