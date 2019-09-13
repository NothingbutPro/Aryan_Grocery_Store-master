package com.aryanonline.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.aryanonline.Config.BaseURL;
import com.aryanonline.AppController;
import com.aryanonline.MainActivity;
import com.aryanonline.R;
import com.aryanonline.util.AppPreference;
import com.aryanonline.util.ConnectivityReceiver;
import com.aryanonline.util.CustomVolleyJsonRequest;
import com.aryanonline.util.DatabaseHandler;
import com.aryanonline.util.Session_management;

import instamojo.library.InstamojoPay;
import instamojo.library.InstapayListener;

import static com.aryanonline.Fragment.Product_fragment.promodecolourandsizeList;


public class Delivery_payment_detail_fragment extends Fragment {

    private static String TAG = Delivery_payment_detail_fragment.class.getSimpleName();
    public static String pay_status = "Fail";
    private TextView tv_timeslot, tv_address, tv_item, tv_total,tv_pay_total;
    private Button btn_order;
    RadioButton radio_online_pay,radio_offline_pay,radio_emi_pay;

    private String getlocation_id = "";
    private String gettime = "";
    private String getdate = "";
    private String getuser_id = "";
    private int deli_charges;
    String Payment_method;

    private DatabaseHandler db_cart;
    private Session_management sessionManagement;

