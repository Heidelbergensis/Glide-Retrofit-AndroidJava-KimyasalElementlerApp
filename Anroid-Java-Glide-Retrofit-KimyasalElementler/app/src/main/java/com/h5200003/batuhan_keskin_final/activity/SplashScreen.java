package com.h5200003.batuhan_keskin_final.activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.h5200003.batuhan_keskin_final.R;

public class SplashScreen extends AppCompatActivity {
    //burada SPLASH_TIME_OUT'u 3000 milisaniye olarak tanımlıyoruz.
    private static int SPLASH_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //buradaki intent splash ekranımız için tanımladığımız intenttir.
                //splash screen bittikten sonra mainactivity class'ına yönlendirmeyi sağlıyoruz
                Intent homeIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
            //3000 milisaniyelik timeout burada
        },SPLASH_TIME_OUT);
    }
}