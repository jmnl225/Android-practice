package com.jmnl2020.ex65locationtestfusedapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.browse.MediaBrowser;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    //Google Fused Location Library: 적절한 위치정보제공자를 선정하여 위치정보를 제공
    // (위치정보 제공자를 선택할 필요 Xx - 구글에서 제공)
    // 외부 라이브러리 추가 : play-services-location 라이브러리

    // *주의* 디바이스에 google play store앱이 없으면 실행이 안 됨 !

    GoogleApiClient googleApiClient; // 위치정보관리 객체 (LocationManager 역할)

    FusedLocationProviderClient providerClient; //위치정보 제공자 객체 (알아서 적절한 위치정보 제공자를 선택)

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);

        //위치정보 제공을 위한 퍼미션작업 추가
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ){
            int permissionResult = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            if(permissionResult != PackageManager.PERMISSION_GRANTED){
                String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
                requestPermissions(permissions, 10);
            }
        }

    }// onCreate method

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 10:
                if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                    Toast.makeText(this, "위치정보 사용을 거부했습니다. \n사용자의 위치탐색 기능이 제한됩니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    public void clickBtn(View view) {
        //Fused API를 이용해서 내 위치 정보 얻어오기 (실시간 갱신)

        //위치정보관리 객체 생성을 위해 빌더객체 생성(마치 다이얼로그 빌더처럼)
        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this);

        //1. 구글 API 사용 키 설정
        builder.addApi(LocationServices.API); // 공개 키 사용
        //2. 위치정보 연결 성공 리스너
        builder.addConnectionCallbacks(connectionCallbacks); //4. connectionCallbacks 입력.

        //5. 위치정보 연결 실패도 듣는 리스너를 만들어줘야함
        builder.addOnConnectionFailedListener(connectionFailedListener); //7. 실패 리스너 입력

        //8. 위치정보 관리 객체 생성
        googleApiClient = builder.build();

        //준비 끝!! 이제 위치정보 취득을 위한 연결 시도
        googleApiClient.connect();
        //연결이 성공하면 connectionCallbacks 리스너(연결성공리스너) 객체의 onConnect()method 실행

        //위치정보 제공자 객체 얻어오기
        providerClient = LocationServices.getFusedLocationProviderClient(this);



    }

    //3. 위치정보를 얻기 위한 연결시도에 성공 여부를 듣는 리스너 객체
    GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
        @Override
        public void onConnected(@Nullable Bundle bundle) {
            // 연결에 성공했을 때 (아직 위치정보를 얻은 건 아님!)
            Toast.makeText(MainActivity.this, "위치정보 탐색 시작!", Toast.LENGTH_SHORT).show();

            // 9. 위치정보제공자 객체에게 최적의 제공자를 선택하는 기준 설정
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    //높은 정확도를 최우선시
            locationRequest.setInterval(5000); // 위치정보 탐색 주기: 5초마다


            //위치정보 제공자 객체에게 실시간 위치정보를 요청
            providerClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
            //11. 10에서 갱신되는 locationCallback 메소드를 받아서 적어넣기. 그리고 Looper는 이미 할당된 객체 데려와 적음

        }

        @Override
        public void onConnectionSuspended(int i) {
            //연결이 유예되었을 때

        }
    };

    //6. 연결 시도에 실패하는 것을 듣는 리스너 객체 생성
    GoogleApiClient.OnConnectionFailedListener connectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Toast.makeText(MainActivity.this, "위치정보 탐색을 시작할 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    };


    //10. 위치정보가 갱신되는 것을 듣는 리스너
    LocationCallback locationCallback = new LocationCallback(){
        //위치정보결과를 받았을 때 호출되는 메소드
        @Override
        public void onLocationResult(LocationResult locationResult) {

            Location location = locationResult.getLastLocation();
            double latitude = location.getLatitude();
            double logitude = location.getLongitude();

            tv.setText(latitude + ", "+ logitude);

            super.onLocationResult(locationResult);
        }
    };

    //12. 마지막~~~~~ 액티비티가 화면에 보이지 않을떄 (종료될 때) 위치정보 갱신 안 하도록.


    @Override
    protected void onPause() {
        super.onPause();

        if(providerClient != null) providerClient.removeLocationUpdates(locationCallback);

    }
}
