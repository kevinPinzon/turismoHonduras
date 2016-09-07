package com.example.alumnos.recyclerview;

/**
 * Created by Alumnos on 20/8/2016.
 */
public class Movie {

    private String title;
    private String year;
    private int likes;
    private int dislikes;

    public Movie(String title, String year, int likes, int dislikes) {
        this.title = title;
        this.year = year;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}
