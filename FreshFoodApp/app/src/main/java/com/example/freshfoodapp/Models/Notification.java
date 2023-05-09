package com.example.freshfoodapp.Models;

import java.io.Serializable;

public class Notification implements Serializable {
    private String title;
    private String pic;
    private String desc;

    public Notification() {
    }

    public Notification(String title, String pic, String desc) {
        this.title = title;
        this.pic = pic;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
