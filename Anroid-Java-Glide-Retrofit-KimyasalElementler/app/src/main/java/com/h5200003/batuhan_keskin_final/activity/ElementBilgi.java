package com.h5200003.batuhan_keskin_final.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.h5200003.batuhan_keskin_final.R;

public class ElementBilgi extends AppCompatActivity  {
    //gerekli tanımlamalar
    //textview ve imageviewler için verdiğim parametreler.
    private TextView isim;
    private TextView elementBilgi;
    private ImageView imgElement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_bilgi);
        //cleancode ilkesiyle tanımladığım methodları onCreate içerisine aldım.
            isimGetir();
            bilgiGetir();
            resimiGetir();
    }
    private void isimGetir(){
        //ismiGetir methodu elementBilgi id'sine intent'in ekstrasını çağırarak getirdiğim isimi sergiler
        //intenti ve extrasını mainactivity'de tanımlamıştık
        isim = (TextView)findViewById(R.id.elementBilgi);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        //if methodu extranın null olmadığı süreçte çalıştıracağı bilgileri içerir.
        if (b!=null){
            String j =(String) b.get(getString(R.string.isim));
            isim.setText(j);
        }

    }
    private void bilgiGetir(){
        //bilgiGetir methodu bilgiACiklamaTextView id'sine intent'in ekstrasını çağırarak getirdiğim elementBilgiyi sergiler
        //intenti ve extrasını mainactivity'de tanımlamıştık

        elementBilgi = (TextView)findViewById(R.id.bilgiAciklamaTextView);

        Intent ii = getIntent();
        Bundle bb = ii.getExtras();
        if (bb!=null){
            String jj =(String) bb.get(getString(R.string.elementBilgi));
            //jsondaki html taglerinin düzgün çalışması için bu fonksiyonu kullandım.
            elementBilgi.setText(Html.fromHtml(jj));
        }

    }
    private void resimiGetir(){
        //resimiGetir methodu imageView id'sine intent'in ekstrasını çağırarak getirdiğim resimUrldeki resmi sergiler

        imgElement = findViewById(R.id.imageView);

        String resimUrl = getIntent().getStringExtra(getString(R.string.resimUrl));
        //glide ile resmi urlden yükleyip imgElementte sergiliyoruz
        Glide.with(this)
                .load(resimUrl)
                .into(imgElement);

    }

}