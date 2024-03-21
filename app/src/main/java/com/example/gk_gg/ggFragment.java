package com.example.gk_gg;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ggFragment extends Fragment {

    private GoogleMap mMap;
    private static final LatLng DEFAULT_LOCATION = new LatLng(10.762622, 106.660172);
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private boolean locationPermissionGranted = false;
    private Polyline polyline; // Đường vẽ trên bản đồ

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gg, container, false);

        // Tạo SearchView
        SearchView searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Xử lý khi người dùng gửi yêu cầu tìm kiếm
                Geocoder geocoder = new Geocoder(getActivity());
                List<Address> addresses;
                try {
                    addresses = geocoder.getFromLocationName(query, 1);
                    if (!addresses.isEmpty()) {
                        Address address = addresses.get(0);
                        LatLng location = new LatLng(address.getLatitude(), address.getLongitude());

                        // Di chuyển camera đến vị trí được tìm kiếm
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));

                        // Đánh dấu vị trí được tìm kiếm
                        markLocation(location, query);
                    } else {
                        Toast.makeText(getActivity(), "No results found", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Xử lý khi người dùng thay đổi văn bản trong thanh tìm kiếm
                return false;
            }
        });

        // Tạo SupportMapFragment để hiển thị bản đồ
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.my_map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                mMap = googleMap;
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                MarkerOptions markerOptions = new MarkerOptions().position(DEFAULT_LOCATION).title("Default Location");
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 15));

                // Tạo Spinner để chọn loại bản đồ
                Spinner mapTypeSpinner = view.findViewById(R.id.map_type_spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                        R.array.map_types, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mapTypeSpinner.setAdapter(adapter);

                mapTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                break;
                            case 1:
                                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                                break;
                            case 2:
                                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                                break;
                            case 3:
                                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                                break;
                            case 4:
                                mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Xử lý khi không có gì được chọn
                    }
                });

                // Xử lý khi người dùng nhấn vào bản đồ
                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        String message = "Chosen location: Latitude " + latLng.latitude + ", Longitude " + latLng.longitude;
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        markLocation(latLng, "Custom Location");
                    }
                });


            }
        });

        // Button cho phép tìm đường từ vị trí hiện tại đến điểm mong muốn
        Button directionButton = view.findViewById(R.id.direction_button);
        directionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LatLng destination = new LatLng(10.7777, 106.6666);
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + destination.latitude + "," + destination.longitude);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    Toast.makeText(getActivity(), "Google Maps app is not installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Button để hiển thị vị trí hiện tại của người dùng
        Button currentLocationButton = view.findViewById(R.id.current_location_button);
        currentLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentLocation();
            }
        });

        return view;
    }

    // Phương thức để lấy vị trí hiện tại của người dùng
    private void getCurrentLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
                        markLocation(currentLocation, "Current Location");
                    } else {
                        Toast.makeText(getActivity(), "Couldn't get current location", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    // Xử lý khi người dùng cấp quyền truy cập vị trí
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationPermissionGranted = true;
                getCurrentLocation();
            } else {
                Toast.makeText(getActivity(), "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Phương thức để đánh dấu vị trí trên bản đồ
    private void markLocation(LatLng location, String title) {
        MarkerOptions markerOptions = new MarkerOptions().position(location).title(title);
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }





}
