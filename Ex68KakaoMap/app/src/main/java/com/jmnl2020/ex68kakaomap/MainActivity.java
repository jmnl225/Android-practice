package com.jmnl2020.ex68kakaomap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.kakao.util.maps.helper.Utility.getPackageInfo;

public class MainActivity extends AppCompatActivity {

    //카카오 지도 뷰
    MapView mapView; //카카오지도는 AVD에서는 동작하지 않습니다.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //키 해시를 얻어오는 메소드 호출
        String keyHash = getKeyHash(this);
        //로그 기록남기기 (longcat 창에 기록을 남길 수 있음. 마치 콘솔창처럼 ㅇㅋ)
        Log.i("TAG",keyHash);

        //맵뷰객체 생성
        mapView = new MapView(this);

        //맵 뷰가 놓여질 뷰그룹 찾아오기
        RelativeLayout mapcontainer = findViewById(R.id.map_container);

        //맵 뷰 추가하기
        mapcontainer.addView(mapView);



        //지도의 중심점 변경
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.5,126.97), true);

        //줌레벨 변경
        mapView.setZoomLevel(17, true);

        //줌인
        mapView.zoomIn(true);
        mapView.zoomOut(true);


        //마커(POI) 표시하기
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("미래능력개발교육원");
        marker.setTag(10); // 식별자
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(321.11,11.43));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);

        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(marker);

        mapView.setPOIItemEventListener(new MapView.POIItemEventListener() {
            @Override
            public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
                Toast.makeText(MainActivity.this, ""+mapPOIItem.getItemName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

            }

            @Override
            public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

            }

            @Override
            public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

            }
        });

    }

    //키해드 자바코드로 얻어오는 메소드[ 라이브러리를 추가한 후에 사용 가능한 코드 ]
    public static String getKeyHash(final Context context) {
        PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Log.w("TAG", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
        return null;
    }

}
