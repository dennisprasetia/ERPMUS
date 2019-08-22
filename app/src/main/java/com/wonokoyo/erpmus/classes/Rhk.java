package com.wonokoyo.erpmus.classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Rhk implements Parcelable {
    private Mitra mitra;
    private List<Sekat> sekats;
    private PakanDanKematian pakanDanKematian;
    private List<Nekropsi> nekropsies;

    public Rhk() {

    }

    protected Rhk(Parcel in) {
    }

    public static final Creator<Rhk> CREATOR = new Creator<Rhk>() {
        @Override
        public Rhk createFromParcel(Parcel in) {
            return new Rhk(in);
        }

        @Override
        public Rhk[] newArray(int size) {
            return new Rhk[size];
        }
    };

    public Mitra getMitra() {
        return mitra;
    }

    public void setMitra(Mitra mitra) {
        this.mitra = mitra;
    }

    public List<Sekat> getSekats() {
        return sekats;
    }

    public void setSekats(List<Sekat> sekats) {
        this.sekats = sekats;
    }

    public PakanDanKematian getPakanDanKematian() {
        return pakanDanKematian;
    }

    public void setPakanDanKematian(PakanDanKematian pakanDanKematian) {
        this.pakanDanKematian = pakanDanKematian;
    }

    public List<Nekropsi> getNekropsies() {
        return nekropsies;
    }

    public void setNekropsies(List<Nekropsi> nekropsies) {
        this.nekropsies = nekropsies;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
