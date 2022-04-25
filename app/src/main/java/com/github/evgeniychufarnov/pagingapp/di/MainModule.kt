package com.github.evgeniychufarnov.pagingapp.di

import androidx.room.Room
import com.github.evgeniychufarnov.pagingapp.data.PostsRepository
import com.github.evgeniychufarnov.pagingapp.data.local.PostsDb
import com.github.evgeniychufarnov.pagingapp.data.remote.PostsApi
import com.github.evgeniychufarnov.pagingapp.domain.IPostsRepository
import com.github.evgeniychufarnov.pagingapp.ui.main.MainActivityVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://www.reddit.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostsApi::class.java)
    }

    single {
        Room.databaseBuilder(
            get(),
            PostsDb::class.java,
            "posts.db"
        )
            .build()
    }

    single<IPostsRepository> {
        PostsRepository(get(), get())
    }

    viewModel { MainActivityVm(get()) }
}