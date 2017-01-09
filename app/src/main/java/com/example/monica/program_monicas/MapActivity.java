
package com.example.monica.program_monicas;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.monica.program_monicas.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;


public class MapActivity extends Activity implements SlidingUpPanelLayout.PanelSlideListener,OnMapReadyCallback {

    private ListView mListView;
    private SlidingUpPanelLayout mSlidingUpPanelLayout;

    private View mTransparentHeaderView;
    private View mTransparentView;
    private View mSpaceView;

    private MapFragment mMapFragment;

    private GoogleMap mMap;

    private Marker marker;

    private ArrayList<Double> langitude;
    private ArrayList<Double> longitude;
    private ArrayList<String> cabang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        langitude = new ArrayList<Double>(10);
        longitude = new ArrayList<Double>(10);
        cabang = new ArrayList<String>(10);

        langitude.add(-6.219015);
        langitude.add(-6.156844);
        langitude.add(-6.187996);
        langitude.add(-2.970792);
        langitude.add(-5.415175);
        langitude.add(-6.219015);
        langitude.add(-6.156844);
        langitude.add(-6.187996);
        langitude.add(-2.970792);
        langitude.add(-5.415175);

        longitude.add(106.85577393);
        longitude.add(106.89824);
        longitude.add(106.737904);
        longitude.add(104.738297);
        longitude.add(105.254295);
        longitude.add(106.85577393);
        longitude.add(106.89824);
        longitude.add(106.737904);
        longitude.add(104.738297);
        longitude.add(105.254295);

        cabang.add("Karet Semanggi, Jakarta");
        cabang.add("Kelapa Gading, Jakarta");
        cabang.add("PX Pavilion Puri Indah");
        cabang.add("Palembang");
        cabang.add("Bandar Lampung");
        cabang.add("Karet Semanggi, Jakarta");
        cabang.add("Kelapa Gading, Jakarta");
        cabang.add("PX Pavilion Puri Indah");
        cabang.add("Palembang");
        cabang.add("Bandar Lampung");

        mListView = (ListView) findViewById(R.id.list);
        mListView.setOverScrollMode(ListView.OVER_SCROLL_NEVER);

        mSlidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.slidingLayout);
        mSlidingUpPanelLayout.setEnableDragViewTouchEvents(true);

        int mapHeight = getResources().getDimensionPixelSize(R.dimen.map_height);
        mSlidingUpPanelLayout.setPanelHeight(mapHeight); // you can use different height here
        mSlidingUpPanelLayout.setScrollableView(mListView, mapHeight);

        mSlidingUpPanelLayout.setPanelSlideListener(this);

        // transparent view at the top of ListView
        mTransparentView = findViewById(R.id.transparentView);

        // init header view for ListView
        mTransparentHeaderView = LayoutInflater.from(this).inflate(R.layout.transparent_header_view, null, false);
        mSpaceView = mTransparentHeaderView.findViewById(R.id.space);

        mListView.addHeaderView(mTransparentHeaderView);
        mListView.setAdapter(new ArrayAdapter<String>(this, R.layout.simple_list_item, cabang));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSlidingUpPanelLayout.collapsePane();
                CameraUpdate update = CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(new LatLng(langitude.get(position-1), longitude.get(position-1)), 14.0f));
                marker = mMap.addMarker(new MarkerOptions().position(new LatLng(langitude.get(position-1), longitude.get(position-1)))
                        .title("Bank Nobu").snippet(cabang.get(position - 1)));
                marker.showInfoWindow();
                mMap.moveCamera(update);
                //mMap.animateCamera(update);
            }
        });
        collapseMap();

//        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                if(marker.isInfoWindowShown()) {
//                    marker.hideInfoWindow();
//                }
//                else if(!marker.isInfoWindowShown()) {
//                    marker.showInfoWindow();
//                }
//                return true;
//            }
//        });

        mMapFragment = MapFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mapContainer, mMapFragment, "map");
        fragmentTransaction.commit();

        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            mMapFragment.getMapAsync(this);

            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setCompassEnabled(false);
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);

                for(int index=0 ; index<10 ; index++) {
                    marker = mMap.addMarker(new MarkerOptions().position(new LatLng(langitude.get(index), longitude.get(index)))
                            .title("Bank Nobu").snippet(cabang.get(index)));
                }
                //CameraUpdate update = getLastKnownLocation();
                GPSTracker gps = new GPSTracker(MapActivity.this);
                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    CameraUpdate update = CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(new LatLng(latitude, longitude), 10.0f));
                    if (update != null) {
                        mMap.moveCamera(update);
                    }
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }


            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // In case Google Play services has since become available.
        setUpMapIfNeeded();
    }

//    private CameraUpdate getLastKnownLocation() {
//        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//        criteria.setAccuracy(Criteria.ACCURACY_LOW);
//        String provider = lm.getBestProvider(criteria, true);
//        if (provider == null) {
//            return null;
//        }
//        Location loc = lm.getLastKnownLocation(provider);
//        if (loc != null) {
//            return CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(new LatLng(loc.getLatitude(), loc.getLongitude()), 14.0f));
//        }
//        return null;
//    }

    private void collapseMap() {
        mSpaceView.setVisibility(View.VISIBLE);
        mTransparentView.setVisibility(View.GONE);
    }

    private void expandMap() {
        mSpaceView.setVisibility(View.GONE);
        mTransparentView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPanelSlide(View view, float v) {
    }

    @Override
    public void onPanelCollapsed(View view) {
        expandMap();
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14f), 1000, null);
    }

    @Override
    public void onPanelExpanded(View view) {
        collapseMap();
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11f), 1000, null);
    }

    @Override
    public void onPanelAnchored(View view) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}
