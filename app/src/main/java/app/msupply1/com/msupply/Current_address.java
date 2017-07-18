package app.msupply1.com.msupply;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * Created by Maroof Ahmed Siddique on 1/1/2017.
 */


public class Current_address extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private TextView txtOutputLatitude, txtOutputLongitude, mCurrentAddress;
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private String lat, lon;
    private static final int REQUEST_CODE_PERMISSION = 2;
    private String mPermission = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private String add = "";
    Button saveandcontinue;

    ImageView currentlocation;
    EditText address;

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor spe;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_address);

        sharedpreferences = getSharedPreferences("msupply", Context.MODE_PRIVATE);
        spe   = sharedpreferences.edit();

        sharedPreferences = getSharedPreferences("splogin",0);
        spt   = sharedPreferences.edit();

        address = (EditText)findViewById(R.id.current_address);
        currentlocation = (ImageView)findViewById(R.id.current_location);
        saveandcontinue = (Button) findViewById(R.id.saveandcontinue);
        address.setText("");

        currentlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI();
            }
        });

        saveandcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String addresss = address.getText().toString();
                spe.putString("address", addresss);
                spe.commit();

                Intent intent = new Intent(Current_address.this,Cart.class);
                startActivity(intent);
            }
        });

        checkLocationPermission();
    }

    private void checkLocationPermission() {

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{mPermission}, REQUEST_CODE_PERMISSION);

                // If any permission above not allowed by user, this condition will execute every time, else your else part will work
            } else {
                buildGoogleApiClient();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("Req Code", "" + requestCode);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length == 1 &&
                    grantResults[0] == MockPackageManager.PERMISSION_GRANTED) {

                // Success Stuff here
                buildGoogleApiClient();
            } else {
                // Failure Stuff
            }
        }

    }


    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(300000); // Update location every ten seconds

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            lat = String.valueOf(mLastLocation.getLatitude());
            lon = String.valueOf(mLastLocation.getLongitude());

        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        lat = String.valueOf(location.getLatitude());
        lon = String.valueOf(location.getLongitude());

        double latitude =Double.parseDouble(lat);
        double longitude =Double.parseDouble(lon);
        getAddress(latitude,longitude);


    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        buildGoogleApiClient();
    }

    synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
    }

    void updateUI() {
     /*   txtOutputLatitude.setText("Latitude --> " + lat);
        txtOutputLongitude.setText("Longitude --> " +lon);*/

        address.setText(add);
    }

    public void getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            if (addresses != null && addresses.size()>0) {
                Address obj = addresses.get(0);
                add = obj.getAddressLine(0) + ",";

               // add = add + "\n" + obj.getCountryCode();
                add = add + obj.getLocality() + ",";
                add = add + "\n" + obj.getAdminArea() + ",";
                add = add + obj.getCountryName() + ",";
                add = add + "\n" + obj.getPostalCode() + ".";

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
