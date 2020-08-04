package com.jmnl2020.ex73cameraapitest;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

//SurfaceView : 고속버퍼뷰 (카메라의 미리보기를 빠르게 그려내는 뷰)
public class MyCameraView extends SurfaceView implements SurfaceHolder.Callback {

    //SurfaceView가 보여줄 뷰들을 관리하는 관리자객체
    SurfaceHolder holder;

    //Camera . hardware !! import
    Camera camera;

    public MyCameraView(Context context) {
        super(context);
    }

    public MyCameraView(Context context, AttributeSet attrs) {
        super(context, attrs);

        holder = getHolder(); //surface 관리자 객체
        holder.addCallback(this); // new CallbackListener 익명크래스를 만들어야하는데.. 그러지 않고
        // 대신에 상위 클래스 MyCameraView에 implements로 콜백메소드를 상속받음. 따라서 자기 자신에게 능력을 주어져서 클래스 자체를 줌.

    }

    public MyCameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyCameraView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //이 뷰가 화면에 보여질 때 발동.
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //카메라 객체 & 셔터 열기!
        camera = Camera.open(0);// [0:back / 1: front]

        //미리보기 설정

        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //surfaceCreated()실행 후에 뷰의 사이즈가 결정되었을 때 발동
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //카메라 미리보기 시작!
        camera.startPreview();

    }

    //이 뷰가 화면에 보여지지 않을 때 발동.
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //미리보기 종료
        camera.startPreview();

        //카메라 셔터 닫기
        camera.release();
        camera= null;

    }
}
