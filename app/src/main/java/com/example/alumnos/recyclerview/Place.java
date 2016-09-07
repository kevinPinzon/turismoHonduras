package com.example.alumnos.recyclerview;

/**
 * Created by Alumnos on 26/8/2016.
 */
public class Place {
    private String pictureUrl;
    private String title;
    private String description;
    private int id;

    public Place() {
    }

    public Place( int id,String pictureUrl, String title, String description) {
        this.pictureUrl = pictureUrl;
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
