package fi.tuni.miksa.oikeudet;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public final String TAG="softa(Git)";
    private static final int REQUEST_CAMERA_PERMISSION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * if lause selitetään kuvalla
     * https://stackoverflow.com/questions/41310510/what-is-the-difference-between-shouldshowrequestpermissionrationale-and-requestp/41312851
     */
    private void requestCameraPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            Log.d(TAG,"Onko Sovellus käynnistetty aikaisemmin? on");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            Log.d(TAG,"Onko Sovellus käynnistetty aikaisemmin? ei");
            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        }
    }

    /**
     * Nappulan painallus kysyy vain oikeuksia kameran käyttöön - Ei tee muuta
     * @param view
     */
    public void otaKuva(View view){
        Log.d(TAG, "otaKuva()");
        requestCameraPermission();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.d(TAG,"Oikeuksia kysyttiin ja vastaus tuli: "+requestCode);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length != 1 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG," virhe - kerran kysytty, mutta ei oikeuksia - uusiksi... Ikuinen looppi");
                requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            }
        } else {
            Log.d(TAG,"Joidenkin muiden oikeuksien kysely... ");
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
