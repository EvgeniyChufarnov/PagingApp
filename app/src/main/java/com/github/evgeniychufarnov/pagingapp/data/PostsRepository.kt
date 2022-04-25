package com.github.evgeniychufarnov.pagingapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.evgeniychufarnov.pagingapp.data.local.PostsDb
import com.github.evgeniychufarnov.pagingapp.data.remote.PostsApi
import com.github.evgeniychufarnov.pagingapp.data.remote.PostsRemoteMediator
import com.github.evgeniychufarnov.pagingapp.domain.IPostsRepository
import com.github.evgeniychufarnov.pagingapp.domain.entities.Post
import kotlinx.coroutines.flow.Flow

private const val PAGE_SIZE = 25

class PostsRepository(
    private val postsApi: PostsApi,
    private val postsDb: PostsDb
): IPostsRepository {

    @ExperimentalPagingApi
    override fun getPostsResultStream(): Flow<PagingData<Post>> {
        return Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = PostsRemoteMediator(postsApi, postsDb)
        ) {
            postsDb.postsDao().pagingSource()
        }.flow
    }
}