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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rpodmp.viarbitski.rpodmp2.R;
import com.rpodmp.viarbitski.rpodmp2.db.entity.Artist;

import java.util.ArrayList;
import java.util.List;

public class ArtistsListAdapter extends RecyclerView.Adapter<ArtistsListAdapter.ArtistViewHolder> implements Filterable {

    private final List<Artist> artistsList;
    private List<Artist> artistsListFiltered;
    private final Context context;

    public ArtistsListAdapter(Context context, List<Artist> artistsList) {
        this.context = context;
        this.artistsList = artistsList;
        this.artistsListFiltered = artistsList;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artist_item, parent, false);
        return new ArtistViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        Artist artist = artistsListFiltered.get(position);
        holder.name.setText(artist.name);
        holder.playcount.setText(String.valueOf(artist.playCount));
        Glide.with(context).load(artist.imageUrl).placeholder(R.drawable.ic_baseline_face_24)
                .error(R.drawable.ic_baseline_face_24)
                .centerCrop().into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return artistsListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    artistsListFiltered = artistsList;
                } else {
                    List<Artist> filteredList = new ArrayList<>();
                    for (Artist artist : artistsList) {
                        if (artist.name.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(artist);
                        }
                    }
                    artistsListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = artistsListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                artistsListFiltered = (ArrayList<Artist>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {
        public TextView name, playcount;
        public RelativeLayout viewBackground, viewForeground;
        public ImageView thumbnail;

        public ArtistViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.artist_name);
            playcount = view.findViewById(R.id.artist_playcount);
            viewBackground = view.findViewById(R.id.artist_view_background);
            viewForeground = view.findViewById(R.id.artist_view_foreground);
            thumbnail = view.findViewById(R.id.artist_thumbnail);
        }
    }
}

