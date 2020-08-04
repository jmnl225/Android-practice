package com.jmnl2020.ex66geocoding;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText etAddress;
    EditText etLat, etLng;

    double lat, lng;
    double lat2, lng2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAddress = findViewById(R.id.et_address);
        etLat = findViewById(R.id.et_lat);
        etLng = findViewById(R.id.et_lng);

    }

    public void clickBtn(View view) {
        //주소를 입력받아서 좌표로 바꿈 (주소 -> 좌표) : 지오코딩

        String addr= etAddress.getText().toString();

        //지오코딩 작업을 수행하는 객체생성
        Geocoder geocoder= new Geocoder(this, Locale.KOREA); // Locale : 지역

        try { // surround add catch
            List<Address> addresses = geocoder.getFromLocationName(addr,3);
            StringBuffer stringBuffer = new StringBuffer();

            for (Address t: addresses){
                stringBuffer.append( t.getLatitude()+", "+ t.getLongitude()+"\n" );
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(stringBuffer.toString()).setPositiveButton("OK", null).create().show();

            ////////////////////////////
            //지도앱으로 보여주기위해 좌표값 멤버변수에 대입
            lat = addresses.get(0).getLatitude();
            lng = addresses.get(0).getLongitude();



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clickBtn2(View view) {
        //좌표를 입력받아 주소로 바꿈 : 역지오코딩

        lat2 = Double.parseDouble(etLat.getText().toString());
        lng2 = Double.parseDouble(etLng.getText().toString());

        Geocoder geocoder = new Geocoder(this, Locale.KOREA);

        try {
            List<Address> addresses =geocoder.getFromLocation(lat2, lng2, 3);

            StringBuffer buffer= new StringBuffer();
            for (Address t : addresses ){

                //t가 가진 정보가 너무 많아서 buffer에게 하나씩 받아넣기
                buffer.append(t.getCountryName()+"\n");
                buffer.append(t.getPostalCode()+"\n");
                buffer.append(t.getAddressLine(0) + "\n"); // 주소1
                buffer.append(t.getAddressLine(1) + "\n"); // 주소2 - 없으면 null
                buffer.append(t.getAddressLine(2) + "\n"); // 주소3
                buffer.append("--------------------\n");
            }

            new AlertDialog.Builder(this).setMessage(buffer.toString()).setPositiveButton("OK", null).create().show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void clickShow(View view) {
        // 지도 앱 실행하기
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);

        Uri uri = Uri.parse("geo:"+lat+","+lng+"?q="+lat+","+lng);
        intent.setData(uri);

        startActivity(intent); //디바이스에 설치된 지도 앱이 실행됨


    }

    public void clickShow2(View view) {
        //지도앱 실행하기

        Uri uri = Uri.parse("geo:"+lat2+","+lng2+"?q="+lat2+","+lat2+"&z=10"); //&z == zoom ( 범위: 1~25 / 시골은 한계가 있음)

        Intent intent = new Intent(Intent.ACTION_VIEW, uri ); //action, data 줄여쓰기

        startActivity(intent);

    }
}
