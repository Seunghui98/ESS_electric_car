package com.example.ess;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    EditText eEmailText, mPasswordText, mPasswordcheckText, mName;
    Button mregisterBtn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //액션 바 등록
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Create Account");

        actionBar.setDisplayHomeAsUpEnabled(true); //뒤로 가기 아이콘
        actionBar.setDisplayShowTitleEnabled(true); //홈 아이콘

        //파이어베이스 접근 설정
        //user = firebaseAuth.getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();
        //firebaseDatabase = FirebaseDatabase.getInstance().getReference();


    }
}