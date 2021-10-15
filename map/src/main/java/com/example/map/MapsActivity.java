package com.example.map;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.map.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //See terrain view map
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        // Add a marker in my home and move the camera
        LatLng myHome = new LatLng(20.9524428, 105.7609551);
//        drawCircle(myHome);
//        mMap.addMarker(new MarkerOptions().position(myHome).title("Marker in my home :3")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.common_full_open_on_phone))); // Set icon for the marker
        //Go to the Latlng and zoom in

        Bitmap bitmap = Bitmap.createBitmap(200,50, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0,0,200,50,paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        canvas.drawText("Welcome", 30, 40,paint);

          mMap.addMarker(new MarkerOptions().position(myHome).title("Marker in my home :3")
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))); // Set icon for the marker
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myHome, 12));
        mMap.getUiSettings().setZoomControlsEnabled(true);



    }

    private void drawCircle (LatLng latLng){
        CircleOptions circleOptions = new CircleOptions()
                                            .center(latLng)
                                            .radius(300)
                                            .fillColor(Color.BLACK);
        mMap.addCircle(circleOptions);
    }
}