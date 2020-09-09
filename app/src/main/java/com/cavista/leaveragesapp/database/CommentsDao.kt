package com.cavista.leaveragesapp.database

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.cavista.leaveragesapp.models.CommentsModel

@Dao
interface CommentsDao {

    @Query("SELECT * from comments_table WHERE title = :titleValue ORDER BY comment ASC")
    fun getAlphabetizedWords(titleValue: String): LiveData<List<CommentsModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(commentsModel: CommentsModel)

    @Query("DELETE FROM comments_table")
    fun deleteAll()
}
