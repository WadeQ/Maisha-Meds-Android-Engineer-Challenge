package com.wadektech.maishamedschallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wadektech.maishamedschallenge.data.Posts
import com.wadektech.maishamedschallenge.data.PostsRepository
import com.wadektech.maishamedschallenge.data.remote.PostsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class PostsViewModel(private val repository: PostsRepository) : ViewModel() {
    private var job = Job()
    private val _coroutineScope = CoroutineScope(job + Dispatchers.Main)
    private var _postsPagedList: LiveData<PagedList<Posts>>

    init {
        getAllMagicCardsFromApi()
        val factory : DataSource.Factory<Int, Posts> = repository.getAllPostsFromRemote()
        val pagedListBuilder: LivePagedListBuilder<Int, Posts> = LivePagedListBuilder<Int, Posts>(factory,
            25)
        _postsPagedList = pagedListBuilder.build()
    }

    private fun savePosts(posts: List<Posts>) = viewModelScope.launch {
        repository.saveAllPostsFromRemote(posts)
    }

    fun getAllNotesFromDB(): LiveData<PagedList<Posts>> {
        return _postsPagedList
    }

    private fun getAllMagicCardsFromApi(){
        _coroutineScope.launch {
            //fetch data async without using callbacks or subscribers
            val postsList = PostsApi.retrofitService.getAllPostsAsync()
            try {
                val posts = postsList.await()
                savePosts(posts)
                Timber.d("Results are ${posts.size}")
            } catch (t :Throwable){
                Timber.d("Failure due to ${t.message}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}