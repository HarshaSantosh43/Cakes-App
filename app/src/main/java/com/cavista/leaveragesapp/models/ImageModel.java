package com.cavista.leaveragesapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cavista.leaveragesapp.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.databinding.BindingAdapter;

public class ImageModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("datetime")
    @Expose
    private Long datetime;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("animated")
    @Expose
    private Boolean animated;
    @SerializedName("width")
    @Expose
    private Long width;
    @SerializedName("height")
    @Expose
    private Long height;
    @SerializedName("size")
    @Expose
    private Long size;
    @SerializedName("views")
    @Expose
    private Long views;
    @SerializedName("bandwidth")
    @Expose
    private Long bandwidth;
    @SerializedName("vote")
    @Expose
    private Object vote;
    @SerializedName("favorite")
    @Expose
    private Boolean favorite;
    @SerializedName("nsfw")
    @Expose
    private Object nsfw;
    @SerializedName("section")
    @Expose
    private Object section;
    @SerializedName("account_url")
    @Expose
    private Object accountUrl;
    @SerializedName("account_id")
    @Expose
    private Object accountId;
    @SerializedName("is_ad")
    @Expose
    private Boolean isAd;
    @SerializedName("in_most_viral")
    @Expose
    private Boolean inMostViral;
    @SerializedName("has_sound")
    @Expose
    private Boolean hasSound;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("ad_type")
    @Expose
    private Long adType;
    @SerializedName("ad_url")
    @Expose
    private String adUrl;
    @SerializedName("edited")
    @Expose
    private String edited;
    @SerializedName("in_gallery")
    @Expose
    private Boolean inGallery;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("comment_count")
    @Expose
    private Object commentCount;
    @SerializedName("favorite_count")
    @Expose
    private Object favoriteCount;
    @SerializedName("ups")
    @Expose
    private Object ups;
    @SerializedName("downs")
    @Expose
    private Object downs;
    @SerializedName("points")
    @Expose
    private Object points;
    @SerializedName("score")
    @Expose
    private Object score;
    @SerializedName("section_name")
    @Expose
    private String sectionName;

    protected ImageModel(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            datetime = null;
        } else {
            datetime = in.readLong();
        }
        type = in.readString();
        byte tmpAnimated = in.readByte();
        animated = tmpAnimated == 0 ? null : tmpAnimated == 1;
        if (in.readByte() == 0) {
            width = null;
        } else {
            width = in.readLong();
        }
        if (in.readByte() == 0) {
            height = null;
        } else {
            height = in.readLong();
        }
        if (in.readByte() == 0) {
            size = null;
        } else {
            size = in.readLong();
        }
        if (in.readByte() == 0) {
            views = null;
        } else {
            views = in.readLong();
        }
        if (in.readByte() == 0) {
            bandwidth = null;
        } else {
            bandwidth = in.readLong();
        }
        byte tmpFavorite = in.readByte();
        favorite = tmpFavorite == 0 ? null : tmpFavorite == 1;
        byte tmpIsAd = in.readByte();
        isAd = tmpIsAd == 0 ? null : tmpIsAd == 1;
        byte tmpInMostViral = in.readByte();
        inMostViral = tmpInMostViral == 0 ? null : tmpInMostViral == 1;
        byte tmpHasSound = in.readByte();
        hasSound = tmpHasSound == 0 ? null : tmpHasSound == 1;
        if (in.readByte() == 0) {
            adType = null;
        } else {
            adType = in.readLong();
        }
        adUrl = in.readString();
        edited = in.readString();
        byte tmpInGallery = in.readByte();
        inGallery = tmpInGallery == 0 ? null : tmpInGallery == 1;
        link = in.readString();
        sectionName = in.readString();
    }

    public static final Creator<ImageModel> CREATOR = new Creator<ImageModel>() {
        @Override
        public ImageModel createFromParcel(Parcel in) {
            return new ImageModel(in);
        }

        @Override
        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDatetime() {
        return datetime;
    }

    public void setDatetime(Long datetime) {
        this.datetime = datetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getAnimated() {
        return animated;
    }

    public void setAnimated(Boolean animated) {
        this.animated = animated;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Long bandwidth) {
        this.bandwidth = bandwidth;
    }

    public Object getVote() {
        return vote;
    }

    public void setVote(Object vote) {
        this.vote = vote;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Object getNsfw() {
        return nsfw;
    }

    public void setNsfw(Object nsfw) {
        this.nsfw = nsfw;
    }

    public Object getSection() {
        return section;
    }

    public void setSection(Object section) {
        this.section = section;
    }

    public Object getAccountUrl() {
        return accountUrl;
    }

    public void setAccountUrl(Object accountUrl) {
        this.accountUrl = accountUrl;
    }

    public Object getAccountId() {
        return accountId;
    }

    public void setAccountId(Object accountId) {
        this.accountId = accountId;
    }

    public Boolean getAd() {
        return isAd;
    }

    public void setAd(Boolean ad) {
        isAd = ad;
    }

    public Boolean getInMostViral() {
        return inMostViral;
    }

    public void setInMostViral(Boolean inMostViral) {
        this.inMostViral = inMostViral;
    }

    public Boolean getHasSound() {
        return hasSound;
    }

    public void setHasSound(Boolean hasSound) {
        this.hasSound = hasSound;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public Long getAdType() {
        return adType;
    }

    public void setAdType(Long adType) {
        this.adType = adType;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public Boolean getInGallery() {
        return inGallery;
    }

    public void setInGallery(Boolean inGallery) {
        this.inGallery = inGallery;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Object getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Object commentCount) {
        this.commentCount = commentCount;
    }

    public Object getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Object favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Object getUps() {
        return ups;
    }

    public void setUps(Object ups) {
        this.ups = ups;
    }

    public Object getDowns() {
        return downs;
    }

    public void setDowns(Object downs) {
        this.downs = downs;
    }

    public Object getPoints() {
        return points;
    }

    public void setPoints(Object points) {
        this.points = points;
    }

    public Object getScore() {
        return score;
    }

    public void setScore(Object score) {
        this.score = score;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @BindingAdapter("avatar")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .placeholder(R.drawable.loading)
                .thumbnail(0.25f)
                .into(view);
    }

    @BindingAdapter("banner_picture")
    public static void loadBannerImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.loader_image)
                .thumbnail(0.5f)
                .into(view);
    }

    @BindingAdapter("profile_picture")
    public static void loadProfileImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().circleCrop())
                .placeholder(R.drawable.loading)
                .thumbnail(0.25f)
                .into(view);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(description);
        if (datetime == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(datetime);
        }
        parcel.writeString(type);
        parcel.writeByte((byte) (animated == null ? 0 : animated ? 1 : 2));
        if (width == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(width);
        }
        if (height == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(height);
        }
        if (size == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(size);
        }
        if (views == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(views);
        }
        if (bandwidth == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(bandwidth);
        }
        parcel.writeByte((byte) (favorite == null ? 0 : favorite ? 1 : 2));
        parcel.writeByte((byte) (isAd == null ? 0 : isAd ? 1 : 2));
        parcel.writeByte((byte) (inMostViral == null ? 0 : inMostViral ? 1 : 2));
        parcel.writeByte((byte) (hasSound == null ? 0 : hasSound ? 1 : 2));
        if (adType == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(adType);
        }
        parcel.writeString(adUrl);
        parcel.writeString(edited);
        parcel.writeByte((byte) (inGallery == null ? 0 : inGallery ? 1 : 2));
        parcel.writeString(link);
        parcel.writeString(sectionName);
    }
}
