package com.h5200003.batuhan_keskin_final.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.h5200003.batuhan_keskin_final.model.Element;
import com.h5200003.batuhan_keskin_final.util.GlideUtil;
import com.h5200003.batuhan_keskin_final.R;

import java.util.List;

public class ElementAdaptor extends RecyclerView.Adapter<ElementViewHolder > {
    //tanımlamalar
    List<Element> elementler;
    Context context;
    OnItemClickListener onItemClickListener;

    //onItemClickListener interfacesini tanımlıyorum ve içerisine onItemClick methoduyla parametrelerini veriyorum
    public interface OnItemClickListener {
        void onItemClick(Element tiklananElement);
    }

    //ElementAdapter methodu ve içeriği
    public ElementAdaptor (List <Element> elementler, Context context,OnItemClickListener onItemClickListener) {
        //element context ve onItemClickListener tanımlarına parametre atanıyor
        this.elementler = elementler;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public ElementViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        //onCreateViewHolder içerisinde gerekli viewleri çağırıyorum
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_element,parent, false);
        ElementViewHolder elementViewHolder =new ElementViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onClickte ise bu elementleri holderlerina  ve adapterpositionlarını alıyoruz.
                onItemClickListener.onItemClick(elementler.get(elementViewHolder.getAdapterPosition()));

            }
        });
        return elementViewHolder;
    }

    @Override
    public void onBindViewHolder (@NonNull ElementViewHolder viewHolder, int position) {
        //viewholderlere isimi ,kutleyi ,sembolu ve glide ile fotoğrafları koyuyoruz.
        viewHolder.textIsim.setText( elementler.get(position).getIsim());
        viewHolder.textKutle.setText( elementler.get(position).getKutle());
        viewHolder.textSembol.setText(elementler.get(position).getSembol());
        GlideUtil.resmiIndiripGoster(context,elementler.get(position).getResimUrl(),viewHolder. imgElement);

    }
    @Override
    public int getItemCount () {
        return elementler.size();
    }


}
