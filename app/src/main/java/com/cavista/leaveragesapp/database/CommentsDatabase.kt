package com.cavista.leaveragesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cavista.leaveragesapp.models.CommentsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [CommentsModel::class], version = 1)
abstract class CommentsDatabase : RoomDatabase() {

    abstract fun commentsDao(): CommentsDao

    companion object {
        @Volatile
        private var INSTANCE: CommentsDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CommentsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CommentsDatabase::class.java,
                        "comments_table")
                        .fallbackToDestructiveMigration()
                        .addCallback(CommentsDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                instance
            }
        }

        private class CommentsDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                       // populateDatabase(database.commentsDao())
                    }
                }
            }
        }

        fun populateDatabase(commentsDao: CommentsDao) {
            //commentsDao.deleteAll()
            var commentsModel = CommentsModel("This is what happens if Ugly Bastards try to ruin vanilla relationships", "Hello")
            commentsDao.insert(commentsModel)
            commentsModel = CommentsModel("Vanilla", "World2!")
            commentsDao.insert(commentsModel)
        }
    }

}
