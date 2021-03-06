package com.vmr.popularmovies.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.vmr.popularmovies.R;
import com.vmr.popularmovies.api.client.MovieFetchAsync;
import com.vmr.popularmovies.api.model.Movie;
import com.vmr.popularmovies.ui.adapter.MovieListAdapter;
import com.vmr.popularmovies.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity implements MovieFetchAsync.MovieFetchListener, MovieListAdapter.MovieListListener{

    private RecyclerView mMoviesList;
    private MovieFetchAsync mTask;
    private MovieListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        setTitle(getString(R.string.movies));
        mMoviesList = (RecyclerView)findViewById(R.id.rv_movies);


        int columns = getResources().getInteger(R.integer.gallery_columns);
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new GridLayoutManager(this, columns);
        mMoviesList.setLayoutManager(layoutManager);
        mAdapter = new MovieListAdapter(this,new ArrayList<Movie>(),this);
        mMoviesList.setAdapter(mAdapter);

        fetchMovie(MovieFetchAsync.MOST_POPULAR);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_movie_list, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_top_rated:
                fetchMovie(MovieFetchAsync.TOP_RATED);
                item.setChecked(true);
                break;
            case R.id.sort_most_popular:
                fetchMovie(MovieFetchAsync.MOST_POPULAR);
                item.setChecked(true);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMovieFetched(List<Movie> movies) {
        mAdapter.updateList(movies);
    }

    private void fetchMovie(String sortBy){
        if(NetworkUtils.isNetworkAvailable(this)) {
            mTask = new MovieFetchAsync(sortBy, this);
            mTask.execute();
        }
        else{
            Toast.makeText(this,"No internet connection available, try again",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMovieSelected(Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("movie",movie);
        startActivity(intent);
    }
}