    public Delivery_payment_detail_fragment() {
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
        View view = inflater.inflate(R.layout.fragment_confirm_order, container, false);

        ((MainActivity) getActivity()).setTitle(getResources().getString(R.string.payment_detail));

        db_cart = new DatabaseHandler(getActivity());
        sessionManagement = new Session_management(getActivity());

        tv_timeslot = (TextView) view.findViewById(R.id.textTimeSlot);
        tv_address = (TextView) view.findViewById(R.id.txtAddress);
        //tv_item = (TextView) view.findViewById(R.id.textItems);
        //tv_total = (TextView) view.findViewById(R.id.textPrice);
        tv_total = (TextView) view.findViewById(R.id.txtTotal);
        tv_pay_total = (TextView) view.findViewById(R.id.tv_pay_total);

        btn_order = (Button) view.findViewById(R.id.buttonContinue);
        radio_online_pay = (RadioButton) view.findViewById(R.id.radio_online_pay);
        radio_offline_pay = (RadioButton) view.findViewById(R.id.radio_offline_pay);
        radio_emi_pay = (RadioButton) view.findViewById(R.id.radio_emi_pay);

        getdate = getArguments().getString("getdate");
        gettime = getArguments().getString("time");
        getlocation_id = getArguments().getString("location_id");
        deli_charges = Integer.parseInt(getArguments().getString("deli_charges"));
        String getaddress = getArguments().getString("address");

        tv_timeslot.setText(getdate + " " + gettime);
        tv_address.setText(getaddress);

        Double total = Double.parseDouble(db_cart.getTotalAmount()) + deli_charges;

        //tv_total.setText("" + db_cart.getTotalAmount());
        //tv_item.setText("" + db_cart.getCartCount());
        tv_total.setText(getResources().getString(R.string.tv_cart_item) + db_cart.getCartCount() + "\n" +
                getResources().getString(R.string.amount) + db_cart.getTotalAmount() + "\n" +
                getResources().getString(R.string.delivery_charge) + deli_charges + "\n" +
                getResources().getString(R.string.total_amount) +
                db_cart.getTotalAmount() + " + " + deli_charges + " = " + total + " " + getResources().getString(R.string.currency));

        tv_pay_total.setText("Pay "+total + " " + getResources().getString(R.string.currency));

        radio_online_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_offline_pay.setChecked(false);
                radio_emi_pay.setChecked(false);
                RadioButton rd_online = (RadioButton) view;
                btn_order.setText("Proceed To Pay");

            }
        });

        radio_offline_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_online_pay.setChecked(false);
                radio_emi_pay.setChecked(false);
                RadioButton rd_offline = (RadioButton) view;
                btn_order.setText("Place Order");
            }
        });

        radio_emi_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_online_pay.setChecked(false);
                radio_offline_pay.setChecked(false);
                RadioButton rd_offline = (RadioButton) view;
                btn_order.setText("Submit EMI Details");
            }
        });


        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radio_online_pay.isChecked() || radio_offline_pay.isChecked() || radio_emi_pay.isChecked()){
                    if (radio_offline_pay.isChecked()){
                        Payment_method=radio_offline_pay.getText().toString();
                        if (ConnectivityReceiver.isConnected()) {
                            attemptOrder();
                        } else {
                            ((MainActivity) getActivity()).onNetworkConnectionChanged(false);
                        }
                    }else if (radio_online_pay.isChecked()){
                        //db_cart.getTotalAmount()
                        callInstamojoPay(new Session_management(getActivity()).getUserDetails().get(BaseURL.KEY_EMAIL), AppPreference.getMobile(getActivity()) , "10","Online buying",new Session_management(getActivity()).getUserDetails().get(BaseURL.KEY_NAME));


                    }else {
                        Payment_method=radio_emi_pay.getText().toString();

                        if (ConnectivityReceiver.isConnected()) {
                            attemptOrder();
                        } else {
                            ((MainActivity) getActivity()).onNetworkConnectionChanged(false);
                        }
//                        Fragment fm = new Emi_fragment();
//                        FragmentManager fragmentManager = getFragmentManager();
//                        fragmentManager.beginTransaction().replace(R.id.contentPanel, fm)
//                                .addToBackStack(null).commit();
                    }


                }else {
                    Toast.makeText(getActivity(), "Please Choose Payment Option", Toast.LENGTH_LONG).show();
                }

            }
        });

        return view;
    }

    //++++++++++++++++++++++++++++++++++++++Instamojo
    private void callInstamojoPay(String email, String phone, String amount, String purpose, String buyername) {
        final Activity activity = getActivity();
        Payment_method=radio_online_pay.getText().toString();
        InstamojoPay instamojoPay = new InstamojoPay();
        IntentFilter filter = new IntentFilter("ai.devsupport.instamojo");
        getActivity().registerReceiver(instamojoPay, filter);
        JSONObject pay = new JSONObject();
        try {
            pay.put("email", email);
            pay.put("phone", phone);
            pay.put("purpose", purpose);
            pay.put("amount", amount);
            pay.put("name", buyername);
            pay.put("send_sms", true);
            pay.put("send_email", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initListener();
        instamojoPay.start(activity, pay, listener);
    }

    InstapayListener listener;
//
//
//    InstapayListener listener;


    private void initListener() {
        listener = new InstapayListener() {
            @Override
            public void onSuccess(String response) {
                pay_status = "Success";
                Toast.makeText(getActivity().getApplicationContext(), response, Toast.LENGTH_LONG)
                        .show();
                Toast.makeText(getActivity(), "Online Payment Success", Toast.LENGTH_SHORT).show();
                if (ConnectivityReceiver.isConnected()) {
                    attemptOrder();
                } else {
                    ((MainActivity) getActivity()).onNetworkConnectionChanged(false);
                }
            }

            @Override
            public void onFailure(int code, String reason) {
                Toast.makeText(getActivity().getApplicationContext(), "Failed: " + reason, Toast.LENGTH_LONG)
                        .show();
                Toast.makeText(getActivity(), "Having Problems", Toast.LENGTH_SHORT).show();
            }
        };
    }

    //+++++++++++++++++++++++++++++
    private void attemptOrder() {

        // retrive data from cart database
        ArrayList<HashMap<String, String>> items = db_cart.getCartAll();
        if (items.size() > 0) {
            JSONArray passArray = new JSONArray();
            for (int i = 0; i < items.size(); i++) {
                HashMap<String, String> map = items.get(i);

                JSONObject jObjP = new JSONObject();

                try {
                    jObjP.put("product_id", map.get("product_id"));
                    jObjP.put("qty", map.get("qty"));
                    jObjP.put("product_name", map.get("product_name"));
                    Log.e("Colour is",""+map.get("colour"));
                    Log.e("Size is " , ""+map.get("size"));
                    jObjP.put("unit_value", map.get("unit_value"));
                    jObjP.put("unit", map.get("unit"));
                    jObjP.put("price", map.get("price"));
                    jObjP.put("size", map.get("size"));
                    Log.e("color", map.get("colour"));
                    jObjP.put("color", map.get("colour"));

                    passArray.put(jObjP);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            getuser_id = sessionManagement.getUserDetails().get(BaseURL.KEY_ID);

            if (ConnectivityReceiver.isConnected()) {

                Log.e(TAG, "from:" + gettime + "\ndate:" + getdate +
                        "\n" + "\nuser_id:" + getuser_id + "\n" + getlocation_id + "\ndata:" + passArray.toString());

                makeAddOrderRequest(getdate, gettime, getuser_id, getlocation_id, passArray,Payment_method);
            }
        }
    }

    /**
     * Method to make json object request where json response starts wtih
     */
    private void makeAddOrderRequest(String date, String gettime, String userid,
                                     String location, JSONArray passArray, String payment_method) {

        // Tag used to cancel the request
        String tag_json_obj = "json_add_order_req";

        Map<String, String> params = new HashMap<String, String>();
        params.put("date", date);
        params.put("time", gettime);
        params.put("user_id", userid);
        params.put("location", location);
        params.put("data", passArray.toString());
        params.put("payment_mode", payment_method);

        CustomVolleyJsonRequest jsonObjReq = new CustomVolleyJsonRequest(Request.Method.POST,
                BaseURL.ADD_ORDER, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG +"With Response", response.toString());

                try {
                    Boolean status = response.getBoolean("responce");
                    if (status) {

                        String msg = response.getString("data");
                        promodecolourandsizeList.clear();
                        db_cart.clearCart();
                        ((MainActivity) getActivity()).setCartCounter("" + db_cart.getCartCount());

                        Bundle args = new Bundle();
                        Fragment fm = new Thanks_fragment();
                        args.putString("msg", msg);
                        promodecolourandsizeList.clear();
                        fm.setArguments(args);
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.contentPanel, fm)
                                .addToBackStack(null).commit();

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
