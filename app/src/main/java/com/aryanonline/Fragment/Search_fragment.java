package com.aryanonline.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aryanonline.Adapter.Product_adapter;
import com.aryanonline.Config.BaseURL;
import com.aryanonline.Model.Product_model;
import com.aryanonline.AppController;
import com.aryanonline.MainActivity;
import com.aryanonline.R;
import com.aryanonline.util.ConnectivityReceiver;
import com.aryanonline.util.CustomVolleyJsonRequest;

import static com.aryanonline.Fragment.Product_fragment.promodecolourandsizeList;


public class Search_fragment extends Fragment {

    private static String TAG = Search_fragment.class.getSimpleName();

    private EditText et_search;
    private Button btn_search;
    private RecyclerView rv_search;

    private List<Product_model> product_modelList = new ArrayList<>();
    private Product_adapter adapter_product;

    public Search_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        ((MainActivity) getActivity()).setTitle(getResources().getString(R.string.search));

        et_search = (EditText) view.findViewById(R.id.et_search);
        btn_search = (Button) view.findViewById(R.id.btn_search);
        rv_search = (RecyclerView) view.findViewById(R.id.rv_search);
        rv_search.setLayoutManager(new LinearLayoutManager(getActivity()));

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String get_search_txt = et_search.getText().toString();

