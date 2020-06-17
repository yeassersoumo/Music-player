package com.soumo.videoapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.soumo.videoapplication.R;
import com.soumo.videoapplication.activity.SongPlayActivity;
import com.soumo.videoapplication.model.Song;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    RequestOptions options ;
    private Context mContext ;
    private List<Song> mData ;


    public Adapter(Context mContext, List lst) {


        this.mContext = mContext;
        this.mData = lst;
        options = new RequestOptions();


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.song_list,parent,false);

        final MyViewHolder viewHolder = new MyViewHolder(view) ;

        viewHolder.view_container.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, SongPlayActivity.class);
                i.putExtra("song_video",mData.get(viewHolder.getAdapterPosition()).getVideo_url());

                mContext.startActivity(i);

            }
        }

        );

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(mData.get(position).getId());
        holder.title.setText(mData.get(position).getTitle());
        holder.category.setText(mData.get(position).getSubcategory());


        // load image from the internet using Glide
        Glide.with(mContext).load(mData.get(position).getImage_url()).apply(options).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id,title,category;
        ImageView image;


        LinearLayout view_container;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.srlID);
            title = itemView.findViewById(R.id.titleId);
            category = itemView.findViewById(R.id.catagoryId);
            image = itemView.findViewById(R.id.imageId);


            view_container = itemView.findViewById(R.id.container);


        }
    }
}
