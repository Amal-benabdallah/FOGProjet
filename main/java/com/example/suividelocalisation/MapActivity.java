package com.example.suividelocalisation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double latitude;
    private double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Recevoir les données de localisation de l'intent ou de toute autre source
        //latitude = getIntent().getDoubleExtra("latitude",latitude);
        //longitude = getIntent().getDoubleExtra("longitude", longitude);
        // Initialiser la carte
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Liste de positions
        List<LatLng> positions = new ArrayList<>();
        positions.add(new LatLng(34.721309, 10.7172482)); // Première position
        positions.add(new LatLng(34.721, 10.718)); // Deuxième position
       

        // Ajouter des marqueurs pour chaque position dans la liste
        for (LatLng position : positions) {
            mMap.addMarker(new MarkerOptions().position(position).title("Position choisie"));
        }

        // Centrer la carte sur la première position de la liste
        if (!positions.isEmpty()) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(positions.get(0)));
        }
    }

}
