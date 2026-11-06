package com.example.seminar04;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Medicament implements Parcelable {
    private boolean cuReteta; //checkbox cu reteta sau fara
    private double pret;
    private String denumire;
    private String tipUtilizare;
    private boolean disponibil;

    public Medicament() {
        this.cuReteta = true;
        this.pret = 0;
        this.denumire = "anonim";
        this.tipUtilizare = "-";
        this.disponibil = false;
    }

    public Medicament(boolean cuReteta, String denumire, double pret, String tipUtilizare, boolean disponibil) {
        this.denumire = denumire;
        this.pret = pret;
        this.cuReteta = cuReteta;
        this.tipUtilizare = tipUtilizare;
        this.disponibil = disponibil;
    }

    public boolean isCuReteta() {
        return cuReteta;
    }

    public void setCuReteta(boolean cuReteta) {
        this.cuReteta = cuReteta;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getTipUtilizare() {
        return tipUtilizare;
    }

    public void setTipUtilizare(String tipUtilizare) {
        this.tipUtilizare = tipUtilizare;
    }

    public boolean isDisponibil() {
        return disponibil;
    }

    public void setDisponibil(boolean disponibil) {
        this.disponibil = disponibil;
    }

    @Override
    public String toString() {
        return denumire + " - " + pret + " lei"
                + (cuReteta ? ", cu rețetă" : ", fără rețetă")
                + ", " + tipUtilizare
                + (disponibil ? ", disponibil" : ", indisponibil");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeByte((byte) (cuReteta ? 1 : 0));
        dest.writeDouble(pret);
        dest.writeString(denumire);
        dest.writeString(tipUtilizare);
        dest.writeByte((byte) (disponibil ? 1 : 0));
    }

    protected Medicament(Parcel in) {
        cuReteta = in.readByte() != 0;
        pret = in.readDouble();
        denumire = in.readString();
        tipUtilizare = in.readString();
        disponibil = in.readByte() != 0;
    }

    public static final Creator<Medicament> CREATOR = new Creator<Medicament>() {
        @Override
        public Medicament createFromParcel(Parcel in) {
            return new Medicament(in);
        }

        @Override
        public Medicament[] newArray(int size) {
            return new Medicament[size];
        }
    };
}