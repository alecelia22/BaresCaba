package com.wiblumkt.barescaba;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by ajcelia on 29/12/2014.
 */
public class MapaDeBares extends Fragment {
    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mapa, container, false);

        return rootView;
    }

    public void setmMap(GoogleMap mMap) {
        this.mMap = mMap;
    }
}