package com.example.ess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.skt.Tmap.TMapView;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity  {


    ArrayList<Gwangjuev> list = new ArrayList<Gwangjuev>();
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION};
    JSONArray jsonArray;
    TMapView tMapView;
    String jsonStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        tMapView = new TMapView(this);

        tMapView.setSKTMapApiKey("l7xx5f3a9d055d6b46749f83073c5b4dd784");
        tMapView.setCompassMode(true);
        tMapView.setIconVisibility(true);
        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setTrackingMode(true);
        tMapView.setSightVisible(true);
        relativeLayout.addView(tMapView);
        setContentView(relativeLayout);
        //MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(35.139758, 126.793716);


        final String jj = "http://203.237.114.218:8080/ess/Ev.jsp";
       jsonStr = null;
       new Thread(){
           public void run(){
               try{
                   URL url = new URL(jj);
                   HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                   Log.i("쓰레드", "접속시도");
                   if( conn != null){
                       Log.i("쓰레드", "접속성공");
                       conn.setUseCaches(false);
                       conn.setConnectTimeout(10000);
                       conn.setRequestMethod("POST");
                       conn.setDoInput(true);  // 서버로부터 입력을 받도록 설정
                       conn.setDoOutput(true);  // 서버로 출력할 수 있게 설정, 자동으로 POST 방식이 됨
                       conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                       OutputStream out = conn.getOutputStream();
                       out.write("ename=홍길동".getBytes("utf-8"));
                       out.flush();

                       int resCode = conn.getResponseCode();
                       Log.i("응답코드", ""+resCode);
                       if (resCode == HttpURLConnection.HTTP_OK) {
                           Log.i("쓰레드", "응답수신");
                           InputStream is = conn.getInputStream();
                           InputStreamReader isr = new InputStreamReader(is);
                           BufferedReader br = new BufferedReader(isr);
                           StringBuilder strBuilder = new StringBuilder();
                           String line = null;
                           while ((line = br.readLine()) != null) {
                               strBuilder.append(line + "\n");
                           }

                           jsonStr = strBuilder.toString();
                           br.close();
                           conn.disconnect();
                       }
                       Log.i("쓰레드", "응답처리완료");
                   }
               }catch (Exception ex){
                   Log.e("접속요류", ""+ex);
               }
           }
       }.start();

        ArrayList<Gwangjuev> list = new ArrayList<Gwangjuev>();
       while(jsonStr==null) {
           try {
               Thread.sleep(500);
           } catch (InterruptedException ie) {

           }
       }
           runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   processJSON(jsonStr);
               }
           });
       }

       private void processJSON(String jsonStr){

           try{

               JSONArray array = new JSONArray(jsonStr);
               for(int i=0;i<array.length();i++){
                   JSONObject jsObj = array.getJSONObject(i);
                   Gwangjuev ev;
                   String address = jsObj.getString("address");
                   String latitude = jsObj.getString("latitude");
                   String now = jsObj.getString("now");
                   String name = jsObj.getString("name");
                   String dong1 = jsObj.getString("dong1");
                   String dong2 = jsObj.getString("dong2");
                   String facility = jsObj.getString("facility");
                   String gu = jsObj.getString("gu");
                   String longitude = jsObj.getString("longitude");
                   System.out.println(name +" " + address +" " + gu);
                   ev = new Gwangjuev(i+1, name, address, gu, dong1, dong2, latitude, longitude, facility, now);
                   list.add(ev);
               }



           } catch (Exception ex){
               Log.e("JSON", ""+ex);
               System.out.println("오류!");
               ex.printStackTrace();
           }


        }


        /*
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("광산구 전기차 충전소");
        marker.setTag(0);
        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(marker);
        */
    }
    /*
    private class Task extends AsyncTask<String, String, String> {
        JSONArray ev_JsonArray;

        @Override
                protected void onPreExecute(){super.onPreExecute();}


        ContentValues id;
        String url = "http://203.237.114.218:8080/ess/Ev.jsp";
        Task() {

        }


        @Override
        protected JSONArray doInBackground(String... strUrl) {
            String result;

            try{
                URL url = new URL(strUrl[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                StringBuffer builder = new StringBuffer();

                String inputString = null;
                while((inputString = bufferedReader.readLine()) != null){
                    builder.append(inputString);
                }

                String s = builder.toString();
                ev_JsonArray = new JSONArray(s);
                System.out.println(ev_JsonArray.toString());
                conn.disconnect();
                bufferedReader.close();
                in.close();
            } catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e){
                e.printStackTrace();
            }

            return ev_JsonArray;
        }


    }

*/


