package com.aryanonline.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.aryanonline.Model.Category_model;
import com.aryanonline.Model.Product_model;
import com.aryanonline.AppController;
import com.aryanonline.MainActivity;
import com.aryanonline.R;
import com.aryanonline.util.ConnectivityReceiver;
import com.aryanonline.util.CustomVolleyJsonRequest;


public class Product_fragment extends Fragment {

    private static String TAG = Product_fragment.class.getSimpleName();

    private RecyclerView rv_cat;
    private TabLayout tab_cat;

    private List<Category_model> category_modelList = new ArrayList<>();
    private List<String> cat_menu_id = new ArrayList<>();

    private List<Product_model> product_modelList = new ArrayList<>();
    public static List<Product_model> promodecolourandsizeList = new ArrayList<>();
    private Product_adapter adapter_product;

    public Product_fragment() {
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
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        setHasOptionsMenu(true);

        tab_cat = (TabLayout) view.findViewById(R.id.tab_cat);
        rv_cat = (RecyclerView) view.findViewById(R.id.rv_subcategory);
        rv_cat.setLayoutManager(new LinearLayoutManager(getActivity()));

        String getcat_id = getArguments().getString("cat_id");
        String getcat_title = getArguments().getString("cat_title");

        ((MainActivity) getActivity()).setTitle(getcat_title);

        // check internet connection
        if (ConnectivityReceiver.isConnected()) {
            makeGetCategoryRequest(getcat_id);
        }

        tab_cat.setVisibility(View.GONE);
        tab_cat.setSelectedTabIndicatorColor(getActivity().getResources().getColor(R.color.white));

        tab_cat.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String getcat_id = cat_menu_id.get(tab.getPosition());

                if (ConnectivityReceiver.isConnected()) {
                    makeGetProductRequest(getcat_id);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                /*String getcat_id = cat_menu_id.get(tab.getPosition());
                tab_cat.setSelectedTabIndicatorColor(getActivity().getResources().getColor(R.color.white));

                if (ConnectivityReceiver.isConnected()) {
                    makeGetProductRequest(getcat_id);
                }*/
            }
        });

        return view;
    }

    /**
     * Method to make json object request where json response starts wtih {
     */
    private void makeGetProductRequest(String cat_id) {

        // Tag used to cancel the request
        String tag_json_obj = "json_product_req";

        Map<String, String> params = new HashMap<String, String>();
        params.put("cat_id", cat_id);

        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.POST,
                BaseURL.GET_PRODUCT_URL, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                Log.e("Cate_product", response.toString());

                try {
                    Boolean status = response.getBoolean("responce");
                    if (status) {

                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<Product_model>>() {
                        }.getType();

                        // product_modelList = gson.fromJson(response.getString("data"), listType);
                        product_modelList = gson.fromJson(response.getJSONArray("data").toString(), listType);
                        Log.e("Product","after json"+response.getJSONArray("data").getJSONObject(0).getString("in_stock"));
                        product_modelList.clear();
                        promodecolourandsizeList.clear();
                        promodecolourandsizeList = new ArrayList<>(response.getJSONArray("data").length());
                        for(int p=0;p<response.getJSONArray("data").length();p++)
                        {
                            Log.e("responce proid" , ""+response.getJSONArray("data").getJSONObject(p).getString("product_id"));
                            product_modelList.add(new Product_model(response.getJSONArray("data").getJSONObject(p).getString("product_id")
                                    ,response.getJSONArray("data").getJSONObject(p).getString("product_name"),
                                    response.getJSONArray("data").getJSONObject(p).getString("product_description"),
                                    response.getJSONArray("data").getJSONObject(p).getString("product_image"),
                                    response.getJSONArray("data").getJSONObject(p).getString("category_id"),
                                    response.getJSONArray("data").getJSONObject(p).getString("in_stock")
                                    ,response.getJSONArray("data").getJSONObject(p).getString("price"),
                                    response.getJSONArray("data").getJSONObject(p).getString("unit_value"),
                                    response.getJSONArray("data").getJSONObject(p).getString("unit"),
                                    response.getJSONArray("data").getJSONObject(p).getString("increament"),
                                    response.getJSONArray("data").getJSONObject(p).getString("Mrp") ,
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
                                    response.getJSONArray("data").getJSONObject(p).getString("delivery_charg")
                            ));
                            if(!response.getJSONArray("data").getJSONObject(p).getString("s_clolor").isEmpty() &&
                                    !response.getJSONArray("data").getJSONObject(p).getString("s_size").isEmpty())
                            {
                                String sepelistcolor = Arrays.asList(response.getJSONArray("data").getJSONObject(p).getString("s_clolor").split(",")).get(0).toString();
                                String sepelistsize = Arrays.asList(response.getJSONArray("data").getJSONObject(p).getString("s_size").split(",")).get(0).toString();
                                promodecolourandsizeList.add(new Product_model(sepelistcolor,sepelistsize));
                            }
                            else
                                if(!response.getJSONArray("data").getJSONObject(p).getString("cloth_color").isEmpty()
                                    &&  !response.getJSONArray("data").getJSONObject(p).getString("cloth_size").isEmpty()){
                                String sepelistcolor1 = Arrays.asList(response.getJSONArray("data").getJSONObject(p).getString("cloth_color").split(",")).get(0).toString();
                                String sepelistsize1 = Arrays.asList(response.getJSONArray("data").getJSONObject(p).getString("cloth_size").split(",")).get(0).toString();
                                promodecolourandsizeList.add(new Product_model(sepelistcolor1,sepelistsize1));
//       promodecolourandsizeList.add(new Product_model(response.getJSONArray("data").getJSONObject(p).getString("cloth_color").toString()
//               ,response.getJSONArray("data").getJSONObject(p).getString("cloth_size")));
                            }else {
                                    promodecolourandsizeList.add(new Product_model("None","None"));
                                }
                        }
                        //   Log.e("product" , "json"+response.getJSONArray());
                        adapter_product = new Product_adapter(product_modelList, getActivity());
                        rv_cat.setAdapter(adapter_product);
                        adapter_product.notifyDataSetChanged();

                        if (getActivity() != null) {
                            if (product_modelList.isEmpty()) {
                                Toast.makeText(getActivity(), getResources().getString(R.string.no_rcord_found), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                } catch (JSONException e) {
                    if (getActivity() != null) {
                        if (product_modelList.isEmpty()) {
                            Toast.makeText(getActivity(), getResources().getString(R.string.no_rcord_found), Toast.LENGTH_SHORT).show();
                        }
                    }
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

    /**
     * Method to make json object request where json response starts wtih
     */
    private void makeGetCategoryRequest(final String parent_id) {

        // Tag used to cancel the request
        String tag_json_obj = "json_category_req";

        Map<String, String> params = new HashMap<String, String>();
        params.put("parent", parent_id);

        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.POST,
                BaseURL.GET_CATEGORY_URL, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    Boolean status = response.getBoolean("responce");
                    if (status) {

                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<Category_model>>() {
                        }.getType();

                        category_modelList = gson.fromJson(response.getString("data"), listType);

                        if (!category_modelList.isEmpty()) {
                            tab_cat.setVisibility(View.VISIBLE);

                            cat_menu_id.clear();
                            for (int i = 0; i < category_modelList.size(); i++) {
                                cat_menu_id.add(category_modelList.get(i).getId());
                                tab_cat.addTab(tab_cat.newTab().setText(category_modelList.get(i).getTitle()));
                            }
                        } else {
                            makeGetProductRequest(parent_id);
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



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem search = menu.findItem(R.id.action_search);
        search.setVisible(true);
        MenuItem check = menu.findItem(R.id.action_change_password);
        check.setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_search:
                Fragment fm = new Search_fragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contentPanel, fm)
                        .addToBackStack(null).commit();
                return false;
        }
        return false;
    }





  /*     @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem search = menu.findItem(R.id.action_search);
        search.setVisible(false);
        MenuItem check = menu.findItem(R.id.action_change_password);
        check.setVisible(false);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setBackgroundColor(getResources().getColor(R.color.white));
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(Color.WHITE);
        searchEditText.setHintTextColor(Color.GRAY);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter_product.getFilter().filter(newText);
                return false;
            }
        });
    }*/


}
