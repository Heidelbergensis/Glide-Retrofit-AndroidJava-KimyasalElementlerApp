package com.h5200003.batuhan_keskin_final.network;

import com.h5200003.batuhan_keskin_final.model.Element;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
//bu api sayesinde Githubımdaki Element.json dosyasına ulaşıyorum ve bilgileri retrofit aracılığıyla getiriyorum

public interface ServiceApi {
    @GET("Element.json")
    //mainactivity'de elementleriGetir ile bu bilgileri kullanıyorum.
    Observable<List<Element>> elementleriGetir();
}

