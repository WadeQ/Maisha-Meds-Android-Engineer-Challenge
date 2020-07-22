package com.wadektech.maishamedschallenge.data

import androidx.paging.DataSource
import com.wadektech.maishamedschallenge.data.local.PostsRoomDatabase

class PostsRepository(private val db: PostsRoomDatabase) {

    suspend fun saveAllPostsFromRemote(posts: List<Posts>) {
        db.postsDao().saveAllPosts(posts)
    }

    fun getAllPostsFromRemote() : DataSource.Factory<Int, Posts>{
        return db.postsDao().getAllPosts()
    }

}