package com.vmr.popularmovies.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vmr.popularmovies.R;
import com.vmr.popularmovies.api.model.Movie;

import java.util.Locale;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView imageMoviePoster;
    private TextView overviewText;
    private TextView movieTitleText;
    private TextView releaseDateText;
    private TextView voteAvgText;

    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setTitle(getString(R.string.movie_details));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageMoviePoster = (ImageView)findViewById(R.id.iv_movie_poster);
        overviewText = (TextView)findViewById(R.id.tv_overview);
        releaseDateText = (TextView)findViewById(R.id.tv_release_date);
        movieTitleText = (TextView)findViewById(R.id.tv_title);
        voteAvgText = (TextView)findViewById(R.id.tv_vote_avg);

        if(getIntent().hasExtra("movie")){
            mMovie = getIntent().getExtras().getParcelable("movie");

            overviewText.setText(mMovie.getOverview());
            releaseDateText.setText(mMovie.getRelease_date());
            movieTitleText.setText(mMovie.getTitle());
            voteAvgText.setText(String.format(Locale.getDefault(),"%d/10",mMovie.getVote_average()));

            Picasso.with(this)
                    .load(mMovie.getPoster_path())
                    .into(imageMoviePoster);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
