package com.waracle.androidtest.ui.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DonKamillo on 17.05.2016.
 */
public class Cake {

    private String title;
    private String desc;
    private String image;

    public static Cake cakeFactory(JSONObject object) {
        try {
            return new Cake(object.getString("title"), object.getString("desc"), object.getString("image"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Cake(String title, String desc, String image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