                if(TextUtils.isEmpty(get_search_txt)){
                    Toast.makeText(getActivity(), getResources().getString(R.string.enter_keyword), Toast.LENGTH_SHORT).show();
                }else{
                    if(ConnectivityReceiver.isConnected()){
                        product_modelList.clear();
                        makeGetProductRequest(get_search_txt);
                    }else{
                        ((MainActivity) getActivity()).onNetworkConnectionChanged(false);
                    }
                }

            }
        });

        return view;
    }

    /**
     * Method to make json object request where json response starts wtih {
     */
    private void makeGetProductRequest(String search_text) {

        // Tag used to cancel the request
        String tag_json_obj = "json_product_req";

        Map<String, String> params = new HashMap<String, String>();
        params.put("search", search_text);

        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.POST,
                BaseURL.GET_PRODUCT_URL, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    Boolean status = response.getBoolean("responce");
                    if (status) {

                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<Product_model>>() {
                        }.getType();
                        Log.e("product" , "json"+response.getString("data"));
//                        product_modelList = gson.fromJson(response.getString("data"), listType);
                        for(int p=0;p<response.getJSONArray("data").length();p++) {
                            Log.e("offer pres", "" + response.getJSONArray("data").getJSONObject(p).getString("offers_persent"));
                            Log.e("responce proid", "" + response.getJSONArray("data").getJSONObject(p).getString("product_id"));
                            // Toast.makeText(getActivity(), "offer discount at json is"+response.getJSONArray("data").getJSONObject(p).getString("offers_persent"), Toast.LENGTH_SHORT).show();
//                            if(response.getJSONArray("data").getJSONObject(p).getString("offers_persent").)
                            product_modelList.add(new Product_model(response.getJSONArray("data").getJSONObject(p).getString("product_id")
                                    , response.getJSONArray("data").getJSONObject(p).getString("product_name"),
                                    response.getJSONArray("data").getJSONObject(p).getString("product_description"),
                                    response.getJSONArray("data").getJSONObject(p).getString("product_image"),
                                    response.getJSONArray("data").getJSONObject(p).getString("category_id"),
                                    response.getJSONArray("data").getJSONObject(p).getString("in_stock")
                                    , response.getJSONArray("data").getJSONObject(p).getString("price"),
                                    response.getJSONArray("data").getJSONObject(p).getString("unit_value"),
                                    response.getJSONArray("data").getJSONObject(p).getString("unit"),
                                    response.getJSONArray("data").getJSONObject(p).getString("increament"),
                                    response.getJSONArray("data").getJSONObject(p).getString("Mrp"),
                                    response.getJSONArray("data").getJSONObject(p).getString("today_deals"),
                                    response.getJSONArray("data").getJSONObject(p).getString("offers_cat"),
                                    response.getJSONArray("data").getJSONObject(p).getString("deals_description"),
                                    response.getJSONArray("data").getJSONObject(p).getString("offers_cat_desc"),
                                    response.getJSONArray("data").getJSONObject(p).getString("emi"),
                                    response.getJSONArray("data").getJSONObject(p).getString("warranty"),
                                    response.getJSONArray("data").getJSONObject(p).getString("product_offer_image"),
                                    response.getJSONArray("data").getJSONObject(p).getString("p_offer_description"),
                                    response.getJSONArray("data").getJSONObject(p).getString("top_product_status"),
                                    response.getJSONArray("data").getJSONObject(p).getString("date"),
                                    response.getJSONArray("data").getJSONObject(p).getString("offers_persent"),
                                    response.getJSONArray("data").getJSONObject(p).getString("offers_warranty"),
                                    response.getJSONArray("data").getJSONObject(p).getString("stock"),
                                    response.getJSONArray("data").getJSONObject(p).getString("title"),
                                    response.getJSONArray("data").getJSONObject(p).getString("parent"),
                                    response.getJSONArray("data").getJSONObject(p).getString("s_clolor"),
                                    response.getJSONArray("data").getJSONObject(p).getString("s_size"),
                                    response.getJSONArray("data").getJSONObject(p).getString("cloth_color"),
                                    response.getJSONArray("data").getJSONObject(p).getString("cloth_size"),
                                    response.getJSONArray("data").getJSONObject(p).getString("replacement_policy"),
                                    response.getJSONArray("data").getJSONObject(p).getString("cod"),
                                    response.getJSONArray("data").getJSONObject(p).getString("delivery_charg"),
                                    response.getJSONArray("data").getJSONObject(p).getString("standard_d_date"),
                                    response.getJSONArray("data").getJSONObject(p).getString("free_mrp"),
                                    response.getJSONArray("data").getJSONObject(p).getString("free_product_name")
                            ));
                            if (!response.getJSONArray("data").getJSONObject(p).getString("s_clolor").isEmpty() &&
                                    !response.getJSONArray("data").getJSONObject(p).getString("s_size").isEmpty()) {
                                String sepelistcolor = Arrays.asList(response.getJSONArray("data").getJSONObject(p).getString("s_clolor").split(",")).get(0).toString();
                                String sepelistsize = Arrays.asList(response.getJSONArray("data").getJSONObject(p).getString("s_size").split(",")).get(0).toString();
                                promodecolourandsizeList.add(new Product_model(sepelistcolor, sepelistsize));
                            } else if (!response.getJSONArray("data").getJSONObject(p).getString("cloth_color").isEmpty()
                                    && !response.getJSONArray("data").getJSONObject(p).getString("cloth_size").isEmpty()) {
                                String sepelistcolor1 = Arrays.asList(response.getJSONArray("data").getJSONObject(p).getString("cloth_color").split(",")).get(0).toString();
                                String sepelistsize1 = Arrays.asList(response.getJSONArray("data").getJSONObject(p).getString("cloth_size").split(",")).get(0).toString();
                                promodecolourandsizeList.add(new Product_model(sepelistcolor1, sepelistsize1));
//       promodecolourandsizeList.add(new Product_model(response.getJSONArray("data").getJSONObject(p).getString("cloth_color").toString()
//               ,response.getJSONArray("data").getJSONObject(p).getString("cloth_size")));
                            } else {
                                promodecolourandsizeList.add(new Product_model("None", "None"));
                            }
                        }
                        adapter_product = new Product_adapter(product_modelList, getActivity());
                        rv_search.setAdapter(adapter_product);
                        adapter_product.notifyDataSetChanged();

                        if(getActivity() != null) {
                            if (product_modelList.isEmpty()) {
                                Toast.makeText(getActivity(), getResources().getString(R.string.no_rcord_found), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.connection_time_out), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

}
