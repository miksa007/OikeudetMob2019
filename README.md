# OikeudetMob2019

Sovellus demoaa kamerankäytön oikeuksien kyselyä. Nappulaa painettaessa sovellus kysyy oikeutta käyttää kameraa.

Muutettu kolmea tiedostoa
* AndroidManifest.xml 
* Lisätty rivi     `<uses-permission android:name="android.permission.CAMERA" />`

* activity_main.xml
* Lisätty:
```xml
   <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="32dp"
        android:onClick="otaKuva"
        android:text="Button"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```

* Mainactivity.java tiedostoon
* Luokka muuttujat
```java
    public final String TAG="softa(Git)";
    private static final int REQUEST_CAMERA_PERMISSION = 1;
```

* Nappulan toiminta
```java
    /**
     * Nappulan painallus kysyy vain oikeuksia kameran käyttöön - Ei tee muuta
     * @param view
     */
    public void otaKuva(View view){
        Log.d(TAG, "otaKuva()");
        requestCameraPermission();
    }
```

* Oikeuksiien kysely
```java
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
```

* Oikeuksien kyselyyn saatujen vastauksien käsittely
```java
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
```

