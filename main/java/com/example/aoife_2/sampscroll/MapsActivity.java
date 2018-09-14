package com.example.aoife_2.sampscroll;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        double CSLat = 52.67396833473445;
        double KBLat = 52.67274367092222;
        double SLat = 52.67316978626234;
        double GLLat = 52.67368046863771;
        double FLat = 52.67421716661167;
        double ERLat = 52.67538812112444;
        double LCLat = 52.67579469521174;
        double LBLat = 52.673104730754105;
        double SRLat = 52.67371950143999;
        double PLat = 52.67439931715282;
        double MBLat = 52.674025257184375;
        double IWLat = 52.67793809124219;
        double HSLat = 52.6780031395533;

        double CSLong = -8.575376269701565;
        double KBLong = -8.577178714159572;
        double SLong = -8.577672240618313;
        double GLLong = -8.573369977358425;
        double FLong = -8.573080298784816;
        double ERLong = -8.572940823916042;
        double LCLong = -8.57398152101382;
        double LBLong = -8.568584916475857;
        double SRLong = -8.567898270968044;
        double PLong = -8.567726609591091;
        double MBLong = -8.572103974703396;
        double IWLong = -8.568842408541286;
        double HSLong = -8.568842408541286;

        double longDest = 0, latDest = 0;

        String buildingCode = getIntent().getStringExtra("BUILDING_CODE");

        if (buildingCode.startsWith("CSG") || buildingCode.startsWith("CS1") || buildingCode.startsWith("CS2") || buildingCode.startsWith("CS3")) {
            longDest = CSLong;
            latDest = CSLat;

        } else if (buildingCode.startsWith("KBG") || buildingCode.startsWith("KB1") || buildingCode.startsWith("KB2") || buildingCode.startsWith("KB3")) {
            longDest = KBLong;
            latDest = KBLat;

        } else if (buildingCode.startsWith("GLG") || buildingCode.startsWith("GL0") || buildingCode.startsWith("GL1") || buildingCode.startsWith("GL2")) {
            longDest = GLLong;
            latDest = GLLat;

        } else if (buildingCode.startsWith("FB") || buildingCode.startsWith("FG") || buildingCode.startsWith("F1") || buildingCode.startsWith("F2")) {
            longDest = FLong;
            latDest = FLat;

        } else if (buildingCode.startsWith("ERB") || buildingCode.startsWith("ER0") || buildingCode.startsWith("ER1") || buildingCode.startsWith("ER2")) {
            longDest = ERLong;
            latDest = ERLat;

        }
        else if(buildingCode.startsWith("LCB") || buildingCode.startsWith("LC0") || buildingCode.startsWith("LC1") || buildingCode.startsWith("LC2")){
            longDest = LCLong;
            latDest = LCLat;

        }
        else if(buildingCode.startsWith("LB") || buildingCode.startsWith("LG") || buildingCode.startsWith("L1") || buildingCode.startsWith("L2")){
            longDest = LBLong;
            latDest = LBLat;

        }
        else if(buildingCode.startsWith("SR1") || buildingCode.startsWith("SR2") || buildingCode.startsWith("SR3")){
            longDest = SRLong;
            latDest = SRLat;

        }
        else if(buildingCode.startsWith("PG") || buildingCode.startsWith("PM") || buildingCode.startsWith("P1") || buildingCode.startsWith("P2")){
            longDest = PLong;
            latDest = PLat;

        }
        else if(buildingCode.startsWith("A0") || buildingCode.startsWith("AM") || buildingCode.startsWith("A1") || buildingCode.startsWith("A2") || buildingCode.startsWith("A3") || buildingCode.startsWith("B0") || buildingCode.startsWith("B1") || buildingCode.startsWith("B2") || buildingCode.startsWith("B3") || buildingCode.startsWith("CG") || buildingCode.startsWith("C0") || buildingCode.startsWith("C1") || buildingCode.startsWith("C2") || buildingCode.startsWith("DG") || buildingCode.startsWith("D0") || buildingCode.startsWith("D1") || buildingCode.startsWith("D2") || buildingCode.startsWith("EG") || buildingCode.startsWith("E0") || buildingCode.startsWith("EM") || buildingCode.startsWith("E1") || buildingCode.startsWith("E2")){
            longDest = MBLong;
            latDest = MBLat;

        }
        else if(buildingCode.startsWith("IWG") || buildingCode.startsWith("IW1") || buildingCode.startsWith("IW2")){
            longDest = IWLong;
            latDest = IWLat;

        }
        else if(buildingCode.startsWith("HSG") || buildingCode.startsWith("HS1") || buildingCode.startsWith("HS2") || buildingCode.startsWith("HS2")){
            longDest = HSLong;
            latDest = HSLat;

        }
        else if(buildingCode.startsWith("SG") || buildingCode.startsWith("S1") || buildingCode.startsWith("S2")){
            longDest = SLong;
            latDest = SLat;
        }

        GPSTracker gps = new GPSTracker(this);
        LatLng currentLoc = new LatLng (gps.getLatitude(),gps.getLongitude());
        LatLng destLoc = new LatLng(latDest, longDest);
        mMap.addMarker(new MarkerOptions().position(currentLoc).icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.addMarker(new MarkerOptions().position(destLoc));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLoc));
        addCameraToMap(destLoc);
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(currentLoc,destLoc)
                .width(5)
                );
    }

    private void addCameraToMap(LatLng currentLoc){
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(currentLoc)
                .zoom(16)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
