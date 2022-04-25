package com.github.evgeniychufarnov.pagingapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.evgeniychufarnov.pagingapp.domain.entities.Post

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)

    @Query("SELECT * FROM posts")
    fun pagingSource(): PagingSource<Int, Post>

    @Query("DELETE FROM posts")
    suspend fun clearAll()
}