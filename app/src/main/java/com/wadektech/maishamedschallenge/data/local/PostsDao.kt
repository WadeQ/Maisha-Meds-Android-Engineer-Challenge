package com.wadektech.maishamedschallenge.data.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wadektech.maishamedschallenge.data.Posts

@Dao
interface PostsDao {

    @Query("SELECT * FROM posts_db ORDER BY id ASC")
    fun getAllPosts(): DataSource.Factory<Int, Posts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllPosts(posts: List<Posts>)
}