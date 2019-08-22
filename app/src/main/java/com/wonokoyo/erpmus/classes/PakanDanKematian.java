package com.wonokoyo.erpmus.classes;

public class PakanDanKematian {
    private int penerimaan;
    private int sisa;
    private int kematian;
    private String keterangan;

    public PakanDanKematian() {

    }

    public int getPenerimaan() {
        return penerimaan;
    }

    public void setPenerimaan(int penerimaan) {
        this.penerimaan = penerimaan;
    }

    public int getSisa() {
        return sisa;
    }

    public void setSisa(int sisa) {
        this.sisa = sisa;
    }

    public int getKematian() {
        return kematian;
    }

    public void setKematian(int kematian) {
        this.kematian = kematian;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
