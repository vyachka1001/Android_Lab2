package com.rpodmp.viarbitski.rpodmp2.ui.tracks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rpodmp.viarbitski.rpodmp2.R;
import com.rpodmp.viarbitski.rpodmp2.adapters.TracksListAdapter;
import com.rpodmp.viarbitski.rpodmp2.databinding.FragmentTracksChartBinding;
import com.rpodmp.viarbitski.rpodmp2.db.AppDatabase;

import java.net.ConnectException;

public class TracksChartFragment extends Fragment {

    private FragmentTracksChartBinding binding;
    private TracksChartViewModel tracksChartViewModel;
    private TracksListAdapter tracksListAdapter;
    private SearchView searchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.chart_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search_tracks_action);
        searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tracksListAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        searchView.setQuery("", true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_tracks_action:
                try {
                    tracksChartViewModel.fetchTracks(AppDatabase.getInstance(getContext()).trackDao());
                    tracksListAdapter.notifyDataSetChanged();

                } catch (ConnectException ex) {
                    View view = getActivity().findViewById(R.id.nav_view);
                    Toast.makeText(view.getContext(), "No Connection!", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return true;
            case R.id.clean_tracks_action:
                tracksChartViewModel.deleteTracks(AppDatabase.getInstance(getContext()).trackDao());
                tracksListAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tracksChartViewModel =
                new ViewModelProvider(this).get(TracksChartViewModel.class);

        binding = FragmentTracksChartBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        RecyclerView tracksListView = binding.tracksRecyclerView;
        tracksListAdapter = new TracksListAdapter(getActivity(), tracksChartViewModel.getTracks(AppDatabase.getInstance(getContext()).trackDao()), false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        tracksListView.setLayoutManager(layoutManager);
        tracksListView.setAdapter(tracksListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}