package uqac.dim.travelpicture;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
/*
public class Map extends SupportMapFragment implements OnMapReadyCallback {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout file as the content view.


        // Get a handle to the fragment and register the callback.
        getMapAsync(this);

    }

    // Get a handle to the GoogleMap object and display marker.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
    }
}


*/
 import androidx.appcompat.app.AppCompatActivity;

  import android.os.Bundle;

  import com.google.android.gms.maps.GoogleMap;
  import com.google.android.gms.maps.OnMapReadyCallback;
  import com.google.android.gms.maps.SupportMapFragment;
  import com.google.android.gms.maps.model.LatLng;
  import com.google.android.gms.maps.model.MarkerOptions;

  // Implement OnMapReadyCallback.

  public class Map extends Fragment {

      MapView mMapView;
      private GoogleMap googleMap;

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
          View rootView = inflater.inflate(R.layout.fragment_map, container, false);

          mMapView = (MapView) rootView.findViewById(R.id.mapView);
          mMapView.onCreate(savedInstanceState);

          mMapView.onResume(); // needed to get the map to display immediately

          try {
              MapsInitializer.initialize(getActivity().getApplicationContext());
          } catch (Exception e) {
              e.printStackTrace();
          }

          mMapView.getMapAsync(new OnMapReadyCallback() {
              @Override
              public void onMapReady(GoogleMap mMap) {
                  googleMap = mMap;

                  // For showing a move to my location button

                  googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                  // For dropping a marker at a point on the Map
                  LatLng sydney = new LatLng(-34, 151);
                  googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                  // For zooming automatically to the location of the marker
                  CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                  googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                  // Display traffic.
                  googleMap.setTrafficEnabled(true);
              }
          });
          return rootView;
      }
      @Override
      public void onResume() {
          super.onResume();
          mMapView.onResume();
      }

      @Override
      public void onPause() {
          super.onPause();
          mMapView.onPause();
      }

      @Override
      public void onDestroy() {
          super.onDestroy();
          mMapView.onDestroy();
      }

      @Override
      public void onLowMemory() {
          super.onLowMemory();
          mMapView.onLowMemory();
      }
  }