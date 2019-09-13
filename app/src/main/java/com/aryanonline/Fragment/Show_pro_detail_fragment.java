package com.aryanonline.Fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aryanonline.Adapter.Colours_Adapter;
import com.aryanonline.Adapter.SizesAdapter;
import com.aryanonline.Config.BaseURL;
import com.aryanonline.MainActivity;
import com.aryanonline.Model.Product_model;
import com.aryanonline.R;
import com.aryanonline.Replacement_Activity;
import com.aryanonline.Warranty_Activity;
import com.aryanonline.util.DatabaseHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.aryanonline.Fragment.Product_fragment.promodecolourandsizeList;

public class Show_pro_detail_fragment extends Fragment {
    TextView add_to_cart,prod_buy_now,prod_name,tv_prod_price,tv_prod_desc,prod_in_stock,
            tv_emi,tv_waranty,tv_offer_desc,tv_price_only,tv_mrp_only,tv_del_charges;
    //
    ImageView prod_img,iv_special_offer,offer_image;
    String shoe_colour ="no",cloth_colour = "no",cloth_size = "no",shoe_size = "no";
    ArrayList<String> ColoursList = new ArrayList<>();
    ArrayList<String> SizesList = new ArrayList<>();
    // ArrayList<String> SizesList = new ArrayList<>();
    private DatabaseHandler dbcart;
    RecyclerView procolour ,shoesizesc,shirtsizesc;
    private Context context;
    HashMap<String, String> map = new HashMap<>();
    private String strinx;
    TextView waryxt,replacetxt;
    private String strinx2;
    int thisposition =0;
    Colours_Adapter colours_adapter;
    SizesAdapter sizesAdapter;
    public static  TextView colour_sel ,size_sel,colour_sel2 ,size_sel2;
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
        waryxt=view.findViewById(R.id.waryxt);
        replacetxt=view.findViewById(R.id.replacetxt);
        colour_sel2=view.findViewById(R.id.colour_sel2);
        tv_del_charges=view.findViewById(R.id.tv_del_charges);
        tv_price_only=view.findViewById(R.id.tv_price_only);
        tv_mrp_only=view.findViewById(R.id.tv_mrp_only);
        size_sel2=view.findViewById(R.id.size_sel2);
        prod_img=view.findViewById(R.id.prod_img);
        prod_name=view.findViewById(R.id.prod_name);
        colour_sel=view.findViewById(R.id.colour_sel);
        size_sel=view.findViewById(R.id.size_sel);
        tv_prod_price=view.findViewById(R.id.tv_prod_price);
        //Recyler viewsssssssss+++++++++++++++++++++++++++++++++
        procolour=view.findViewById(R.id.procolour);
        shoesizesc=view.findViewById(R.id.shoesizesc);
        shirtsizesc=view.findViewById(R.id.shirtsizesc);
        //+++++++++++++++++++++++
        tv_prod_desc=view.findViewById(R.id.tv_prod_desc);
        prod_in_stock=view.findViewById(R.id.prod_in_stock);
        prod_buy_now=view.findViewById(R.id.prod_buy_now);
        iv_special_offer=view.findViewById(R.id.iv_special_offer);
        offer_image=view.findViewById(R.id.offer_image);
        tv_emi=view.findViewById(R.id.tv_emi);
        tv_waranty=view.findViewById(R.id.tv_waranty);
        tv_offer_desc=view.findViewById(R.id.tv_offer_desc);

