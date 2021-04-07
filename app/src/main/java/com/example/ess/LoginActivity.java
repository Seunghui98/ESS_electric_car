package com.example.ess;


import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;

import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


import androidx.appcompat.app.AppCompatActivity;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class LoginActivity extends AppCompatActivity {
    Button mLoginBtn;
    TextView mResigettxt;
    EditText userID, userPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //버튼 등록하기
        mResigettxt = (TextView) findViewById(R.id.btn_register);
        mLoginBtn = (Button) findViewById(R.id.btn_login);
        userID = (EditText) findViewById(R.id.et_id);
        userPassword = (EditText) findViewById(R.id.et_pass);

        CustomTask task = new CustomTask();
        task.execute("root", "1234");

        //가입 버튼 눌리면
        mResigettxt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //intent 함수를 통해 register 액티비티 함수 호출
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginid = userID.getText().toString();
                String loginpwd = userPassword.getText().toString();
                try {
                    String result = new CustomTask().execute(loginid, loginpwd).get();
                    System.out.println(result);
                    if (result.equals("true")) {
                        Toast.makeText(LoginActivity.this, "로그인", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (result.equals("false")) {
                        Toast.makeText(LoginActivity.this, "아이디 or 비밀번호가 다름!", Toast.LENGTH_SHORT).show();
                        userID.setText("");
                        userPassword.setText("");
                    } else if (result.equals("noId")) {
                        Toast.makeText(LoginActivity.this, "존재하지 않는 아이디!", Toast.LENGTH_SHORT).show();
                        userID.setText("");
                        userPassword.setText("");
                    }

                } catch (Exception e) {
                }
            }
        });
    }








    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;



        @Override
        protected String doInBackground(String... strings) {
            try{
                String str = "";
                URL url = new URL("http://203.237.114.218:8080/ess/Login.jsp"); //보낼 JSP 경로
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&password="+strings[1];
                osw.write(sendMsg); //OutputStreamWriter에 담아 전송
                osw.flush();
                //jsp와 통신이 정상적으로 수행될 때 동작할 코드들
                if(conn.getResponseCode() == conn.HTTP_OK){
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();

                    //jsp에서 보낸 값을 받기
                    while((str = reader.readLine()) != null){
                        buffer.append(str);

                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode() +"에러");
                }


            } catch(MalformedURLException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }


}
