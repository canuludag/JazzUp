
package com.uludag.can.jazzup.models.playlistswithtracks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("added_at")
    @Expose
    private String addedAt;
    @SerializedName("added_by")
    @Expose
    private AddedBy addedBy;
    @SerializedName("is_local")
    @Expose
    private Boolean isLocal;
    @SerializedName("primary_color")
    @Expose
    private Object primaryColor;
    @SerializedName("track")
    @Expose
    private Track track;
    @SerializedName("video_thumbnail")
    @Expose
    private VideoThumbnail videoThumbnail;

    public String getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(String addedAt) {
        this.addedAt = addedAt;
    }

    public AddedBy getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(AddedBy addedBy) {
        this.addedBy = addedBy;
    }

    public Boolean getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(Boolean isLocal) {
        this.isLocal = isLocal;
    }

    public Object getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(Object primaryColor) {
        this.primaryColor = primaryColor;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public VideoThumbnail getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(VideoThumbnail videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

}
