package com.cavista.leaveragesapp.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.cavista.leaveragesapp.models.DataModel;
import com.cavista.leaveragesapp.models.ImageModel;
import com.cavista.leaveragesapp.models.LeaveragesResponse;
import com.cavista.leaveragesapp.network.RetroService;
import com.cavista.leaveragesapp.network.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cavista.leaveragesapp.network.AppConstants.BASE_URL;

public class PostsRepository {

    private ArrayList<ImageModel> userArrayList = new ArrayList<>();
    private MutableLiveData<List<ImageModel>> mutableLiveData = new MutableLiveData<>();
    private Application application;
    private Executor executor;
    private RetrofitInterface retrofitInterface;


    public PostsRepository(Application application) {
        this.application = application;
    }

    public PostsRepository(Context context) {
        retrofitInterface = RetroService.getRetrofitServer();
        executor = Executors.newSingleThreadExecutor();
    }

    public MutableLiveData<List<ImageModel>> getAllPosts(Context mContext, int page_no, String name) {
        if (retrofitInterface == null) {
            retrofitInterface = RetroService.getRetrofitServer();
        }
        if (executor == null) {
            executor = Executors.newSingleThreadExecutor();
        }

        if (name != null && !name.equals("")) {
            name = name.replaceAll(" ", "").toLowerCase();
        }
        String queryUrl = BASE_URL + "search/" + page_no + "?q=" + name;
        Call<LeaveragesResponse> call = retrofitInterface.getLeaverage(queryUrl);
        call.enqueue(new Callback<LeaveragesResponse>() {
            @Override
            public void onResponse(Call<LeaveragesResponse> call, Response<LeaveragesResponse> response) {
                if (response.body() != null) {
                    LeaveragesResponse leaveragesResponse = response.body();
                    if (leaveragesResponse != null && leaveragesResponse.getStatus() == 200 && leaveragesResponse.getData() != null && leaveragesResponse.getData() != null) {

                        if (page_no == 1 && userArrayList.size() > 0) {
                            userArrayList.clear();
                        }

                        for (int i = 0; i < leaveragesResponse.getData().size(); i++) {

                            DataModel dataModel = leaveragesResponse.getData().get(i);
                            if (dataModel != null && dataModel.getImages() != null && dataModel.getImages().size() > 0) {
                                List<ImageModel> imagesList = dataModel.getImages();
                                for (int j = 0; j < imagesList.size(); j++) {
                                    ImageModel imageModel = imagesList.get(j);
                                    if (imageModel != null) {
                                        if (imageModel.getTitle() == null) {
                                            imageModel.setTitle(dataModel.getTitle() != null ? dataModel.getTitle() : "NA");
                                        }
                                        userArrayList.add(imageModel);
                                    }
                                }
                            }
                        }
                        mutableLiveData.setValue(userArrayList);
                    } else {
                        if (userArrayList.size() > 0) {
                            userArrayList.clear();
                        }
                        if (mContext != null) {
                            Toast.makeText(mContext, "Data not available for this search", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    if (userArrayList.size() > 0) {
                        userArrayList.clear();
                    }
                }
            }

            @Override
            public void onFailure(Call<LeaveragesResponse> call, Throwable t) {
                if (mContext != null) {
                    Toast.makeText(mContext, "Failed to get data from server", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return mutableLiveData;
    }

}
