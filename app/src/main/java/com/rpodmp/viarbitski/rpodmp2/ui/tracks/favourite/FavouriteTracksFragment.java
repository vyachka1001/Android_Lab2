package com.rpodmp.viarbitski.rpodmp2.ui.tracks.favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rpodmp.viarbitski.rpodmp2.R;
import com.rpodmp.viarbitski.rpodmp2.adapters.TracksListAdapter;
import com.rpodmp.viarbitski.rpodmp2.databinding.FragmentTracksChartBinding;
import com.rpodmp.viarbitski.rpodmp2.db.AppDatabase;


public class FavouriteTracksFragment extends Fragment {

    private FragmentTracksChartBinding binding;
    private FavouriteTracksViewModel favouriteTracksViewModel;
    private SearchView searchView;
    private TracksListAdapter tracksListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.favourites_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search_favourites_action);
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
            case R.id.clean_favourites_action:
                favouriteTracksViewModel.deleteFavourites(AppDatabase.getInstance(getContext()).trackDao());
                tracksListAdapter.notifyDataSetChanged();
                return true;
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favouriteTracksViewModel =
                new ViewModelProvider(this).get(FavouriteTracksViewModel.class);

        binding = FragmentTracksChartBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        RecyclerView tracksListView = binding.tracksRecyclerView;
        tracksListAdapter = new TracksListAdapter(getActivity(), favouriteTracksViewModel.getFavouriteTracks(AppDatabase.getInstance(getContext()).trackDao()), true);
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