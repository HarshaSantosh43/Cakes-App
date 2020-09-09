package com.cavista.leaveragesapp.viewmodel;

import android.app.Application;
import android.content.Context;

import com.cavista.leaveragesapp.models.ImageModel;
import com.cavista.leaveragesapp.repository.PostsRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class PostsViewModel extends AndroidViewModel {
    PostsRepository repository;

    public PostsViewModel(@NonNull Application application) {
        super(application);
        repository = new PostsRepository(application);
    }

    public LiveData<List<ImageModel>> getAllPosts(Context mContext, int page_no, String name) {
        return repository.getAllPosts(mContext, page_no, name);
    }

}
