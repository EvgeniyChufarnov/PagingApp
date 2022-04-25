package com.github.evgeniychufarnov.pagingapp.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.github.evgeniychufarnov.pagingapp.domain.entities.Post
import kotlinx.coroutines.flow.Flow

interface IPostsRepository {
    @ExperimentalPagingApi
    fun getPostsResultStream(): Flow<PagingData<Post>>
}