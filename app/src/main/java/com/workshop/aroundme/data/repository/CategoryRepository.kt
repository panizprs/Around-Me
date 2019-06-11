package com.workshop.aroundme.data.repository

import com.workshop.aroundme.data.model.ParentCategory
import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.data.model.toParentCategory
import com.workshop.aroundme.domain.repository.CategoryRepository
import com.workshop.aroundme.remote.datasource.CategoryRemoteDataSource
import io.reactivex.Single

class CategoryRepositoryImpl(private val categoryRemoteDataSource: CategoryRemoteDataSource) : CategoryRepository {

    override fun getCategories(): Single<List<ParentCategory>> {
        return categoryRemoteDataSource.getCategories().map {categories ->
            categories.map{parentCategoryEntity ->
                parentCategoryEntity.toParentCategory()
            }
        }
    }
}
