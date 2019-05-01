package com.example.loginregisterjava.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loginregisterjava.R;
import com.example.loginregisterjava.modal.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<Category> dataList;
    private Context context;

    CardView cardView;

    public CustomAdapter(Context context,List<Category> dataList){
        this.context = context;
        this.dataList = dataList;

    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView txtid,txtname;
        ImageView image_icon, delete, edit;
        CustomViewHolder(View itemView) {
            super(itemView);
            txtid=(TextView)itemView.findViewById(R.id.id);
            txtname=(TextView)itemView.findViewById(R.id.name);
            image_icon = (ImageView) itemView.findViewById(R.id.image_icon);
            delete = (ImageView) itemView.findViewById(R.id.delete);
            cardView = (CardView) itemView.findViewById(R.id.card_view);

        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.categriesadapter, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int i) {
        holder.txtid.setText(dataList.get(i).getId());
        holder.txtname.setText(dataList.get(i).getName());
        Picasso.with(context).load(dataList.get(i).getIcon()).into(holder.image_icon);

        cardView.setOnClickListener(new
                                            View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                }
                                            });
        holder.delete.setOnClickListener(new
                                                 View.OnClickListener() {
                                                     @Override
                                                     public void onClick(View v) {

                                                         dataList.remove(i);

                                                         notifyDataSetChanged();
                                                     }
                                                 });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

