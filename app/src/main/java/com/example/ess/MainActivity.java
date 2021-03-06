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
                   Log.i("?????????", "????????????");
                   if( conn != null){
                       Log.i("?????????", "????????????");
                       conn.setUseCaches(false);
                       conn.setConnectTimeout(10000);
                       conn.setRequestMethod("POST");
                       conn.setDoInput(true);  // ??????????????? ????????? ????????? ??????
                       conn.setDoOutput(true);  // ????????? ????????? ??? ?????? ??????, ???????????? POST ????????? ???
                       conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                       OutputStream out = conn.getOutputStream();
                       out.write("ename=?????????".getBytes("utf-8"));
                       out.flush();

                       int resCode = conn.getResponseCode();
                       Log.i("????????????", ""+resCode);
                       if (resCode == HttpURLConnection.HTTP_OK) {
                           Log.i("?????????", "????????????");
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
                       Log.i("?????????", "??????????????????");
                   }
               }catch (Exception ex){
                   Log.e("????????????", ""+ex);
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
               System.out.println("??????!");
               ex.printStackTrace();
           }


        }


    }
  

