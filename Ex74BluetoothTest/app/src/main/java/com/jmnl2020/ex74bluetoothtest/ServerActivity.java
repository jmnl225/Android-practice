package com.jmnl2020.ex74bluetoothtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.UUID;

public class ServerActivity extends AppCompatActivity {

    //블루투스 하드웨어 장치에 대한 식별자 UUID
    static final UUID BT_UUID= UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    TextView tv;

    BluetoothAdapter bluetoothAdapter;

    //서버소켓에서 사용할 참조변수
    ServerThread serverThread;

    BluetoothServerSocket serverSocket;
    BluetoothSocket socket;

    //데이터를 주고받기위한 스트림 (자료형단위로 보낼 수 있는 Stream)
    DataInputStream dis;
    DataOutputStream dos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        tv= findViewById(R.id.tv);

        //제목줄 제목변경
        getSupportActionBar().setTitle("Server");

        //브루투스 관리자객체 소환
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //안드로이드 기종이 너무 많아서 블루투스에서 Default
        if(bluetoothAdapter == null ){
            Toast.makeText(this, "이 기기에는 블루투스가 없음", Toast.LENGTH_SHORT).show();
            finish(); // 바로 이 곳에 액티비티가 종료되지 않음. 그래서 아래 return
            return;
        }

        //위에서 리턴되지 않았다면 : bluetooth가 있다는 것.
        //블루투스 장치가 켜져있는지 체크 및 On 요청
        if(bluetoothAdapter.isEnabled()){
            //서버소켓 생성작업 실행 -> 2. 메소드 만들기
            createServerSocket(); //3. 조건충족실행
        }else { // 블루투스를 장치에서 ON 을 선택하도록 하는 화면으로 전환
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 100);
        }

    }//onCreate method.

    @Override //intent가 다녀왔을 때 resultcode가 다를 때
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // resultCode가 디바이스마다 다를 경우가 있음. 그럴 때 textview에 찍어내보자!
        // tv.settext(""+resultcode); 허용 / 거부 / 취소일 경우를 모두 코드를 살펴볼 수 있음.

        //3.
        switch (requestCode){
            case 100:
                if(resultCode == RESULT_CANCELED){ // 여기에 적힌
                    Toast.makeText(this, "블루투스를 허용하지 않았습니다. \n앱을 종료합니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }else { // 블루투스를 켰다면
                    //서버 소켓 생성작업
                    createServerSocket();
                }
                break;

            case 200:
                if(resultCode == RESULT_CANCELED){
                    Toast.makeText(this, "블루투스탐색을 허용하지 않았습니다. \n다른장치에서 이 장치를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    //2. 서버소켓 생성작업 메소드
    void createServerSocket(){

        //통신작업은 반드시 별도의 Thread가 진행해야함.
        serverThread = new ServerThread();
        serverThread.start(); // runmethod 발동!

        //이 기기를 다른 장치에서 검색할 수 있도록 허용하는 화면 실행. (Activity) 실행
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300); //300초간 검색 허용
        //(원래는 버튼을 만들어서 눌렀을 때 검색이 허용되는 걸로)

        startActivityForResult (intent, 200);


    }

    //서버소켓작업 및 통신을 하는 별도의 Thread 클래스 설계 : inner class
    class ServerThread extends Thread{
        @Override
        public void run() {
            //서버소켓 생성
            try {//RadioFrequency
                serverSocket = bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord("SERVER", BT_UUID);
                // UUID는 블루투스 회사마다 다를 수 있으므로 잘 찾아와야함. 구글 고!

                //연결이 잘 되었으면 메세지 표시. 하지만 별도의 Thread에서는 화면에 출력 불가 -> runnable 어쩌구 Thread 생성해야함.
                // 하지만 메세지를 보낼 일이 많다면? -> runOnUI 메소드를 가진 객체 생성
                setUI("서버소켓이 생성되었습니다.\n");

                //클라이언트의 접속을 기다리기
                socket = serverSocket.accept();// 커서가 여기에서 대기
                setUI("클라이언트가 접속했습니다.\n");

                //접속된 socket을 이용해서 통신하기위해 무지개로드 만들기
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());

                // 스트림을 통해서 원하는 데이터 전송하거나 받기 가능! -> 보낸 client에게 감
                String msg = dis.readUTF();
                int num = dis.readInt();

                setUI("클라이언트가 보낸 메세지: "+msg+"\n========================\n" + num);

                dis.close();
                dos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }//runmethod

        //UI Thread로 메세지 출력하는 기능

        void setUI(final String msg){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv.append(msg); //익명클래스 안에서는 지역변수를 사용 불가. 따라서 final
                }
            });
        }

    }// ServerThread end.

}
