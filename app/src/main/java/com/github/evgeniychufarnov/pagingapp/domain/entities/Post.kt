package com.github.evgeniychufarnov.pagingapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey
    val name: String,
    val title: String?,
    val rating: Int?,
    val commentsNum: Int?
)