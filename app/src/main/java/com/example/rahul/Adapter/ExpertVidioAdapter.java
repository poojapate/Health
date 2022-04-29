package com.example.rahul.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rahul.Model.TotalVidioModel;
import com.example.rahul.Model.UploadVidioModel;
import com.example.rahul.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.List;

public class ExpertVidioAdapter extends RecyclerView.Adapter<ExpertVidioAdapter.ViewHolder> {

    Context context;
   List<TotalVidioModel.Datum> totalVidioModels;

    public ExpertVidioAdapter(Context context,List<TotalVidioModel.Datum> totalVidioModels) {
        this.context = context;
        this.totalVidioModels = totalVidioModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.vidiodesign,parent,false);
        return new ExpertVidioAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        LoadControl loadControl = new DefaultLoadControl();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory());
        holder.simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(context,trackSelector
                ,loadControl);


        DefaultHttpDataSourceFactory factory = new DefaultHttpDataSourceFactory("exoplayer_video");
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        Uri video = Uri.parse(totalVidioModels.get(position).getVideo());
        String Videoname =totalVidioModels.get(position).getVideoname();
        String created_at = totalVidioModels.get(position).getCreatedAt();

        MediaSource mediaSource = new ExtractorMediaSource(video,factory,extractorsFactory,null,null);

        holder.playerView.setPlayer(holder.simpleExoPlayer);

        holder.playerView.setKeepScreenOn(true);
        holder.simpleExoPlayer.prepare(mediaSource);
        holder.simpleExoPlayer.setPlayWhenReady(true);



    }

    @Override
    public int getItemCount() {
        return totalVidioModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PlayerView playerView;
        ImageView btn_fullscreen;
        String Videoname,created_at;
        private SimpleExoPlayer simpleExoPlayer;
        String mYoutubeLink = "https://inmortaltech.com/fitness-Apis/assets/workout/IMG_677779611";

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            playerView=  itemView.findViewById(R.id.player_view);
            btn_fullscreen= itemView.findViewById(R.id.bt_fullscreen);
           

        }
    }
}
