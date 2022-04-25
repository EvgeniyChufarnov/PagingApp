package com.github.evgeniychufarnov.pagingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.evgeniychufarnov.pagingapp.domain.entities.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class PostsDb : RoomDatabase() {
    abstract fun postsDao(): PostsDao
}