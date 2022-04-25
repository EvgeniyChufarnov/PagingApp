package com.github.evgeniychufarnov.pagingapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.evgeniychufarnov.pagingapp.R
import com.github.evgeniychufarnov.pagingapp.databinding.ItemPostBinding
import com.github.evgeniychufarnov.pagingapp.domain.entities.Post

class PostsAdapter : PagingDataAdapter<Post, PostsAdapter.PostViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(parent)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class PostViewHolder(
        viewGroup: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(R.layout.item_post, viewGroup, false)
    ) {
        private val binding = ItemPostBinding.bind(itemView)

        fun bind(post: Post) {
            binding.tvTitle.text = post.title
            binding.tvRating.text = if (post.rating != null) "${post.rating}" else "0"
            binding.tvCommentsNum.text = if (post.commentsNum != null) "${post.commentsNum}" else "0"
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem
        }
    }
}