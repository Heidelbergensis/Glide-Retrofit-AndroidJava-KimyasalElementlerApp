package com.h5200003.batuhan_keskin_final;

import android.app.Application;
//gövdesi boş bir application sınıfı
public class ElementApp extends Application {
    ElementApp instance=null;
    public ElementApp getApp()
    {
        if(instance==null)
        {
            instance=this;
        }
        return instance;
    }
}
