package com.h5200003.batuhan_keskin_final.activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.h5200003.batuhan_keskin_final.model.Element;
import com.h5200003.batuhan_keskin_final.adaptor.ElementAdaptor;
import com.h5200003.batuhan_keskin_final.util.GlideUtil;
import com.h5200003.batuhan_keskin_final.R;
import com.h5200003.batuhan_keskin_final.network.Service;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    Context context;
    ImageView imgKapak;
    //kapak resmimin tutulduğu github sayfası
    String resimUrl ="https://raw.githubusercontent.com/Heidelbergensis/h5200003batuhankeskin/main/slide.jpg";
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init methodu içerisindeki methodlarımızı onCreate içerisinde tanımlamış olduk.
        init();

    }
    private void init(){
        //if karar durumu burada cihazın internete bağlı olup olmama durumuna göre onNoConnection methodunu çalıştıracak.
        if (!isConnected()){
            onNoConnection();
        }
        //tanımladığım kapakResminiCek ve elementleriGetir methodu init içerisinde CleanCode amacı ile tanımlandı.
        kapakResminiCek();
        elementleriGetir();
    }

    private  void kapakResminiCek()
    {
        //imgKapak id'sine bağlı olarak Glide ile resmi indirip gösterdim.
        //resim url github adresimdeki kapak fotoğrafına ait.
        //imgKapak imageView tanımlı.
        imgKapak =findViewById(R.id.imgKapak);
        GlideUtil.resmiIndiripGoster(getApplicationContext(),resimUrl,imgKapak);
    }
    //elementlerin listeleneceği alandaki RecyclerView
    //elementleri liste olarak tanımladım ve elementList parametresi verdim.
    private  void  initRecycleView(List<Element> elementList)
    {
        //recyclerView rcvElementlerin idsine bağlı
        recyclerView =findViewById(R.id.rcvElementler);
        //elementadapter classının içerisinde tanımlı contextionitemclicklistener gibi araçları kullandım
        ElementAdaptor elementAdaptor =new ElementAdaptor(elementList, getApplicationContext(), new ElementAdaptor.OnItemClickListener() {
            @Override
            //onItemClick methodunda çağrılacak verileri intent ile girdim.
            public void onItemClick(Element tiklananElement) {
                //getApplicationContext'ten alınıp ElementBilgi Class'ına aktarılacak veriler.
                Intent intent = new Intent(getApplicationContext(), ElementBilgi.class);
                //Elementin ismini,Bilgisini ve resimURlsini intent'in extrası ile aldım.
                intent.putExtra(getString(R.string.isim),tiklananElement.getIsim());
                intent.putExtra(getString(R.string.elementBilgi),tiklananElement.getElementBilgi());
                intent.putExtra(getString(R.string.resimUrl),tiklananElement.getResimUrl());
                //intent'i başlattım.
                startActivity(intent);

            }

        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(elementAdaptor);
    }
    //elementleri getiren methodum.
    //Observer
    void elementleriGetir(){
        new Service().getServiceApi().elementleriGetir().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Element>>() {
                    //element Listesini Arraylist olarak tanımladım.
                    List<Element> elementler=new ArrayList<>();

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Element> elementListParam) {
                        elementler=elementListParam;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                    @Override
                    public void onComplete() {
                        //if kalıbı içerisi çalıştığında initRecylerView methodu ile elementler serilmiş oluyor.
                        if (elementler.size()>0){
                            initRecycleView(elementler);

                        }
                    }


        });
    }
    //cihazın internet bağlanıtısını return ettiğim method.
    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(context.CONNECTIVITY_SERVICE);
        //null olma durumu,zaten bağlı olma durumu ve bağlanılıyor olma durumunu kontrol ediyorum.
        return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    @Override
    public void onBackPressed() {
        //geri tuşuna basınca çıkarılacak alertdiyalogunu tanımlıyorum
        new AlertDialog.Builder(this)
                //geri tuşuna basılınca cıkacak  yazı
                .setMessage(R.string.onBackPressed)
                .setCancelable(false)
                .setPositiveButton(R.string.onBackPressedYes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //dönülecek ekran finish ile uygulama kapatılıyor
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton(R.string.onBackPressedNo,                                                                                                                                                                                                                                                                       null).show();
    }

    public void onNoConnection()
    {
        //eğer internet yoksa ortaya çıkacak alertdialogunu tanımlıyorum
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //internet yoksa cıkacak mesaj
        builder.setMessage(R.string.onNoConnection);
        //internet yoksa cıkacak title
        builder.setTitle(R.string.onNoConnectionUyari);
        builder.setCancelable(false);
        //posisitive buton ayarlara döndürüyor
        builder.setPositiveButton(R.string.onNoConnectionSettings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //yeni intent ile startActivity'i wifi ayarlarına bağlamış oldum
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_WIFI_SETTINGS);
                        startActivity(intent);
                    }
                });
        //negatif buton ise uygulamadan cıkarıyor
        builder.setNegativeButton(R.string.onNoConnectionCikis, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
            //finish fonksiyonunu cağırıp yapıyorum.
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}