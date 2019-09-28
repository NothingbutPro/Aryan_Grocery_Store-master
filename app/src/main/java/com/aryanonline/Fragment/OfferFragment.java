package com.aryanonline.Fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aryanonline.Adapter.OffersAdapter;
import com.aryanonline.Config.BaseURL;
import com.aryanonline.MainActivity;
import com.aryanonline.Model.Offers;
import com.aryanonline.R;
import com.aryanonline.RegisterActivity;
import com.aryanonline.util.AppPreference;
import com.aryanonline.util.JSONParser;
import com.aryanonline.util.NameValuePair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class OfferFragment extends Fragment {
    RecyclerView recoffer;
    OffersAdapter offersAdapter;
    ArrayList<Offers> offersList = new ArrayList<>();
    public OfferFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offer, container, false);
        new GETAllOffer().execute();
        recoffer = view.findViewById(R.id.recoffer);
        return view;


    }
      public class GETAllOffer extends AsyncTask<String, String, String> {

          ProgressDialog dialog;

          protected void onPreExecute() {
              dialog = new ProgressDialog(getActivity());
              dialog.show();

          }

          protected String doInBackground(String... arg0) {

              try {

                  URL url = new URL("http://aryanonline.co.in/aryan-store/index.php/api/offers");

                  JSONObject postDataParams = new JSONObject();



                  Log.e("postDataParams", postDataParams.toString());

                  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                  conn.setReadTimeout(15000 /* milliseconds*/);
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

                      BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                      StringBuilder result = new StringBuilder();
                      String line;
                      while ((line = r.readLine()) != null) {
                          result.append(line);
                      }
                      r.close();
                      return result.toString();

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

                  // JSONObject jsonObject = null;
                  Log.e("SendJsonDataToServer>>>", result.toString());
                  try {
                      JSONObject jsonObject = new JSONObject(result);
                      String responce = jsonObject.getString("responce");
                      JSONArray jsonArray = jsonObject.getJSONArray("data");
                      for(int p=0;p<jsonArray.length();p++)
                      {
                          JSONObject jsonObject1 = jsonArray.getJSONObject(p);
                          offersList.add(new Offers(jsonObject1.getString("id"),jsonObject1.getString("location_offers")
                          ,jsonObject1.getString("type"),jsonObject1.getString("status")));
                      }
                      LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                      llm.setOrientation(LinearLayoutManager.VERTICAL);
                      recoffer.setLayoutManager(llm);
                      offersAdapter = new OffersAdapter(getActivity() ,offersList );
                      recoffer.setAdapter(offersAdapter);

                  } catch (JSONException e) {

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
