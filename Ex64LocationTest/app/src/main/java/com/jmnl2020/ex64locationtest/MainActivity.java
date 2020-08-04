package com.jmnl2020.ex64locationtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final int REQ_PERMISSION = 10;
    //퍼미션에 쓰는 final 상수

    TextView tvProviders;

    //위치정보 관리자

    LocationManager locationManager;

    TextView tvBest;

    Boolean isEnter = false; // 특정 위치에 들어갔는가?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. 위치정보관리자 객체 소환
        tvProviders = findViewById(R.id.tv_providers);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //device가 제공하는 위치정보제공자의 종류 확인
        // gps, network, passive
        List<String> providers = locationManager.getAllProviders();
        String s = "";
        for (String provider : providers) {
            s = provider + ", ";
        }

        tvProviders.setText(s);

        //위치정보 제공자 중에서 베스트 제공자 얻어오기
        //베스트를 선정하는 기준(criteria)  /

        Criteria criteria = new Criteria();
        criteria.setCostAllowed(true);
        criteria.setAccuracy(criteria.NO_REQUIREMENT);
        criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
        criteria.setAltitudeRequired(true); // 고도에 대한 위치가 필요한가?

        String bestProvider = locationManager.getBestProvider(criteria, true);
        tvBest = findViewById(R.id.tv_best);
        tvBest.setText(bestProvider);

        //Best 위치정보 제공자를 얻으려면 위치정보제공에 대한 퍼미션작업

        //위치정보는 [동적퍼미션- 앱 을 실행할 때 다이얼로그를 통해 사용자에게 위치]
        //Android Manifest.xml 퍼미션은 먼저 처리해뒀어야함.

        //동적 퍼미션 작업 (마시멜로우 버전 이상)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //위치정보에 대한 허가를 받았는지 스스로 체크
            int permissionResult = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            if (permissionResult != PackageManager.PERMISSION_GRANTED) {
                //퍼미션을 요청하는 다이얼로그 화면 보이게하는 메소드 호출]
                String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
                requestPermissions(permissions, REQ_PERMISSION);
                //REQ_PERMISSION final 상수로 만들어서 사용
            }
        }


    }//onCreate method end.

    //requestPErmassions() 메소드를 통해 보여진 다이얼로그에서
    //허가/거부 여부를 선택했을때 자동으로 실행되는 메소드
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQ_PERMISSION:
                //이 메소드의 세번쨰 파라미터에 결과가 있음.
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "위치정보 제공에 동의하지 않았습니다. 이 앱의 일부기능이 제한됩니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "위치정보 제공에 동의하였습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }


    public void clickBtn(View view) {//내 위치 얻어내기

        TextView tvMyLocation = findViewById(R.id.tv_mylocation);

        //위치정보(위도&경도) 객체 참조변수
        Location location = null;

        // location = locationManager.getLastKnownLocation("gps");
        // 위 코드를 사용하려면 반드시 퍼미션체크 코드가 자바에 명시적으로 존재해야만함. (퍼미션허가여부와 별개로.)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (locationManager.isProviderEnabled("gps")) {
            //GPS 사용이 가능한지 물어보고! 가능하다면!
            location = locationManager.getLastKnownLocation("gps");
        } else if(locationManager.isProviderEnabled("network")){
            location = locationManager.getLastKnownLocation("network");
        }

        if(location == null){
            tvMyLocation.setText("내 위치를 찾지 못했습니다.");
        }else{
            //위도, 경도를 얻어오기
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            tvMyLocation.setText(latitude+", "+longitude);

        }

    }


    public void clickBtn2(View view) {
        //내 위치 자동갱신
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        } //퍼미션 받았는지 확인코드 복붙~

        if(locationManager.isProviderEnabled("gps")){
            locationManager.requestLocationUpdates("gps",5000, 2, locationListener);
            //위치프로바이더 타입 , 위치정보를 얼마마다 갱신할까? 최소 시간(거의초단위), 최소 어느정도 거리를 이동했을 때 갱신할까?;
        }else if(locationManager.isProviderEnabled("network")){
            locationManager.requestLocationUpdates("network", 5000, 2, locationListener);
        }


    }

    //갱신된 위치정보를 듣는 리스너 멤버객체
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            TextView tvauto = findViewById(R.id.tv_automylocation);

            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            tvauto.setText(latitude + ", "+ longitude);

            // 특정 지점에 들어갔을 때 이벤트 발생
            // 왕십리역 좌표: 37.5612, 127.0382

            // 내 위치(lat, lng)와 왕십리 역 좌표 사이에 실제 거리(m)를 계산할 수 있음

            float[] result = new float[3]; //계산 결과를 넣을 빈 float배열
            Location.distanceBetween(latitude, longitude, 37.5612,127.0382, result);

            //계산 결과를 가진 results[0] 에 두 좌표사이의 m거리가 계산되어있음
            if (result[0] < 50){ //두 좌표 사이의 거리가 50m 이내인가?

                if(isEnter == false){ //50m 안에 있어도 한 번 적용되었으면 다시는 알람을 띄우지 않도록.
                    new AlertDialog.Builder(MainActivity.this).setMessage("축하합니다!! 이벤트 달성!").setPositiveButton("OK", null).create().show();
                    isEnter = true;
                }

            }else { //50m 밖으로 나가면 다시 false로 변경
                if(isEnter) isEnter = false;
            }

        }

       @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            //위치 프로바이더가 바뀌었을때
        }

        @Override
        public void onProviderEnabled(String provider) {
            //프로바이더가 될때

        }

        @Override
        public void onProviderDisabled(String provider) {
            //프로바이더가 안 될때

        }
    };

    //////////////////////

    public void clickBtn3(View view) {
        //내 위치 자동 갱신 종료
        locationManager.removeUpdates(locationListener);
    }
}//Activity end.
