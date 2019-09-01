package com.sepon.regnumtollplaza.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sepon.regnumtollplaza.ChittagongActivity;
import com.sepon.regnumtollplaza.Chittagong_Axel;
import com.sepon.regnumtollplaza.ChorshindduActivity;
import com.sepon.regnumtollplaza.ManikGong_Axel;
import com.sepon.regnumtollplaza.Plaza;
import com.sepon.regnumtollplaza.R;

import java.util.List;

public class PlazaAdapter extends RecyclerView.Adapter<PlazaAdapter.PlazaViewHolder> {

    private List<Plaza> plazaList;
    Context context;

    public PlazaAdapter(List<Plaza> plazaList, Context context) {
        this.plazaList = plazaList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlazaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.plazaview, viewGroup, false);
        PlazaViewHolder plazaViewHolder = new PlazaViewHolder(view);
        return plazaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PlazaViewHolder holder, final int position) {
            holder.plazaname.setText(plazaList.get(position).getName());

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String plaza =plazaList.get(position).getName();
                    if (plaza.equals("Chittagong")){
                        Intent intent = new Intent(context, ChittagongActivity.class);
                        intent.putExtra("plazaName", plaza);
                        context.startActivity(intent);
                    }else if (plaza.equals("ManikGong")){
                        Intent intent = new Intent(context, ManikGong_Axel.class);
                        intent.putExtra("plazaName", plaza);
                        context.startActivity(intent);
                    }else if (plaza.equals("Chorshinddu")){
                        Intent intent = new Intent(context, ChorshindduActivity.class);
                        intent.putExtra("plazaName", plaza);
                        context.startActivity(intent);
                    }

                }
            });
    }

    @Override
    public int getItemCount() {
        return plazaList.size();
    }

    public class PlazaViewHolder extends RecyclerView.ViewHolder {
        ImageView plazaimage;
        ImageView circleImageView;
        TextView plazaname;
        CardView view;

        public PlazaViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.plazaimage);
            plazaname = itemView.findViewById(R.id.plazaname);
            view = itemView.findViewById(R.id.view);
        }
    }
}
