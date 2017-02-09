package com.vmr.popularmovies.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vmr.popularmovies.R;
import com.vmr.popularmovies.api.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Ramos on 2/8/17.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder>  {

    private List<Movie> mMovieList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;

    public MovieListAdapter(Context context, List<Movie> list){
        mInflater = LayoutInflater.from(context);
        mMovieList = list;
        mContext = context;
    }

    public void updateList(List<Movie> list){
        mMovieList = list;
        notifyDataSetChanged();
    }

    @Override
    public MovieListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_movie_list_content, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.ViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        Picasso.with(mContext)
                .load(movie.getPoster_path())
                .into(holder.ivThumbnail);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;

        public ViewHolder(View view) {
            super(view);
            ivThumbnail = (ImageView)view.findViewById(R.id.ivThumbnail);
        }


    }
}
