package com.cavista.leaveragesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.cavista.leaveragesapp.R;
import com.cavista.leaveragesapp.databinding.ItemUserFirstBinding;
import com.cavista.leaveragesapp.models.ImageModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageViewHolder> implements Filterable {
    private List<ImageModel> imageModelList;
    private List<ImageModel> imageOriginalModelList;
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemUserFirstBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user_first, parent, false);
        return new ImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ImageModel imageModel = imageModelList.get(position);
        holder.itemUserBinding.setImageModel(imageModel);
    }

    @Override
    public int getItemCount() {

        if (imageModelList != null) {
            return imageModelList.size();
        } else {
            return 0;
        }
    }

    public void setPostList(List<ImageModel> postsModelArrayList) {
        if (postsModelArrayList != null && postsModelArrayList.size() > 0) {
            this.imageModelList = postsModelArrayList;
            imageOriginalModelList = new ArrayList<>(imageModelList);
            notifyDataSetChanged();
        }
    }

    public ImageModel getCurrentItemAt(int position) {
        return imageModelList.get(position);
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        private ItemUserFirstBinding itemUserBinding;

        public ImageViewHolder(@NonNull ItemUserFirstBinding itemUserBinding) {
            super(itemUserBinding.getRoot());
            this.itemUserBinding = itemUserBinding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getCurrentItemAt(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ImageModel imageModel);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private Filter myFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ImageModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(imageOriginalModelList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ImageModel imageModel : imageOriginalModelList) {
                    if (imageModel != null && imageModel.getTitle() != null && imageModel.getTitle().toLowerCase().contains(filterPattern.toLowerCase())) {
                        filteredList.add(imageModel);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (imageModelList != null && imageModelList.size() > 0) {
                imageModelList.clear();
                imageModelList.addAll((List) results.values);
                notifyDataSetChanged();
            }
        }
    };
}
