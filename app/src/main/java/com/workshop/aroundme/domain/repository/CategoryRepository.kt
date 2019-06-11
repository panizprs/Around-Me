package com.workshop.aroundme.domain.repository

import com.workshop.aroundme.data.model.ParentCategory
import io.reactivex.Single

interface CategoryRepository{
    fun getCategories(): Single<List<ParentCategory>>
}