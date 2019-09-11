package com.aryanonline;

import android.app.ProgressDialog;
import android.app.Activity;

import org.json.JSONException;

import android.content.IntentFilter;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class Warranty_Activity extends AppCompatActivity {
    TextView warrant;

    //    private void callInstamojoPay(String email, String phone, String amount, String purpose, String buyername) {
//        final Activity activity = this;
//        InstamojoPay instamojoPay = new InstamojoPay();
//        IntentFilter filter = new IntentFilter("ai.devsupport.instamojo");
//        registerReceiver(instamojoPay, filter);
//        JSONObject pay = new JSONObject();
//        try {
//            pay.put("email", email);
//            pay.put("phone", phone);
//            pay.put("purpose", purpose);
//            pay.put("amount", amount);
//            pay.put("name", buyername);
//       pay.put("send_sms", true);
//      pay.put("send_email", true);
// } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        initListener();
//        instamojoPay.start(activity, pay, listener);
//    }
//
//    InstapayListener listener;
////
////
////    InstapayListener listener;
//
//
//    private void initListener() {
//        listener = new InstapayListener() {
//            @Override
//            public void onSuccess(String response) {
//                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG)
//                        .show();
//            }
//
//            @Override
//            public void onFailure(int code, String reason) {
//                Toast.makeText(getApplicationContext(), "Failed: " + reason, Toast.LENGTH_LONG)
//                        .show();
//            }
//        };
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warranty_);
        // Call the function callInstamojo to start payment here
        warrant = findViewById(R.id.warrant);
        new GETWARRANTy().execute();
    }

    private class GETWARRANTy extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;

        protected void onPreExecute() {
            dialog = new ProgressDialog(Warranty_Activity.this);
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("http://aryanonline.co.in/aryan-store/index.php/api/get_replacement?product_id="+getIntent().getStringExtra("pro_id"));

                JSONObject postDataParams = new JSONObject();
                //  postDataParams.put("product_id", getIntent().getStringExtra("pro_id"));
//                postDataParams.put("password", password.getText().toString());


                Log.e("postDataParams", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000  /*milliseconds*/);
                conn.setConnectTimeout(15000  /*milliseconds*/);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        StringBuffer Ss = sb.append(line);
                        Log.e("Ss", Ss.toString());
                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }



        }



        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                dialog.dismiss();

                JSONObject jsonObject = null;
                Log.e("PostRegistration", result.toString());
                try {

                    jsonObject = new JSONArray(result).getJSONObject(0);
                    String warrantstr = jsonObject.getString("offers_warranty");
                    warrant.setText(warrantstr);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while (itr.hasNext()) {

                String key = itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }
    }
}