package com.wadektech.maishamedschallenge.utils

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.wadektech.maishamedschallenge.data.Posts
import com.wadektech.maishamedschallenge.ui.PostsAdapter
import timber.log.Timber

@BindingAdapter("postsBindingAdapter")
fun bindConcertsAdapter(recyclerView: RecyclerView, posts: PagedList<Posts>?){
    val adapter = recyclerView.adapter as PostsAdapter
    Timber.d("binding adapter list size is: ${posts?.size}")
    adapter.submitList(posts)
}