// Generated by view binder compiler. Do not edit!
package com.rpodmp.viarbitski.rpodmp2.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.rpodmp.viarbitski.rpodmp2.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ArtistItemBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final TextView artistName;

  @NonNull
  public final TextView artistPlaycount;

  @NonNull
  public final ImageView artistThumbnail;

  @NonNull
  public final RelativeLayout artistViewBackground;

  @NonNull
  public final RelativeLayout artistViewForeground;

  @NonNull
  public final ImageView playcountImage;

  private ArtistItemBinding(@NonNull FrameLayout rootView, @NonNull TextView artistName,
      @NonNull TextView artistPlaycount, @NonNull ImageView artistThumbnail,
      @NonNull RelativeLayout artistViewBackground, @NonNull RelativeLayout artistViewForeground,
      @NonNull ImageView playcountImage) {
    this.rootView = rootView;
    this.artistName = artistName;
    this.artistPlaycount = artistPlaycount;
    this.artistThumbnail = artistThumbnail;
    this.artistViewBackground = artistViewBackground;
    this.artistViewForeground = artistViewForeground;
    this.playcountImage = playcountImage;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ArtistItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ArtistItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.artist_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ArtistItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.artist_name;
      TextView artistName = ViewBindings.findChildViewById(rootView, id);
      if (artistName == null) {
        break missingId;
      }

      id = R.id.artist_playcount;
      TextView artistPlaycount = ViewBindings.findChildViewById(rootView, id);
      if (artistPlaycount == null) {
        break missingId;
      }

      id = R.id.artist_thumbnail;
      ImageView artistThumbnail = ViewBindings.findChildViewById(rootView, id);
      if (artistThumbnail == null) {
        break missingId;
      }

      id = R.id.artist_view_background;
      RelativeLayout artistViewBackground = ViewBindings.findChildViewById(rootView, id);
      if (artistViewBackground == null) {
        break missingId;
      }

      id = R.id.artist_view_foreground;
      RelativeLayout artistViewForeground = ViewBindings.findChildViewById(rootView, id);
      if (artistViewForeground == null) {
        break missingId;
      }

      id = R.id.playcount_image;
      ImageView playcountImage = ViewBindings.findChildViewById(rootView, id);
      if (playcountImage == null) {
        break missingId;
      }

      return new ArtistItemBinding((FrameLayout) rootView, artistName, artistPlaycount,
          artistThumbnail, artistViewBackground, artistViewForeground, playcountImage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
