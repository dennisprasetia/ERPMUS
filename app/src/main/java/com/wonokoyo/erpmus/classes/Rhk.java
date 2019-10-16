package com.wonokoyo.erpmus.classes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Rhk implements Parcelable {
    private int id_rhk;
    private Mitra mitra;
    private List<Sekat> sekats;
    private PakanDanKematian pakanDanKematian;
    private List<Nekropsi> nekropsies;
    private List<Attachment> attachments;
    private List<String> listSolusi;

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

    public int getId_rhk() {
        return id_rhk;
    }

    public void setId_rhk(int id_rhk) {
        this.id_rhk = id_rhk;
    }

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

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }

    public List<String> getListSolusi() {
        return listSolusi;
    }

    public void setListSolusi(List<String> listSolusi) {
        this.listSolusi = listSolusi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}