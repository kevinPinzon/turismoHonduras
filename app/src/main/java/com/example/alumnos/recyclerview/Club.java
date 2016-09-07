package com.example.alumnos.recyclerview;

/**
 * Created by alexp on 6/9/2016.
 */
public class Club {
    private String urlPic;
    private String name;
    private int stars;
    private String description;

    public Club(String name) {
        this.name = name;
    }

    public Club(String urlPic,String name, int stars, String description) {
        this.urlPic = urlPic;
        this.name = name;
        this.stars = stars;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlPic() {
        return urlPic;
    }

    public void setUrlPic(String urlPic) {
        this.urlPic = urlPic;
    }
}
