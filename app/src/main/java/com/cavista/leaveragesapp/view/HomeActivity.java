package com.cavista.leaveragesapp.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.cavista.leaveragesapp.R;
import com.cavista.leaveragesapp.adapter.ImagesAdapter;
import com.cavista.leaveragesapp.databinding.ActivityHomeBinding;
import com.cavista.leaveragesapp.models.ImageModel;
import com.cavista.leaveragesapp.network.InternetConnectionDetector;
import com.cavista.leaveragesapp.preferences.AppPreferences;
import com.cavista.leaveragesapp.repository.PostsRepository;
import com.cavista.leaveragesapp.viewmodel.PostsViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class HomeActivity extends AppCompatActivity {

    private PostsViewModel postsViewModel;
    private ActivityHomeBinding activityHomeBinding;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private ImagesAdapter imagesAdapter;
    private AppPreferences preferences;
    private PostsRepository repository;
    private LinearLayoutManager layoutManager;
    private TextInputLayout textInputLayoutSearch;
    private TextInputEditText editTextSearch;
    private Button searchButton;
    private int page_no = 1;
    private String name = "vanilla";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        repository = new PostsRepository(HomeActivity.this);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        preferences = new AppPreferences(this);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.logout) {
                showLogoutSnackbar();
            }
            return false;
        });

        textInputLayoutSearch = activityHomeBinding.textInputLayoutSearch;
        editTextSearch = activityHomeBinding.editTextSearch;
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (textInputLayoutSearch.getError() != null) {
                    textInputLayoutSearch.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        searchButton = activityHomeBinding.searchButton;
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(editTextSearch.getText().toString().trim())) {
                    textInputLayoutSearch.setError("Enter valid search input");
                    editTextSearch.requestFocus();
                } else {
                    page_no = 1;
                    name = editTextSearch.getText().toString().trim();
                    getData(page_no, name);
                }
            }
        });

        recyclerView = activityHomeBinding.recyclerView;

        layoutManager = new GridLayoutManager(HomeActivity.this, isTablet(HomeActivity.this) ? 4 : 3);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        imagesAdapter = new ImagesAdapter();
        recyclerView.setAdapter(imagesAdapter);
        imagesAdapter.setOnItemClickListener(new ImagesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ImageModel imageModel) {
                preferences.setImageName(imageModel.getTitle());
                Intent intent = new Intent(HomeActivity.this, SelectedImageActivity.class);
                intent.putExtra("SingleData", (Parcelable) imageModel);
                intent.putExtra("SectionName", imageModel.getTitle());
                startActivity(intent);
            }
        });

        MenuItem searchItem = toolbar.getMenu().findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String seachText) {
                imagesAdapter.getFilter().filter(seachText);
                return true;
            }
        });

        postsViewModel = ViewModelProviders.of(this).get(PostsViewModel.class);

        refreshLayout = activityHomeBinding.refresh;
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(page_no, name);
            }
        });

        getData(page_no, name);
    }


    private void getData(int page_no, String name) {

        hideKeyBoard(HomeActivity.this);
        /*Check for internet connectivity*/
        if (InternetConnectionDetector.getInstance().isConnected(HomeActivity.this)) {
            refreshLayout.setRefreshing(true);
            postsViewModel.getAllPosts(this.getApplicationContext(), page_no, name).observe(this, allPostsList -> {
                refreshLayout.setRefreshing(false);

                if (allPostsList != null && allPostsList.size() > 0) {
                    imagesAdapter.setPostList(allPostsList);
                } else {
                    showErrorSnackbar("Data not available try with another input");
                }
            });
        } else {
            refreshLayout.setRefreshing(false);
            Toast.makeText(HomeActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void showErrorSnackbar(String message) {
        refreshLayout.setRefreshing(false);
        View rootView = findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
        snackbar.setAction("OK", v -> {
            snackbar.dismiss();
        });
        snackbar.show();
    }

    private void showLogoutSnackbar() {
        View rootView = findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(rootView, "Exit from this App", Snackbar.LENGTH_INDEFINITE);
        snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));
        snackbar.setAction("Ok", v -> {
            snackbar.dismiss();
            performLogout();
        });
        snackbar.show();
    }

    private void performLogout() {

        Handler handler = new Handler();
        handler.postDelayed(() -> {

            finish();
        }, 500);
    }

    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    private void hideKeyBoard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
