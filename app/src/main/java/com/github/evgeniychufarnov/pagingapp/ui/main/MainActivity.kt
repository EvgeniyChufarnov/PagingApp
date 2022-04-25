package com.github.evgeniychufarnov.pagingapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.github.evgeniychufarnov.pagingapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalPagingApi
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter: PostsAdapter by lazy { PostsAdapter() }
    private val vm:MainActivityVm by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPosts.adapter = adapter

        initObservers()
    }
    
    private fun initObservers() {
        vm.posts.observe(this) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
    }
}