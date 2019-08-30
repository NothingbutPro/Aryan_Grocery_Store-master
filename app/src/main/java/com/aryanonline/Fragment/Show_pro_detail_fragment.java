package com.aryanonline.Fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aryanonline.Config.BaseURL;
import com.aryanonline.MainActivity;
import com.aryanonline.R;
import com.aryanonline.util.DatabaseHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Show_pro_detail_fragment extends Fragment {
    TextView add_to_cart,prod_buy_now,prod_name,tv_prod_price,tv_prod_desc,prod_in_stock,
            tv_emi,tv_waranty,tv_offer_desc;
    ImageView prod_img,iv_special_offer,offer_image;
    List<String> ColoursList = new ArrayList<>();
    List<String> SizesList = new ArrayList<>();
    private DatabaseHandler dbcart;
    private Context context;
    HashMap<String, String> map = new HashMap<>();
    private String strinx;
    int thisposition =0;

    public Show_pro_detail_fragment() {
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
        View view = inflater.inflate(R.layout.activity_show_prod_detail, container, false);

        ((MainActivity) getActivity()).setTitle("Product Detail");

        add_to_cart=view.findViewById(R.id.add_to_cart);
        prod_img=view.findViewById(R.id.prod_img);
        prod_name=view.findViewById(R.id.prod_name);
        tv_prod_price=view.findViewById(R.id.tv_prod_price);
        tv_prod_desc=view.findViewById(R.id.tv_prod_desc);
        prod_in_stock=view.findViewById(R.id.prod_in_stock);
        prod_buy_now=view.findViewById(R.id.prod_buy_now);
        iv_special_offer=view.findViewById(R.id.iv_special_offer);
        offer_image=view.findViewById(R.id.offer_image);
        tv_emi=view.findViewById(R.id.tv_emi);
        tv_waranty=view.findViewById(R.id.tv_waranty);
        tv_offer_desc=view.findViewById(R.id.tv_offer_desc);

        dbcart = new DatabaseHandler(getActivity());


        try {

            map.put("product_id", getArguments().getString("product_id"));
            map.put("category_id", getArguments().getString("category_id"));
            map.put("product_image", getArguments().getString("product_image"));
            map.put("increament", getArguments().getString("increament"));
            map.put("product_name", getArguments().getString("product_name"));
            map.put("price",getArguments().getString("price"));
            map.put("stock",getArguments().getString("stock"));
            map.put("title", getArguments().getString("title"));
            // fOR CLOTHES ++++++++++++++++++++++++++++++++++++++++
            map.put("cloth_colour", getArguments().getString("cloth_colour"));
            map.put("cloth_size", getArguments().getString("cloth_size"));
            map.put("s_colour", getArguments().getString("s_colour"));
            map.put("s_size", getArguments().getString("s_size"));
         //   map.put("cloth_size", getArguments().getString("cloth_size"));
            //++++++++++++++++++++++++++++++++++++++++++++++++++
            map.put("unit", getArguments().getString("unit"));
            map.put("Mrp", getArguments().getString("Mrp"));
            map.put("unit_value", getArguments().getString("unit_value"));
            map.put("Prod_description", getArguments().getString("Prod_description"));
            map.put("EMI", getArguments().getString("EMI"));
            map.put("Warantee", getArguments().getString("Warantee"));
            map.put("product_offer_image", getArguments().getString("product_offer_image"));
            map.put("p_offer_description", getArguments().getString("p_offer_description"));
        }catch (Exception e){
            e.printStackTrace();
        }
        prod_name.setText(map.get("product_name"));
        tv_prod_desc.setText("Product Description: "+map.get("Prod_description"));
        tv_prod_price.setText(map.get("unit_value")+" "+map.get("unit")+" "+"INR "+map.get("price"));
        if(!map.get("cloth_colour").isEmpty()){
            int size  =  map.get("cloth_colour").length();
            Toast.makeText(getActivity(), "THis is not a cloth size "+map.get("cloth_colour").length(), Toast.LENGTH_SHORT).show();
            for (int cl=0;cl<map.get("cloth_colour").length();cl++)
            {
                if(cl==0)
                {
                    strinx =  map.get("cloth_colour").toString().substring(thisposition,cl);
                }else {
                    if(!strinx.isEmpty()) {
                        if (!strinx.contains(",") &&!(cl == map.get("cloth_colour").length()-1) ) {
                            strinx = map.get("cloth_colour").substring(thisposition, cl);
                        } else {
                            if(!(cl == map.get("cloth_colour").length()-1) ) {
                                strinx = strinx.subSequence(0, strinx.length() - 1).toString();
                                ColoursList.add(strinx);
                                Log.e("Colour if uis", "" + strinx);
                                //   Toast.makeText(getActivity(), "" + ColoursList.get(cl), Toast.LENGTH_SHORT).show();
                                thisposition = cl - 1;
                                //   cl = thisposition;
                                strinx = "";
                            }else {
                               // strinx = strinx.subSequence(0, strinx.length()).toString().concat( map.get("cloth_colour").substring(thisposition, map.get("cloth_colour").length()-1));
                                strinx=  strinx + map.get("cloth_colour").substring(cl-1);
                                ColoursList.add(strinx);
                                Log.e("Colour else  uis", "" + map.get("cloth_colour").substring(cl-1));
                                Log.e("Colour else strinx  uis", "" + strinx);
                                //   Toast.makeText(getActivity(), "" + ColoursList.get(cl), Toast.LENGTH_SHORT).show();
                                //thisposition = cl - 1;
                                //   cl = thisposition;
                                strinx = "";
                            }
                        }
                    }else {
                        strinx = map.get("cloth_colour").substring(thisposition, cl);
                        cl=thisposition;
                    }
                }

//                cl++;
            }
        }

        Log.e("cloth_colour COlour is" , ""+map.get("cloth_colour"));
        tv_emi.setText(map.get("EMI"));
        tv_waranty.setText(map.get("Warantee"));
        tv_offer_desc.setText(map.get("p_offer_description"));

        if(Integer.parseInt(getArguments().getString("stock")) >0){
            prod_in_stock.setText("In Stock");
        }else {
            prod_in_stock.setText("Out of Stock");
            prod_in_stock.setTextColor(Color.RED);
        }

        //*****************************************
        Glide.with(getActivity())
                .load(BaseURL.IMG_PRODUCT_URL + map.get("product_image"))
//                .centerCrop()
                .placeholder(R.drawable.logoimg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(prod_img);

        Glide.with(getActivity())
                .load(BaseURL.IMG_PRODUCT_URL + map.get("product_offer_image"))
//                .centerCrop()
               // .placeholder(R.drawable.logoimg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(offer_image);



        Glide.with(getActivity())
                .load(R.drawable.spe_offer)
//                .centerCrop()
                .placeholder(R.drawable.spe_offer)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(iv_special_offer);




        if (dbcart.isInCart(map.get("product_id"))) {
            add_to_cart.setText(getResources().getString(R.string.tv_btn_gocart));

        } else {
            add_to_cart.setText(getResources().getString(R.string.tv_btn_addcart));
        }


        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Integer.parseInt(getArguments().getString("stock")) >0){

                    if (add_to_cart.getText().toString().equals(getResources().getString(R.string.tv_btn_addcart))){

                        Toast.makeText(getActivity(), "Add to cart Successfully", Toast.LENGTH_SHORT).show();

                        if (dbcart.isInCart(map.get("product_id"))) {
                            dbcart.setCart(map, Float.valueOf("1"));
                            add_to_cart.setText(getResources().getString(R.string.tv_btn_gocart));

                        } else {
                            dbcart.setCart(map, Float.valueOf("1"));
                            add_to_cart.setText(getResources().getString(R.string.tv_btn_gocart));
                        }
                        ((MainActivity)getActivity()).setCartCounter(""+ dbcart.getCartCount());

                    }
                    else
                    {
                        Fragment Favorite_List=new Cart_fragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.contentPanel,Favorite_List);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }

                }else {

                    if (add_to_cart.getText().toString().equals(getResources().getString(R.string.tv_btn_addcart))){

                        Toast.makeText(getActivity(), "Sorry, Out of Stock", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Fragment Favorite_List=new Cart_fragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.contentPanel,Favorite_List);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }

            }
        });
        //**********************************
        prod_buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Integer.parseInt(getArguments().getString("stock")) >0){

                    if (dbcart.isInCart(map.get("product_id"))) {
                        dbcart.setCart(map, Float.valueOf("1"));

                    } else {
                        dbcart.setCart(map, Float.valueOf("1"));
                    }
                    ((MainActivity)getActivity()).setCartCounter(""+ dbcart.getCartCount());

                    Fragment Favorite_List=new Cart_fragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.contentPanel,Favorite_List);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }else {
                    Toast.makeText(getActivity(), "Sorry, Out of Stock", Toast.LENGTH_SHORT).show();
                }



            }
        });

        prod_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showImage(map.get("product_image"));

            }
        });






        return view;
    }

    private void showImage(String product_image) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.product_image_dialog);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();

        ImageView iv_image_cancle = (ImageView) dialog.findViewById(R.id.iv_dialog_cancle);
        ImageView iv_image = (ImageView) dialog.findViewById(R.id.iv_dialog_img);

        Glide.with(getActivity())
                .load(BaseURL.IMG_PRODUCT_URL + product_image)
                .placeholder(R.drawable.shop)
                .crossFade()
                .into(iv_image);

        iv_image_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }


}

