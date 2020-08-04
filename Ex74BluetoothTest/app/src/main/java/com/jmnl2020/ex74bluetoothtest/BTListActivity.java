package com.jmnl2020.ex74bluetoothtest;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class BTListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> deviceList = new ArrayList<>();

    ArrayAdapter adapter;

    BluetoothAdapter bluetoothAdapter;
    Set<BluetoothDevice> devices;

    //브로드캐스트 멤버변수
    DicoveryREsultReceiver dicoveryREsultReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_t_list);

        listView = findViewById(R.id.listview);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, deviceList);
        listView.setAdapter(adapter);

        //블루투스 아답터 소환
        bluetoothAdapter= BluetoothAdapter.getDefaultAdapter();

        // 이미 페어링 되어있는 디바이스들을 리스트에 추가
        //디바이스 안에는 이름과 Mac을 갖고있음. 따라서 그걸 set에 device들을 하나씩 받아넣음.

        //set :
        devices = bluetoothAdapter.getBondedDevices();
        for( BluetoothDevice  device : devices ){
            String name = device.getName();
            String address = device.getAddress();

            deviceList.add( name + "\n" + address );

        }

        //새로운 장치를 찾은 결과는 운영체제에서 Bradcast를 함!
        //그러므로 이걸 듣기위해서는 Broadcast Receover가 필요.
        //Bluetooth의 장치검색 결과는 동적(java 언어에서 등록한) Receiver만 가능함.

        //장치를 찾았다는 방송 듣는 필터
        dicoveryREsultReceiver = new DicoveryREsultReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND); //블루투스 디바이스를 찾았어!! 하는 방송.

        registerReceiver(dicoveryREsultReceiver, filter); //Manifest.xml 말고 자바에서 리시버를 등록. (동적 리시버)

        //탐색이 종료되었다는 방송의 필터
        IntentFilter filter2 = new IntentFilter();
        filter2.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(dicoveryREsultReceiver, filter2);
        // ** 자바에서 만들었을 경우, 필터를 따로 각각 만들어주어야함. 하나를 듣고 스위치로 갈라지는 것 안됨



        //탐색 시작!
        bluetoothAdapter.startDiscovery();

        //다이얼로그 스타일일때,
        //아웃사이드 터치했을때 cancel되지 않도록!
        setFinishOnTouchOutside(false);

        //리스트뷰에서 원하는 디바이스를 선택
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = deviceList.get(position);
                //s 문자열에 저장된 name과 mac 주소중에 주소만 분리
                String[] ss = s.split("\n");
                String address = ss[1];

                //얻어온 address를 이 액티비티를 실행했던
                // clientActivity에 전달
                // 이 액티비티를 실행했던 intent 객체를 부르기.
                Intent intent = getIntent();
                //이 intent에게 가지고 돌아갈 데이터를 추가.
                intent.putExtra("Address", address);

                //이것이 이 액티비티의 결과임을 set.
                setResult(RESULT_OK, intent);

                //setResult를 해놓지 않으면 intent를 보낸 Activity가 request 했던 result 임을 알 수 없음

                finish();

            }
        });

    }//onCreate end.

    //inner class.
    class DicoveryREsultReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            String action =intent.getAction();

            if(action.equals(BluetoothDevice.ACTION_FOUND)){
                //장치를 찾은 상황
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //intent에게 객체를 받아옴. 하지만 device 정보를 가진 연결들이 많을 수 있음. 중복되지 않도록 걸러내야함!

                //기존에 있던 장치들을 중복되지않게 가지고있는 set 객체
                boolean isAdded= devices.add(device);
                devices.add(device); //중복된 디바이스가 없다면 true 리턴
                if (isAdded) { // 추가되었다면 새로운 장치라는 뜻
                    String name = device.getName();
                    String address = device.getAddress();

                    //리스트뷰에 보여줄 데이터에 추가.
                    deviceList.add(name+"\n"+address);

                }


            }else if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)){

                Toast.makeText(context, "블루투스 탐색이 완료되었습니다.", Toast.LENGTH_SHORT).show();

            }

        }
    }

    //액티비티가 종료될 때 리시버 등록


}//class
