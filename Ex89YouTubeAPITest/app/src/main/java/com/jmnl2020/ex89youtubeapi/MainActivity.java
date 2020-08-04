package com.jmnl2020.ex89youtubeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

public class MainActivity extends AppCompatActivity {

    //YouTubePlayerView는 반드시 YouTubeBaseActivity 안에서만 보여짐 따라서.. 현재 액티비티의 extends 상속을 YouTubeBaseActivity 로 설정해야함.
    // YouTubeBaseActivity 는 androidx. 를 사용한 것이 아니라 그냥 Activity 를 받은 것.
    // 즉, androidx에 관련된 모든 작업 불가능 ㅠ (Support. ~~~~)

    YouTubePlayerView youTubeView;

    YouTubePlayerFragment youTubeFragment; //내부적으로 View를 갖고있기 때문에 더욱 자주 사용됨. (YouTubeBaseActivity를 사용하지 않아도 됨!)
    YouTubePlayerFragment youTubeFragment2;

    YouTubeThumbnailView thumbnailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thumbnailView= findViewById(R.id.thumlView);

        thumbnailView.initialize("thumb", new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo("QgESPJQ0-6c");
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });


        //youYubePlayerFragment가 일반 Fragment 여서 Support 버전을 관리하는 getSupportFragmentManager 사용이 불가능.
        youTubeFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtubeFragment);
        youTubeFragment.initialize("first", new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo("vgXwWQhaLxs"); //
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });



//        youTubeView = findViewById(R.id.youtubeView);
//
//        youTubeView.initialize("first", new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                youTubePlayer.cueVideo("vgXwWQhaLxs"); // [https://www.youtube.com/watch?v=vgXwWQhaLxs] 영상 링크의 아이디.
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//            }
//        });

    }

    public void clickBtn(View view) {
        startActivity(new Intent(this, YouTubeDataActivity.class));
    }
}