        dbcart = new DatabaseHandler(getActivity());
        waryxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext() , Warranty_Activity.class);
                intent.putExtra("pro_id" , getArguments().getString("product_id"));
                startActivity(intent);
            }
        });
        replacetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext() , Replacement_Activity.class);
                intent.putExtra("pro_id" , getArguments().getString("product_id"));
                startActivity(intent);
            }
        });
        try {

            map.put("product_id", getArguments().getString("product_id"));

            //for now++++++++++++++++++++++++++++++++++++
            map.put("category_id", getArguments().getString("category_id"));
            map.put("product_image", getArguments().getString("product_image"));
            map.put("increament", getArguments().getString("increament"));
            map.put("product_name", getArguments().getString("product_name"));
            map.put("price",getArguments().getString("price"));
            map.put("stock",getArguments().getString("stock"));
            map.put("title", getArguments().getString("title"));
            map.put("delivery_charg", getArguments().getString("delivery_charg"));
            tv_del_charges.setText(getArguments().getString("delivery_charg"));
            // fOR CLOTHES ++++++++++++++++++++++++++++++++++++++++
            try {
                if(!getArguments().getString("s_size").isEmpty() &&!getArguments().getString("s_colour").isEmpty() ) {

                    promodecolourandsizeList.add(new Product_model( colour_sel.getText().toString() ,size_sel.getText().toString()));

                }else if(!getArguments().getString("cloth_colour").isEmpty() && !getArguments().getString("cloth_size").isEmpty())
                {
                    promodecolourandsizeList.add(new Product_model( colour_sel.getText().toString() ,size_sel.getText().toString()));
                }
                if(!getArguments().getString("cloth_colour").isEmpty()) {
                    //   map.put("colour", getArguments().getString("cloth_colour"));
                    map.put("colour", getArguments().getString("cloth_colour"));

                    cloth_colour = getArguments().getString("cloth_colour");

                }else if(!getArguments().getString("s_colour").isEmpty() )
                {
                    map.put("colour", getArguments().getString("s_size"));
                    shoe_colour = getArguments().getString("s_size");
                }
                if(!getArguments().getString("cloth_size").isEmpty())
                {
                    map.put("size", getArguments().getString("cloth_size"));
                    cloth_size = getArguments().getString("cloth_size");
                }else if(!getArguments().getString("s_size").isEmpty()) {

                    //   map.put("s_colour", getArguments().getString("s_colour"));
                    map.put("size", getArguments().getString("s_colour"));
                    shoe_size =  getArguments().getString("s_colour");
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
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
        tv_price_only.setText("Price: "+map.get("price"));
        tv_mrp_only.setText("MRP: "+map.get("Mrp"));
      //  tv_prod_price.setText(map.get("unit_value")+" "+map.get("unit")+" "+"INR "+map.get("price"));
        tv_prod_price.setText(map.get("unit_value")+" "+map.get("unit"));
        try {
            if (!cloth_colour.equals("no")) {
                int size =cloth_colour.length();
                Toast.makeText(getActivity(), "THis is not a cloth size " + cloth_colour.length(), Toast.LENGTH_SHORT).show();
                for (int cl = 0; cl < map.get("colour").length(); cl++) {
                    if (cl == 0) {
                        strinx = cloth_colour.toString().substring(thisposition, cl);
                    } else {
                        if (!strinx.isEmpty()) {
                            if (!strinx.contains(",") && !(cl == cloth_colour.length() - 1)) {
                                strinx = cloth_colour.substring(thisposition, cl);
                            } else {
                                if (!(cl == map.get("colour").length() - 1)) {
                                    strinx = strinx.subSequence(0, strinx.length() - 1).toString();
                                    ColoursList.add(strinx);
                                    Log.e("Colour if uis", "" + strinx);
                                    //   Toast.makeText(getActivity(), "" + ColoursList.get(cl), Toast.LENGTH_SHORT).show();
                                    thisposition = cl - 1;
                                    //   cl = thisposition;
                                    strinx = "";
                                } else {
                                    // strinx = strinx.subSequence(0, strinx.length()).toString().concat( map.get("cloth_colour").substring(thisposition, map.get("cloth_colour").length()-1));
                                    strinx = strinx + map.get("colour").substring(cl - 1);
                                    ColoursList.add(strinx);
                                    Log.e("Colour else  uis", "" +cloth_colour.substring(cl - 1));
                                    Log.e("Colour else strinx  uis", "" + strinx);
                                    //   Toast.makeText(getActivity(), "" + ColoursList.get(cl), Toast.LENGTH_SHORT).show();
                                    //thisposition = cl - 1;
                                    //   cl = thisposition;
                                    strinx = "";
                                }
                            }
                        } else {
                            strinx = cloth_colour.substring(thisposition, cl);
                            cl = thisposition;

                        }
                    }

//                cl++;
                }

                strinx = "";
                thisposition = 0;
                colours_adapter = new Colours_Adapter(getActivity(), ColoursList);
                procolour.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                procolour.setAdapter(colours_adapter);
                //shoe Colour is size??????????????????????????????????????????
            } else if (!shoe_size.equals("no")) {
//            int size  =  map.get("s_colour").length();
//            for(int px=0;px<map.get("s_colour").length();px++)
//            {
//                Log.e("elements at",""+px);
//                Log.e("elements ",""+map.get("s_colour").subSequence(0,px));
//              if(px!=0)
//              {
//                  if(!strinx2.isEmpty())
//                  {
//                      if(!strinx2.contains(",") &&!(px == map.get("s_colour").length()-1))
//                      {
//                          strinx2 = strinx2.subSequence(0, strinx.length() - 1).toString();
//                          ColoursList.add(strinx);
//                          Log.e("Colour if uis", "" + strinx);
//                          //   Toast.makeText(getActivity(), "" + ColoursList.get(cl), Toast.LENGTH_SHORT).show();
//                          thisposition = px ;
//                          //   cl = thisposition;
//                          strinx = "";
//                      }
//                  }else {
//                      strinx2 = map.get("cloth_colour").substring(thisposition, px);
//                      thisposition =px;
//                  }
//              }else {
//
//              }
//            }
                Toast.makeText(getActivity(), "THis is not a cloth size " + shoe_size.length(), Toast.LENGTH_SHORT).show();
                for (int cl = 0; cl <= shoe_size.length(); cl++) {
                    if (cl == 0) {
                        strinx = shoe_size.toString().substring(thisposition, cl);
                    } else {
                        if (!strinx.isEmpty()) {
                            if (!strinx.contains(",") && !(cl == shoe_size.length() - 1)) {
                                strinx = shoe_size.substring(thisposition, cl);
                            } else {
                                if (!(cl == shoe_size.length())) {
                                    if (!strinx.contains(",")) {
                                        strinx = strinx.subSequence(0, strinx.length() - 1).toString();
                                        SizesList.add(strinx);
                                        Log.e("s_size if uis", "" + strinx);
                                    } else {
                                        strinx = strinx.subSequence(0, strinx.length() - 1).toString();
                                        SizesList.add(strinx);
                                        Log.e("s_size if uis", "" + strinx);
                                        strinx = "";
                                        thisposition = cl - 1;
//                                    cl=cl-2;
                                    }
                                    //   Toast.makeText(getActivity(), "" + ColoursList.get(cl), Toast.LENGTH_SHORT).show();


                                    //   cl = thisposition;
                                    strinx = "";
                                } else {
                                    // strinx = strinx.subSequence(0, strinx.length()).toString().concat( map.get("cloth_colour").substring(thisposition, map.get("cloth_colour").length()-1));
                                    //  strinx=  strinx + map.get("s_colour").substring(cl-1);
                                    strinx = strinx + shoe_size.substring(cl - 1);
                                    SizesList.add(strinx);
//                                if(strinx.contains(",")) {
//                                    strinx =  map.get("s_colour").substring(thisposition,cl-2);
//                                    SizesList.add(strinx);
//                                    Log.e("s_size else strinx  uis", "" + strinx);
//                                    strinx ="";
//                                }else {
//                                    strinx=  strinx + map.get("s_colour").substring(cl-2);
//                                    SizesList.add(strinx);
//                                    Log.e("s_size else strinx  uis", "" + strinx);
//                                }
                                    //     SizesList.add(strinx);
                                    Log.e("s_size else  uis", "" + map.get("colour").substring(cl - 1));
                                    Log.e("s_size else strinx  uis", "" + strinx);
                                    //   Toast.makeText(getActivity(), "" + ColoursList.get(cl), Toast.LENGTH_SHORT).show();
                                    //   thisposition = cl - 1;
                                    //   cl = thisposition;
                                    strinx = "";
//                                SizesList.add("White");
                                }
                            }
                        } else {
                            strinx = shoe_size.substring(thisposition, cl);
                            //  cl=thisposition;
                            if (thisposition == 0) {
                                thisposition = cl - 1;
                            } else {
                                thisposition = cl - 2;
                            }

                        }
                    }

//                cl++;
                }
                if (shoe_size.contains("10")) {
                    SizesList.add("10");

                }
                if (shoe_size.contains("11")) {
                    SizesList.add("11");
                }
                if (shoe_size.contains("12")) {
                    SizesList.add("12");
                }
                if (shoe_size.contains("13")) {
                    SizesList.add("13");
                }
                if (shoe_size.contains("14")) {
                    SizesList.add("14");
                }
                if ( shoe_size.contains("15")) {
                    SizesList.add("15");
                }
                strinx = "";
                thisposition = 0;
                sizesAdapter = new SizesAdapter(getActivity(), SizesList);
                shoesizesc.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                shoesizesc.setAdapter(sizesAdapter);
            }
            //it is a  shoe color
            if (!shoe_colour.equals("no")) {
                int size = shoe_colour.length();
                Toast.makeText(getActivity(), "THis is not a cloth size " + shoe_colour.length(), Toast.LENGTH_SHORT).show();
                for (int cl = 0; cl < shoe_colour.length(); cl++) {
                    if (cl == 0) {
                        strinx = shoe_colour.toString().substring(thisposition, cl);
                    } else {
                        if (!strinx.isEmpty()) {
                            if (!strinx.contains(",") && !(cl == shoe_colour.length() - 1)) {
                                strinx = shoe_colour.substring(thisposition, cl);
                            } else {
                                if (!(cl == shoe_colour.length() - 1)) {
                                    strinx = strinx.subSequence(0, strinx.length() - 1).toString();
                                    ColoursList.add(strinx);
                                    Log.e("s_size if uis", "" + strinx);
                                    //   Toast.makeText(getActivity(), "" + ColoursList.get(cl), Toast.LENGTH_SHORT).show();
                                    thisposition = cl;
                                    //   cl = thisposition;
                                    strinx = "";
                                } else {
                                    // strinx = strinx.subSequence(0, strinx.length()).toString().concat( map.get("cloth_colour").substring(thisposition, map.get("cloth_colour").length()-1));
                                    strinx = strinx + shoe_colour.substring(cl - 1);
                                    ColoursList.add(strinx);
                                    // SizesList.add(strinx);
                                    Log.e("s_size else  uis", "" + shoe_colour.substring(cl - 1));
                                    Log.e("s_size else strinx  uis", "" + strinx);
                                    //   Toast.makeText(getActivity(), "" + ColoursList.get(cl), Toast.LENGTH_SHORT).show();
                                    //thisposition = cl - 1;
                                    //   cl = thisposition;
                                    strinx = "";
//                                SizesList.add("White");
                                }
                            }
                        } else {

                            strinx = shoe_colour.substring(thisposition, cl);
//                        cl=thisposition;
                            if (thisposition == 0) {
                                thisposition = cl - 1;
                            } else {
                                thisposition = cl - 2;
                            }


                        }
                    }

//                cl++;
                }

                strinx = "";
                thisposition = 0;
                colours_adapter = new Colours_Adapter(getActivity(), ColoursList);
                procolour.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                procolour.setAdapter(colours_adapter);
            } else if (!cloth_size.equals("no")) {
                int size = cloth_size.length();
                Toast.makeText(getActivity(), "THis is not a cloth size " + cloth_size.length(), Toast.LENGTH_SHORT).show();
                for (int cl = 0; cl < cloth_size.length(); cl++) {

                    if (cl == 0) {
                        strinx = cloth_size.toString().substring(thisposition, cl);
                    } else {
                        if (!strinx.isEmpty()) {
                            if (!strinx.contains(",") && !(cl == cloth_size.length() - 1)) {
                                strinx = cloth_size.substring(thisposition, cl);
                            } else {
                                if (!(cl ==cloth_size.length() - 1)) {

                                    strinx = strinx.subSequence(0, strinx.length() - 1).toString();
                                    SizesList.add(strinx);
                                    Log.e("cloth_size if uis", "" + strinx);
                                    //   Toast.makeText(getActivity(), "" + ColoursList.get(cl), Toast.LENGTH_SHORT).show();
                                    //    thisposition = cl - 1;
                                    thisposition = cl - 1;
                                    //   cl = thisposition;
                                    strinx = "";
                                } else {
                                    // strinx = strinx.subSequence(0, strinx.length()).toString().concat( map.get("cloth_colour").substring(thisposition, map.get("cloth_colour").length()-1));
                                    strinx = strinx + cloth_size.substring(cl - 1);
                                    SizesList.add(strinx);
//                                     SizesList.add(strinx);
                                    Log.e("s_size else  uis", "" + cloth_size.substring(cl - 1));
                                    Log.e("s_size else strinx  uis", "" + strinx);
                                    //   Toast.makeText(getActivity(), "" + ColoursList.get(cl), Toast.LENGTH_SHORT).show();
                                    //thisposition = cl - 1;
                                    //   cl = thisposition;
                                    strinx = "";
//                                SizesList.add("White");
                                }
                            }
                        } else {
                            strinx = cloth_size.substring(thisposition, cl);
                            //      cl=thisposition;
                            //       SizesList.add(strinx);
//                            thisposition=cl-1;
                            if (thisposition == 0) {
                                thisposition = cl - 1;

                            } else {
                                thisposition = cl - 2;
                            }
                        }
                    }

//                cl++;
                }

                sizesAdapter = new SizesAdapter(getActivity(), SizesList);
                shoesizesc.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                shoesizesc.setAdapter(sizesAdapter);
            }


            Log.e("cloth_colour COlour is", "" +cloth_size);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        //++++++++++++++++++++++++++++++end of  sizes+++++++++++++++++++++++++++++++++++++++
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

        offer_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage( map.get("product_offer_image"));
            }
        });

        Glide.with(getActivity())
                .load(R.drawable.spe_offer)
//                .centerCrop()
                .placeholder(R.drawable.spe_offer)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(iv_special_offer);




        if (dbcart.isInCart(map.get("product_id"))) {
            //for carting

            add_to_cart.setText(getResources().getString(R.string.tv_btn_gocart));

        } else {
            add_to_cart.setText(getResources().getString(R.string.tv_btn_addcart));
        }


        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!getArguments().getString("s_size").isEmpty() && !getArguments().getString("s_colour").isEmpty()) {

                        promodecolourandsizeList.add(new Product_model(colour_sel.getText().toString(), size_sel.getText().toString()));

                        if (!getArguments().getString("cloth_colour").isEmpty()) {
                            //   map.put("colour", getArguments().getString("cloth_colour"));
                            map.put("colour", colour_sel.getText().toString());

                            cloth_colour = getArguments().getString("cloth_colour");

                        } else if (!getArguments().getString("s_colour").isEmpty()) {
                            map.put("colour", colour_sel.getText().toString());
                            shoe_colour = getArguments().getString("s_size");
                        }
                        if (!getArguments().getString("cloth_size").isEmpty()) {
                            map.put("size", size_sel.getText().toString());
                            cloth_size = getArguments().getString("cloth_size");
                        } else if (!getArguments().getString("s_size").isEmpty()) {

                            //   map.put("s_colour", getArguments().getString("s_colour"));
                            map.put("size", size_sel.getText().toString());
                            shoe_size = getArguments().getString("s_colour");
                        }
//           map.get("colour").

                    } else if (!getArguments().getString("cloth_colour").isEmpty() && !getArguments().getString("cloth_size").isEmpty()) {
                        promodecolourandsizeList.add(new Product_model(colour_sel.getText().toString(), size_sel.getText().toString()));

                        if (!getArguments().getString("cloth_colour").isEmpty()) {
                            //   map.put("colour", getArguments().getString("cloth_colour"));
                            map.put("colour", colour_sel.getText().toString());

                            cloth_colour = getArguments().getString("cloth_colour");

                        } else if (!getArguments().getString("s_colour").isEmpty()) {
                            map.put("colour", colour_sel.getText().toString());
                            shoe_colour = getArguments().getString("s_size");
                        }
                        if (!getArguments().getString("cloth_size").isEmpty()) {
                            map.put("size", size_sel.getText().toString());
                            cloth_size = getArguments().getString("cloth_size");
                        } else if (!getArguments().getString("s_size").isEmpty()) {

                            //   map.put("s_colour", getArguments().getString("s_colour"));
                            map.put("size", size_sel.getText().toString());
                            shoe_size = getArguments().getString("s_colour");
                        }
                    }
                    if (Integer.parseInt(getArguments().getString("stock")) > 0) {

                        if (add_to_cart.getText().toString().equals(getResources().getString(R.string.tv_btn_addcart))) {

                            Toast.makeText(getActivity(), "Add to cart Successfully", Toast.LENGTH_SHORT).show();

                            if (dbcart.isInCart(map.get("product_id"))) {
                                if (!map.get("colour").isEmpty() || !map.get("size").isEmpty()) {
                                    map.put("size", "None");
                                    map.put("colour", "None");
                                    dbcart.setCart(map, Float.valueOf("1"));
                                    add_to_cart.setText(getResources().getString(R.string.tv_btn_gocart));
                                } else {
                                    map.put("size", "None");
                                    map.put("colour", "None");
                                    dbcart.setCartwithoutsize(map, Float.valueOf("1"));
                                    add_to_cart.setText(getResources().getString(R.string.tv_btn_gocart));
                                }
                            } else {
                                map.put("size", "None");
                                map.put("colour", "None");
                                dbcart.setCart(map, Float.valueOf("1"));
                                add_to_cart.setText(getResources().getString(R.string.tv_btn_gocart));
                            }
                            ((MainActivity) getActivity()).setCartCounter("" + dbcart.getCartCount());

                        } else {
                            Fragment Favorite_List = new Cart_fragment();
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.contentPanel, Favorite_List);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }

                    } else {

                        if (add_to_cart.getText().toString().equals(getResources().getString(R.string.tv_btn_addcart))) {

                            Toast.makeText(getActivity(), "Sorry, Out of Stock", Toast.LENGTH_SHORT).show();

                        } else {
                            Fragment Favorite_List = new Cart_fragment();
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.contentPanel, Favorite_List);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }
                    }

                }catch (Exception e)
                {

                    e.printStackTrace();
                    if (Integer.parseInt(getArguments().getString("stock")) > 0) {

                        if (add_to_cart.getText().toString().equals(getResources().getString(R.string.tv_btn_addcart))) {

                            Toast.makeText(getActivity(), "Add to cart Successfully", Toast.LENGTH_SHORT).show();

                            if (dbcart.isInCart(map.get("product_id"))) {
                                if (!map.get("colour").isEmpty() || !map.get("size").isEmpty()) {
                                    map.put("size", "None");
                                    map.put("colour", "None");
                                    dbcart.setCart(map, Float.valueOf("1"));
                                    add_to_cart.setText(getResources().getString(R.string.tv_btn_gocart));
                                } else {
                                    map.put("size", "None");
                                    map.put("colour", "None");
                                    dbcart.setCartwithoutsize(map, Float.valueOf("1"));
                                    add_to_cart.setText(getResources().getString(R.string.tv_btn_gocart));
                                }
                            } else {
                                map.put("size", "None");
                                map.put("colour", "None");
                                dbcart.setCart(map, Float.valueOf("1"));
                                add_to_cart.setText(getResources().getString(R.string.tv_btn_gocart));
                            }
                            ((MainActivity) getActivity()).setCartCounter("" + dbcart.getCartCount());

                        } else {
                            Fragment Favorite_List = new Cart_fragment();
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.contentPanel, Favorite_List);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }

                    } else {

                        if (add_to_cart.getText().toString().equals(getResources().getString(R.string.tv_btn_addcart))) {

                            Toast.makeText(getActivity(), "Sorry, Out of Stock", Toast.LENGTH_SHORT).show();

                        } else {
                            Fragment Favorite_List = new Cart_fragment();
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.contentPanel, Favorite_List);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }
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
                        if(!map.get("size").isEmpty()||!map.get("colour").isEmpty()) {
                            map.put("size" , "None");
                            map.put("colour" , "None");
                            dbcart.setCart(map, Float.valueOf("1"));
                        }else {
                            map.put("size" , "None");
                            map.put("colour" , "None");
                            dbcart.setCartwithoutsize(map, Float.valueOf("1"));
                        }

                    } else {
                        map.put("size" , "None");
                        map.put("colour" , "None");
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

