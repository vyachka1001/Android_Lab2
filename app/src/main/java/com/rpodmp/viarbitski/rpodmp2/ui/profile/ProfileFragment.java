package com.rpodmp.viarbitski.rpodmp2.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.rpodmp.viarbitski.rpodmp2.R;
import com.rpodmp.viarbitski.rpodmp2.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private String imageUrl = "https://sun9-81.userapi.com/s/v1/if1/V-8tiLnjm7het28n33YnjD96Ll0WHanvN5UGKWRatPN3X6ca6-XESse8BRZZwyEnmvW5oQZ0.jpg?size=852x1280&quality=96&type=album";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Button favouritesButton = binding.favouritesButton;
        favouritesButton.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.navigation_favourites);
        });
        Glide.with(getContext()).load(imageUrl)
                .placeholder(R.drawable.ic_baseline_face_24)
                .error(R.drawable.ic_baseline_face_24)
                .centerCrop().into((ImageView) view.findViewById(R.id.profile_photo));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}