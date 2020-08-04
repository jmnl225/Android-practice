package com.jmnl2020.chattest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    //구글 로그인을 위한 객체
    private GoogleSignInClient mGoogleSignInClient;

    //FireBase에서 계정 정보를 가져옴
    private FirebaseAuth mAuth;

    //onActivityResultCode를 위한 변수
    private static int RC_SIGN_IN;

    //구글 로그인 버튼
    SignInButton googleSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        googleSignInButton = (SignInButton) findViewById(R.id.googleLoginBtn);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        // ==============================================================================



        //한 번 로그인 한 기록이 있으면 다음 화면
        if(mAuth.getCurrentUser()!= null){
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        }

        //로그인 버튼 클릭
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if(i== R.id.googleLoginBtn){
                    signIn();
                }
            }
        });



    }//onCreate end.


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//        handleSignInResult(result);
//    }
//
//    private void handleSignInResult(GoogleSignInResult result) {
//        if(result.isSuccess()){
//            gotoProfile();
//        }else{
//            Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();
//        }
//    }
//
//    private void gotoProfile() {
//        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
//        startActivity(intent);
//    }

    // ===============================================================================

    //Sinin을 하고 구글로부터 정보를 받아옴
    public void signIn(){

        Log.i("google", "googleSignTest");
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    // ===============================================================================

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Result returned form launching the Intent from GoogleSignInApi.getSignInIntent(...);
        Log.i("google", "onActivityResult 체크체크");
        if(requestCode == RC_SIGN_IN ){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            GoogleSignInAccount account = null;
            try {
                account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginActivity.this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            UpdateUI(user);
                        }else {
                            //If Sign in fails, display a message to users.
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this).setMessage("로그인에 실패했습니다.");
                            builder.create().show();
                        }
                    }

                    //로그인이 완료되었으면 다음 액티비티로 intent를 옮기겠다는 코드
                    private void UpdateUI(FirebaseUser user) {
                        if(user != null){
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

    }


    public void clickLogin(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}// view End.



























