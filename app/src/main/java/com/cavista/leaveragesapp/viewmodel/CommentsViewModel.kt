package com.cavista.leaveragesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cavista.leaveragesapp.database.CommentsDatabase
import com.cavista.leaveragesapp.database.CommentsRepository
import com.cavista.leaveragesapp.models.CommentsModel
import com.cavista.leaveragesapp.preferences.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommentsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CommentsRepository
    val allComments: LiveData<List<CommentsModel>>
    private var preferences: AppPreferences

    init {
        val commentsDao = CommentsDatabase.getDatabase(application, viewModelScope).commentsDao()
        preferences = AppPreferences(application)
        repository = CommentsRepository(commentsDao, preferences.imageName)
        allComments = repository.allComments
    }

    fun insert(commentsModel: CommentsModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(commentsModel)
    }
}
