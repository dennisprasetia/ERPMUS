package com.wonokoyo.erpmus.classes;

import java.io.Serializable;

public class Mitra implements Serializable {
    private String nama;
    private int kandang;
    private int populasi;
    private int umur;

    public Mitra() {

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getKandang() {
        return kandang;
    }

    public void setKandang(int kandang) {
        this.kandang = kandang;
    }

    public int getPopulasi() {
        return populasi;
    }

    public void setPopulasi(int populasi) {
        this.populasi = populasi;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }
}
