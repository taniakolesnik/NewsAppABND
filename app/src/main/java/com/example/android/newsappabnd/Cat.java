package com.example.android.newsappabnd;

/**
 * Created by tetianakolesnik on 26/02/2018.
 */

public class Cat {


    private String mCat;

    public Cat(String cat) {
        setCat(cat);
    }
    public String getCat() {
        return mCat;
    }

    public void setCat(String cat) {
        this.mCat = cat;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "mCat='" + mCat + '\'' +
                '}';
    }

}
