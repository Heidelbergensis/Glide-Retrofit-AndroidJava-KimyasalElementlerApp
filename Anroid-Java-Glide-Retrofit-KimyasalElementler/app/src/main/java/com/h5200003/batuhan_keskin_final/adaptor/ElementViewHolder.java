package com.h5200003.batuhan_keskin_final.adaptor;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.h5200003.batuhan_keskin_final.R;

public class ElementViewHolder extends RecyclerView.ViewHolder {
    //tanımlamaları yapıyorum
    TextView textIsim;
    TextView textKutle;
    TextView textSembol;
    ImageView imgElement;
    ImageView imageView;
    TextView elementBilgi;
    public ElementViewHolder (@NonNull View itemView) {
        //elementviewholder kullanımı için gerekli olan ,id'leri eşliyorum

        super(itemView);
        textIsim =itemView.findViewById( R.id.textIsim);
        textKutle =itemView.findViewById( R.id.textKutle);
        textSembol =itemView.findViewById( R.id.textSembol);
        imgElement = itemView.findViewById(R.id.imgElement);
        elementBilgi = itemView.findViewById(R.id.elementBilgi);
        imageView = itemView.findViewById(R.id.imageView);
    }
}