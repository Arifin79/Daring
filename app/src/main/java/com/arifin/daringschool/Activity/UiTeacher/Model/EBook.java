package com.arifin.daringschool.Activity.UiTeacher.Model;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class EBook implements Serializable {
    private String mName;
    private String mImageUrl;
    private String mPenulis;
    private String mPenelaah;
    private String mPreview;
    private String mPenerbit;
    private String mPdf;
    private String mKey;

    public EBook() {
        //empty constructor needed
    }

    public EBook(String name, String imageUrl, String penulis, String penelaah, String preview, String penerbit, String pdf) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageUrl;
        mPenulis = penulis;
        mPenelaah = penelaah;
        mPreview = preview;
        mPenerbit = penerbit;
        mPdf = pdf;
    }

    public String getmPenulis() {
        return mPenulis;
    }

    public void setmPenulis(String mPenulis) {
        this.mPenulis = mPenulis;
    }

    public String getmPenelaah() {
        return mPenelaah;
    }

    public void setmPenelaah(String mPenelaah) {
        this.mPenelaah = mPenelaah;
    }

    public String getmPreview() {
        return mPreview;
    }

    public void setmPreview(String mPreview) {
        this.mPreview = mPreview;
    }

    public String getmPenerbit() {
        return mPenerbit;
    }

    public void setmPenerbit(String mPenerbit) {
        this.mPenerbit = mPenerbit;
    }

    public String getmPdf() {
        return mPdf;
    }

    public void setmPdf(String mPdf) {
        this.mPdf = mPdf;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }
}
