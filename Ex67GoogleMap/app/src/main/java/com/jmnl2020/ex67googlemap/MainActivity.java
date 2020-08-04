package com.jmnl2020.ex67googlemap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    //1. GoogleMap Library부터 추가 [play-services-maps]]

    //2. 구글 지도 사용에 대한 API 키발급

    //구글 지도를 제어하는 객체 참조변수
    GoogleMap GMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // SupportMapFragment안에 있는 GoogleMap객체를 얻어오기
        //우선 xml에 있는 SupportMapFragment를 참조해야함.

        //자바에서 fragment를 제어하기위해서는 반드시! 꼭!! fragmentManager가 필요~!!

        FragmentManager fragmentManager = getSupportFragmentManager();

        final SupportMapFragment mapFragment= (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);

        //Async = 비동기 = 별도의 thread가 일을 함.
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // 내 멤버변수에 얻어온 GoogleMap 대입
                GMap = googleMap;

                //원하는 좌표 객체 생성
                LatLng seoul = new LatLng(37.56, 126.97);

                //마커 옵션 객체 생성
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(seoul);
                markerOptions.title("서울");
                markerOptions.snippet("대한민국의 수도");

                //지도에 마커 추가
                GMap.addMarker(markerOptions);

                //원하는 좌표 위치로 카메라이동
                //GMap.moveCamera(CameraUpdateFactory.newLatLng(seoul));

                //카메라 이동을 스무스하게 효과를 주면서 zoom까지 적용

                GMap.animateCamera(CameraUpdateFactory.newLatLngZoom(seoul, 17));

                //마커 여러개 추가 가능
                LatLng mrhi= new LatLng(37.5608, 127.0346);

                MarkerOptions mo= new MarkerOptions();
                mo.position(mrhi);
                mo.title("미래능력개발 교육원");
                mo.snippet("http://www.mrhi.or.kr");
                mo.icon(BitmapDescriptorFactory.fromResource(R.drawable.down));
                mo.anchor(0.5f, 1.0f);

                Marker marker =GMap.addMarker(mo); //추가된 마커객체를 리턴해줌
                //마커를 클릭하지 않아도 이미 infoWindow가 보이도롣
                marker.showInfoWindow();

                //지도의 마커정보창을 클릭했을 때 반응하기
                GMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        String title = marker.getTitle();

                        if(title.equals("미래능력개발 교육원")){
                            //교육원 홈페이지로 이동 -> 웹브라우저 실행

                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            Uri uri = Uri.parse("http://www.mrhi.or.kr");
                            intent.setData(uri);

                            startActivity(intent);

                        }

                    }
                });

                //카메라 위치 변경
                GMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mrhi, 14));

                //정보창의 커스텀모양을 만들고 싶다면..
                //정보창 모양을 만들어주는 Adapter 객체 생성

                MyInfoWinAdapter adapter = new MyInfoWinAdapter(MainActivity.this);
                GMap.setInfoWindowAdapter(adapter);

                //줌컨트롤 보이도록 설정
                UiSettings settings = GMap.getUiSettings();
                settings.setZoomControlsEnabled(true);

                //내 위치 보여주기 [위치정보제공 퍼미션 작업 필요 - 동적퍼미션 필요]
                GMap.setMyLocationEnabled(true);

            }
        });

    }
}
