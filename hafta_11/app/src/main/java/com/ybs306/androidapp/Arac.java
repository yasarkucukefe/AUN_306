package com.ybs306.androidapp;

public class Arac {
    String firmaAd;
    int sene;
    String model;
    int km;
    private String sahibiKim;

    public Arac(){ } // boş Costructor

    public Arac(String firmaAd_,String model_,int sene_, int km_){
        this.firmaAd = firmaAd_;
        this.model = model_;
        this.sene = sene_;
        this.km = km_;
    }

    public void setFirmaAd(String firma_){
        this.firmaAd = firma_;
    }

    public String getFirmaAd(){return this.firmaAd;}

    public String getModel(){return this.model;}

    public void setSahibiKim(String isim){this.sahibiKim = isim;}

    public String getSahibiKim(){return this.sahibiKim;}

}


