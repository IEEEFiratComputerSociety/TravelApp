package com.example.travel_app_feed.Model;

import java.io.Serializable;

public class PostModel implements Serializable {

    private String baslik;
    private String icerik;
    private String kategori;
    private String sehir;
    private String tarih;
    private String koordinat;
    private String foto;
    private String id;

    public PostModel(String baslik, String icerik, String kategori, String sehir, String tarih, String koordinat, String foto, String id) {
        this.baslik = baslik;
        this.icerik = icerik;
        this.kategori = kategori;
        this.sehir = sehir;
        this.tarih = tarih;
        this.koordinat = koordinat;
        this.foto = foto;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getKoordinat() {
        return koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.koordinat = koordinat;
    }
}
