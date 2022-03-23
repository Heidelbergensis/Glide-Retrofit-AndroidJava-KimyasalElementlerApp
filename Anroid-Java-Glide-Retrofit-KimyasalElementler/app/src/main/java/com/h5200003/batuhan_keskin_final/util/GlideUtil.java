package com.h5200003.batuhan_keskin_final.util;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
public class GlideUtil {
//glide utilimin tanımı.
    //resmi indirip göster adında bir method tanımlıyorum.
    //bu method'u mainactivityde cağırarak kapak fotoğrafımı indiriyorum.
    public static void resmiIndiripGoster (Context context, String resimUrl, ImageView
            hangiImageView )
    {
        Glide.with(context)
                .load( resimUrl)
                .centerCrop()
                .into( hangiImageView );
    }
}
