package com.h5200003.batuhan_keskin_final.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Element{
    //element modelimde gerekli bilgileri json dosyama göre düzenliyorum
    @SerializedName("isim")
    @Expose
    private String isim;
    @SerializedName("kutle")
    @Expose
    private String kutle;
    @SerializedName("sembol")
    @Expose
    private String sembol;
    @SerializedName("resimUrl")
    @Expose
    private String resimUrl;
    @SerializedName("elementBilgi")
    @Expose
    private String elementBilgi;

    public String getElementBilgi() { return elementBilgi; }

    public void setElementBilgi(String elementBilgi) {
        this.elementBilgi = elementBilgi;
    }

    public String getIsim() { return isim; }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getKutle() { return kutle; }

    public void setKutle(String kutle) {
        this.kutle = kutle;
    }

    public String getSembol() {
        return sembol;
    }

    public void setSembol(String sembol) {
        this.sembol = sembol;
    }

    public String getResimUrl() {
        return resimUrl;
    }

    public void setResimUrl(String resimUrl) {
        this.resimUrl = resimUrl;
    }

}