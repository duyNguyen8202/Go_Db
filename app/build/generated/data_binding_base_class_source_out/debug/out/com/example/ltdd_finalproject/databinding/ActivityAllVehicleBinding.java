// Generated by view binder compiler. Do not edit!
package com.example.ltdd_finalproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.ltdd_finalproject.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAllVehicleBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final GridView gridview;

  @NonNull
  public final SearchView searchVehicle;

  private ActivityAllVehicleBinding(@NonNull LinearLayout rootView, @NonNull GridView gridview,
      @NonNull SearchView searchVehicle) {
    this.rootView = rootView;
    this.gridview = gridview;
    this.searchVehicle = searchVehicle;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAllVehicleBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAllVehicleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_all_vehicle, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAllVehicleBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.gridview;
      GridView gridview = ViewBindings.findChildViewById(rootView, id);
      if (gridview == null) {
        break missingId;
      }

      id = R.id.searchVehicle;
      SearchView searchVehicle = ViewBindings.findChildViewById(rootView, id);
      if (searchVehicle == null) {
        break missingId;
      }

      return new ActivityAllVehicleBinding((LinearLayout) rootView, gridview, searchVehicle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
