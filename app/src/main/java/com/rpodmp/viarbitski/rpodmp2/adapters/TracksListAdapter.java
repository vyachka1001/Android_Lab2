package com.rpodmp.viarbitski.rpodmp2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rpodmp.viarbitski.rpodmp2.R;
import com.rpodmp.viarbitski.rpodmp2.db.AppDatabase;
import com.rpodmp.viarbitski.rpodmp2.db.entity.Track;

import java.util.ArrayList;
import java.util.List;

public class TracksListAdapter extends RecyclerView.Adapter<TracksListAdapter.TrackViewHolder> implements Filterable {

    private final List<Track> tracksList;
    private List<Track> tracksListFiltered;
    private final Context context;
    private final boolean isFavourites;
    private RecyclerView recyclerView;


    private final View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = recyclerView.getChildLayoutPosition(view);
            AppDatabase.getInstance(context).trackDao().setFavourite(tracksListFiltered.get(pos).id, true);
            Toast.makeText(context, "Added to favourite.", Toast.LENGTH_SHORT).show();
        }
    };

    private final View.OnClickListener removeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = recyclerView.getChildLayoutPosition(view);
            AppDatabase.getInstance(context).trackDao().setFavourite(tracksListFiltered.get(pos).id, false);
            Track toDelete = tracksListFiltered.get(pos);
            tracksListFiltered.remove(pos);
            tracksList.remove(toDelete);
            notifyDataSetChanged();
            Toast.makeText(context, "Removed from favourites.", Toast.LENGTH_SHORT).show();
        }
    };

    public TracksListAdapter(Context context, List<Track> tracksList, boolean isFavourites) {
        this.context = context;
        this.tracksList = tracksList;
        this.tracksListFiltered = tracksList;
        this.isFavourites = isFavourites;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.track_item, parent, false);
        return new TrackViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        Track track = tracksListFiltered.get(position);
        holder.title.setText(track.name);
        holder.artist.setText(track.artist);
        holder.itemView.setOnClickListener(isFavourites ? removeListener : addListener);
        Glide.with(context).load(track.imageUrl).placeholder(R.drawable.ic_twotone_music_note_24)
                .error(R.drawable.ic_twotone_music_note_24)
                .centerCrop().into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return tracksListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    tracksListFiltered = tracksList;
                } else {
                    List<Track> filteredList = new ArrayList<>();
                    for (Track track : tracksList) {
                        if (track.name.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(track);
                        }
                    }
                    tracksListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = tracksListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                tracksListFiltered = (ArrayList<Track>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class TrackViewHolder extends RecyclerView.ViewHolder {
        public TextView title, artist;
        public RelativeLayout viewBackground, viewForeground;
        public ImageView thumbnail;

        public TrackViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.track_title);
            artist = view.findViewById(R.id.track_artist);
            viewBackground = view.findViewById(R.id.track_view_background);
            viewForeground = view.findViewById(R.id.track_view_foreground);
            thumbnail = view.findViewById(R.id.track_thumbnail);
        }
    }
}

