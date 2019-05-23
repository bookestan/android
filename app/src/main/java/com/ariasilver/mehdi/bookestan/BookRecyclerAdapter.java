package com.ariasilver.mehdi.bookestan;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.ViewHolder> {

    private ArrayList<BookModel> books;
    private Context context;

    public BookRecyclerAdapter(ArrayList<BookModel> books, Context context) {
        this.books = books;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_advert, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookModel item = books.get(position);
        holder.txt_title.setText(item.getTitle());

        String price = item.getPrice() + " تومان";

        holder.txt_price.setText(price);

        String location_date = item.getCreateDate() + " در  " + item.getUniversity();
        holder.txt_location_date.setText(location_date);

        if (holder.img_ads != null) {
            Picasso.get().load(item.getImage()).placeholder(R.mipmap.no_picture).into(holder.img_ads);
        } else {
            holder.img_ads.setImageResource(R.mipmap.no_picture);
        }


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, DetailActivity.class);

                intent.putExtra("item", books.get(position));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        ImageView img_ads;
        TextView txt_title;
        TextView txt_price;
        TextView txt_location_date;

        private ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View view) {
            super(view);

            txt_title = (TextView) view.findViewById(R.id.txt_title);
            txt_price = (TextView) view.findViewById(R.id.txt_price);
            txt_location_date = (TextView) view.findViewById(R.id.txt_location_date);
            img_ads = (ImageView) view.findViewById(R.id.img_ads);

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), true);
            return true;
        }
    }
}
