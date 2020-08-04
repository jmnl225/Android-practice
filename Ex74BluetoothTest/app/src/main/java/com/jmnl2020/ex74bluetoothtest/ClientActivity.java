package com.jmnl2020.ex74bluetoothtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.awt.font.TextAttribute;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ClientActivity extends AppCompatActivity {

    static final UUID BT_UUID= UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    TextView tv;

    BluetoothAdapter bluetoothAdapter;
    BluetoothSocket socket;

    DataInputStream dis;
    DataOutputStream dos;

    ClientThread clientThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        tv = findViewById(R.id.tv);

        //제목줄 변경
        getSupportActionBar().setTitle("Client");

        //블루투스 관리자객체 소환
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter == null ){
            Toast.makeText(this, "이 기기에는 블루투스가 없습니다.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        //블루투스가 켜져있는지
        if(bluetoothAdapter.isEnabled()){
            //서버 블루투스 장치를 탐색 및 결과를 리스트로 보여주는 화면(Activity : 존재하지 않으므로 직접 만들어야함) 실행
            discoveryBluetoothDevices();

        }else {
            //꺼져있는 경우 블루투스 장치 On 하는 화면실행
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 10);

        }

    }//onCreate end.

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch ( requestCode ){
            case 10:
                if(resultCode == RESULT_CANCELED){
                    Toast.makeText(this, "블루투스 사용 불가", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    //서버 블루투스 장치 탐색 빛 리스트 보는 화면 실행
                    discoveryBluetoothDevices();
                }
                break;

            case 20: //intent가 리스트뷰에 가서 result를 들고 올때 갖는 식별숫자(20)
                if (resultCode == RESULT_OK ){
                    //선택된 BT Device에 Mac 주소 얻어오기!
                    String address = data.getStringExtra("Address");

                    //Mac 주소를 받아왔으니, 이 선택받은 Mac 주소를 이용하여 Socket 생성.
                    // 통신작업은 별도 Thread가 실행
                    clientThread = new ClientThread(address);
                    clientThread.start(); //runmethod 발동 !
                }
                break;
        }
    }


    //블루투스 장치 탐색화면을 보여주는 메소드
    void discoveryBluetoothDevices(){
        Intent intent = new Intent(this, BTListActivity.class);
        startActivityForResult(intent, 20);
    }


    //inner Class
    class ClientThread extends Thread{
        String address;

        public ClientThread(String address) {
            this.address = address;
        }

        @Override
        public void run() {
            // mac 주소에 해당하는 BluetoothDevice 객체 얻어오기
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);

            //원격 디바이스와 소켓연결작업 수행
            try {
                socket = device.createInsecureRfcommSocketToServiceRecord(BT_UUID);
                socket.connect(); // 연결 시도!

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.append("서버와 연결 되었습니다~!!");
                    }
                });

                //접속된 socket을 통해 데이터를 주고받는 무지개로드 만들기
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());

                //스트림을 통해 원하느 데이터 주고받기
                dos.writeUTF(" 안녕하세요!");
                //UTF: 한글도 가능한 문자열 인코딩방식

                dos.writeInt(50);

                dos.flush();
                dos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
