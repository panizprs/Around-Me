package com.workshop.aroundme.domain.interactor.category

import com.workshop.aroundme.data.model.ParentCategory
import com.workshop.aroundme.domain.executor.PostExecutorThread
import com.workshop.aroundme.domain.executor.UseCaseExecutorThread
import com.workshop.aroundme.domain.interactor.base.None
import com.workshop.aroundme.domain.interactor.base.SingleUseCase
import com.workshop.aroundme.domain.repository.CategoryRepository
import io.reactivex.Single

class GetCategoriesUseCase(
    private val categoryRepository: CategoryRepository,
    postExecutorThread: PostExecutorThread,
    useCaseExecutorThread: UseCaseExecutorThread
) : SingleUseCase<None, List<ParentCategory>>(postExecutorThread, useCaseExecutorThread) {


    override fun buildSingle(param: None): Single<List<ParentCategory>> {
        return categoryRepository.getCategories()
    }
}