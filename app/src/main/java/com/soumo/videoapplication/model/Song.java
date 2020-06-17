package com.soumo.videoapplication.model;

public class Song {

    private String id ;
    private String title;
    private String subcategory ;
    private String image_url;
    private String video_url;

    public Song() {
    }

    public Song(String id, String title, String subcategory, String image_url, String video_url) {
        this.id = id;
        this.title = title;
        this.subcategory = subcategory;
        this.image_url = image_url;
        this.video_url = video_url;
    }

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

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }
}
