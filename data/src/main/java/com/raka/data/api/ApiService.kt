package com.raka.data.api

import com.raka.data.model.ApiResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {
    /**
     * get list of All products
     * @return Response of list of ProductResponse
     */
    @GET("products")
    fun getAllProducts(): Single<ApiResponse>
}