package com.github.evgeniychufarnov.pagingapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.evgeniychufarnov.pagingapp.domain.IPostsRepository
import com.github.evgeniychufarnov.pagingapp.domain.entities.Post

class MainActivityVm(
    postsRepository: IPostsRepository
): ViewModel() {

    @ExperimentalPagingApi
    val posts: LiveData<PagingData<Post>> = postsRepository.getPostsResultStream()
        .asLiveData()
        .cachedIn(viewModelScope)
}