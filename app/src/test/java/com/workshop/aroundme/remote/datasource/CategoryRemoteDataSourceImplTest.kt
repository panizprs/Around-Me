package com.workshop.aroundme.remote.datasource

import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.remote.model.response.CategoryDto
import com.workshop.aroundme.remote.model.response.CategoryResponseDto
import com.workshop.aroundme.remote.model.response.Meta
import com.workshop.aroundme.remote.model.response.toCategoryEntity
import com.workshop.aroundme.remote.service.CategoryService
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CategoryRemoteDataSourceImplTest {

    private val categoryService: CategoryService = mockk()
    private val categoryRemoteDataSource = CategoryRemoteDataSourceImpl(categoryService)

    @Before
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `getCategories when is empty`() {

        val map: Map<String, List<CategoryDto>> = mapOf()
        val categoryResponseDto = CategoryResponseDto(Meta(1, "", 1), map)

        every { categoryService.getCategoriesResponse() } returns Single.just(categoryResponseDto)

        assertEquals(categoryRemoteDataSource.getCategories(), listOf<ParentCategoryEntity>())
    }

    @Test
    fun `getCategories when list has one parentCategory`() {

        val meta: Meta = mockk()

        val categoryDto1 = CategoryDto("", 1, "", 1)
        val categoryDto2 = CategoryDto("", 1, "", 1)
        val map: Map<String, List<CategoryDto>> = mapOf("p1" to listOf(categoryDto1, categoryDto2))

        val categoryResponseDto = CategoryResponseDto(meta, map)

        every { categoryService.getCategoriesResponse() } returns Single.just(categoryResponseDto)

        val parentCategoryEntity1 =
            ParentCategoryEntity("p1", listOf(categoryDto1.toCategoryEntity(), categoryDto2.toCategoryEntity()))

        categoryRemoteDataSource.getCategories()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(listOf(parentCategoryEntity1))
    }

    @Test
    fun `getCategories when list has multiple parentCategory`() {

        val meta: Meta = mockk()

        val categoryDto1 = CategoryDto("", 1, "", 1)
        val categoryDto2 = CategoryDto("", 1, "", 1)
        val map: Map<String, List<CategoryDto>> = mapOf("p1" to listOf(categoryDto1, categoryDto2), "p2" to listOf())

        val categoryResponseDto = CategoryResponseDto(meta, map)

        every { categoryService.getCategoriesResponse() } returns Single.just(categoryResponseDto)

        val parentCategoryEntity1 =
            ParentCategoryEntity("p1", listOf(categoryDto1.toCategoryEntity(), categoryDto2.toCategoryEntity()))
        val parentCategoryEntity2 = ParentCategoryEntity("p2", listOf())

        categoryRemoteDataSource.getCategories()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(listOf(parentCategoryEntity1, parentCategoryEntity2))
    }
}