package com.wonokoyo.erpmus.classes;

public class Sekat {
    private int nomor;
    private int jumlah;
    private double bbRata;

    public Sekat() {

    }

    public Sekat(int nomor, int jumlah, double bbRata) {
        this.nomor = nomor;
        this.jumlah = jumlah;
        this.bbRata = bbRata;
    }

    public int getNomor() {
        return nomor;
    }

    public void setNomor(int nomor) {
        this.nomor = nomor;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getBbRata() {
        return bbRata;
    }

    public void setBbRata(double bbRata) {
        this.bbRata = bbRata;
    }
}
