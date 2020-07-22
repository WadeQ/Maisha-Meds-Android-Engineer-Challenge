package com.wadektech.maishamedschallenge.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wadektech.maishamedschallenge.data.Posts

@Database(entities = [Posts::class], version = 1, exportSchema = false)
abstract class PostsRoomDatabase : RoomDatabase() {
    abstract fun postsDao() : PostsDao

    companion object{
        var rInstance: PostsRoomDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = rInstance ?: synchronized(LOCK){
            rInstance  ?: createRoomDB(context.applicationContext).also {
                rInstance = it
            }
        }

        private fun createRoomDB(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PostsRoomDatabase::class.java,
            "ROOM_DB")
            .fallbackToDestructiveMigration()
            .build()
    }

}