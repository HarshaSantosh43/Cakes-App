package com.cavista.leaveragesapp.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.cavista.leaveragesapp.models.CommentsModel

class CommentsRepository(private val commentsDao: CommentsDao, private val title: String) {

    val allComments: LiveData<List<CommentsModel>> = commentsDao.getAlphabetizedWords(title)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(commentsModel: CommentsModel) {
        commentsDao.insert(commentsModel)
    }
}
