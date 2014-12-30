package com.wiblumkt.barescaba;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaDeBares2 extends FragmentActivity implements LocationListener {
    Location location;
    String provider;


    double longitud;
    double lattitud;
    // Might be null if Google Play services APK is not available.
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa);
        // Getting LocationManager object
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        // Creating an empty criteria object
        Criteria criteria = new Criteria();

        // Getting the name of the provider that meets the criteria
        provider = locationManager.getBestProvider(criteria, true);

        if(provider!=null && !provider.equals("")){

            // Get the location from the given provider
            location = locationManager.getLastKnownLocation(provider);

            locationManager.requestLocationUpdates(provider, 20000, 1, this);

            lattitud = location.getLatitude();
            longitud = location.getLongitude();

            if(location!=null) {
                onLocationChanged(location);
            } else {
                Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
        }


        // Seteo incial del mapa
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        LatLng miUbicacion = new LatLng(lattitud, longitud);//-34.614736, -58.457433);
        // pongo una marca en el mapa
        mMap.addMarker(new MarkerOptions().position(miUbicacion).title("YO").draggable(true).snippet("Aca estoy"));
        // Move the camera instantly to hamburg with a zoom of 15.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(miUbicacion, 20));

        // Zoom in, animating the camera.
       // mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        mMap.setMyLocationEnabled(true);

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
