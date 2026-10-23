package com.example.seminar04;

import java.io.Serializable;

public class Medicament implements Serializable {
   private boolean cuReteta; //checkbox cu reteta sau fara
    private double pret;
    private String denumire;
    private String tipUtilizare;
   private  boolean disponibil;

    public Medicament(){
        this.cuReteta=true;
        this.pret=0;
        this.denumire="anonim";
        this.tipUtilizare="-";
        this.disponibil=false;
    }

    public Medicament(boolean cuReteta,String denumire, double pret, String tipUtilizare, boolean disponibil) {
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
}
