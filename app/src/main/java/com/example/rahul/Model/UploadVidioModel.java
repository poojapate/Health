package com.example.rahul.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadVidioModel {


        @SerializedName("success")
        @Expose
        private boolean success;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("data")
        @Expose
        private Data data;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

    public class Data {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("video_id")
        @Expose
        private String videoId;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("videoname")
        @Expose
        private String videoname;
        @SerializedName("video")
        @Expose
        private String video;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("user_type")
        @Expose
        private String userType;
        @SerializedName("plan_status")
        @Expose
        private String planStatus;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVideoname() {
            return videoname;
        }

        public void setVideoname(String videoname) {
            this.videoname = videoname;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getPlanStatus() {
            return planStatus;
        }

        public void setPlanStatus(String planStatus) {
            this.planStatus = planStatus;
        }

    }
    }







