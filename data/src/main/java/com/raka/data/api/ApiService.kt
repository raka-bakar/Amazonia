package com.raka.data.api

import com.raka.data.model.ApiResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {
    /**
     * load list of All products
     * @return Single type pf ApiResponse
     *
     */
    @GET("products")
    fun loadProducts(): Single<ApiResponse>
}