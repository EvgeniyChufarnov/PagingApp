package com.github.evgeniychufarnov.pagingapp.data.remote

import androidx.paging.*
import androidx.room.withTransaction
import com.github.evgeniychufarnov.pagingapp.data.local.PostsDb
import com.github.evgeniychufarnov.pagingapp.domain.entities.Post
import retrofit2.HttpException
import java.io.IOException

private const val SUBREDDIT = "aww"

@ExperimentalPagingApi
class PostsRemoteMediator(
    private val postsApi: PostsApi,
    private val postsDb: PostsDb
) : RemoteMediator<Int, Post>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Post>
    ): MediatorResult {

        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> ""
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )

                    lastItem.name
                }
            }

            val response = postsApi.getPosts(SUBREDDIT, loadKey).toPosts()

            postsDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    postsDb.postsDao().clearAll()
                }

                postsDb.postsDao().insertAll(response)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}