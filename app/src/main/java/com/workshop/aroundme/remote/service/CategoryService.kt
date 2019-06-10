package com.workshop.aroundme.remote.service


import com.workshop.aroundme.remote.model.response.CategoryResponseDto
import io.reactivex.Single
import retrofit2.http.GET

interface CategoryService{

    @GET("v1/category")
    fun getCategoriesResponse(): Single<CategoryResponseDto>

}
