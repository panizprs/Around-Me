package com.workshop.aroundme.data.repository

import com.workshop.aroundme.data.datasource.CategoryRemoteDataSource
import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.data.model.toParentCategory
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CategoryRepositoryImplTest{

    private val categoryRemoteDataSource =  mockk<CategoryRemoteDataSource>()
    private val categoryRepository = CategoryRepositoryImpl(categoryRemoteDataSource)


    @Before
    fun setUp(){
        clearAllMocks()
    }

    @Test
    fun `getCategories when successful`(){

        val categories : List<ParentCategoryEntity>? = listOf()

        every { categoryRemoteDataSource.getCategories() } returns Single.just(categories)

        categoryRepository.getCategories()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(categories?.map{category ->
                category.toParentCategory()
            })
    }

}