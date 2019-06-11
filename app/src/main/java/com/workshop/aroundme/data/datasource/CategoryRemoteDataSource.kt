package com.workshop.aroundme.data.datasource

import com.workshop.aroundme.data.model.ParentCategoryEntity
import io.reactivex.Single

interface CategoryRemoteDataSource{
    fun getCategories(): Single<List<ParentCategoryEntity>>
}