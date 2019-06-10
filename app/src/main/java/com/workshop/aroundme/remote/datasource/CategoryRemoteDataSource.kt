package com.workshop.aroundme.remote.datasource

import com.workshop.aroundme.data.mapper.toCategoryEntity
import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.remote.service.CategoryService
import io.reactivex.Single

class CategoryRemoteDataSource(private val categoryService: CategoryService) {

    fun getCategories(): Single<List<ParentCategoryEntity>> {
        return categoryService.getCategoriesResponse()
            .map { categoryResponseDto ->
                categoryResponseDto.response
                    ?.map {
                        ParentCategoryEntity(
                            name = it.key,
                            children = it.value
                                .map { categoryDto ->
                                    categoryDto.toCategoryEntity()
                                }
                        )
                    }
            }
    }
}