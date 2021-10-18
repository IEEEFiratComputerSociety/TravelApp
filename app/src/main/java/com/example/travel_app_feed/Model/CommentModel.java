package com.example.travel_app_feed.Model;

public class CommentModel {


    private String yorum_id;
    private String yorum;
    private String yorum_tarihi;
    private String kullanici_id;
    private String kullanici_adi;
    private String kullanici_resmi;

    public CommentModel(String yorum_id, String yorum, String yorum_tarihi, String kullanici_id, String kullanici_adi, String kullanici_resmi) {
        this.yorum_id = yorum_id;
        this.yorum = yorum;
        this.yorum_tarihi = yorum_tarihi;
        this.kullanici_id = kullanici_id;
        this.kullanici_adi = kullanici_adi;
        this.kullanici_resmi = kullanici_resmi;
    }

    public String getYorum_id() {
        return yorum_id;
    }

    public void setYorum_id(String yorum_id) {
        this.yorum_id = yorum_id;
    }

    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }

    public String getYorum_tarihi() {
        return yorum_tarihi;
    }

    public void setYorum_tarihi(String yorum_tarihi) {
        this.yorum_tarihi = yorum_tarihi;
    }

    public String getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(String kullanici_id) {
        this.kullanici_id = kullanici_id;
    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }

    public String getKullanici_resmi() {
        return kullanici_resmi;
    }

    public void setKullanici_resmi(String kullanici_resmi) {
        this.kullanici_resmi = kullanici_resmi;
    }

}
