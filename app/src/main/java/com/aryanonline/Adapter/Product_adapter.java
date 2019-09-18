package com.aryanonline.Adapter;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aryanonline.Fragment.Show_pro_detail_fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.aryanonline.Config.BaseURL;
import com.aryanonline.Model.Product_model;
import com.aryanonline.MainActivity;
import com.aryanonline.R;
import com.aryanonline.util.DatabaseHandler;

import static com.aryanonline.Fragment.Product_fragment.promodecolourandsizeList;


public class Product_adapter extends RecyclerView.Adapter<Product_adapter.MyViewHolder>
        implements Filterable {

    private List<Product_model> modelList;
    Product_model product_model;
    private List<Product_model> mFilteredList;
    private Context context;
    private DatabaseHandler dbcart;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_title, tv_price, tv_total, tv_contetiy, tv_add, mrpPrice,tv_colournsize,tv_delchr,tvSubscriptioncart;
        public ImageView iv_logo, iv_plus, iv_minus, iv_remove;

        public MyViewHolder(View view) {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_subcat_title);
            tvSubscriptioncart = (TextView) view.findViewById(R.id.tvSubscriptioncart);
            tv_delchr = (TextView) view.findViewById(R.id.tv_delchr);
            tv_price = (TextView) view.findViewById(R.id.tv_subcat_price);
            tv_total = (TextView) view.findViewById(R.id.tv_subcat_total);
            tv_contetiy = (TextView) view.findViewById(R.id.tv_subcat_contetiy);
            tv_add = (TextView) view.findViewById(R.id.tv_subcat_add);
            iv_logo = (ImageView) view.findViewById(R.id.iv_subcat_img);
            iv_plus = (ImageView) view.findViewById(R.id.iv_subcat_plus);
            iv_minus = (ImageView) view.findViewById(R.id.iv_subcat_minus);
            iv_remove = (ImageView) view.findViewById(R.id.iv_subcat_remove);
            mrpPrice = (TextView) view.findViewById(R.id.mrpPrice);
            tv_colournsize = (TextView) view.findViewById(R.id.tv_colournsize);

            iv_remove.setVisibility(View.GONE);

            iv_minus.setOnClickListener(this);
            iv_plus.setOnClickListener(this);
            tv_add.setOnClickListener(this);
            iv_logo.setOnClickListener(this);

            CardView cardView = (CardView) view.findViewById(R.id.card_view);
            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            int position = getAdapterPosition();

            if (id == R.id.iv_subcat_plus) {

                int qty = Integer.valueOf(tv_contetiy.getText().toString());
                qty = qty + 1;

                tv_contetiy.setText(String.valueOf(qty));

            } else if (id == R.id.iv_subcat_minus) {

                int qty = 1;
                if (!tv_contetiy.getText().toString().equalsIgnoreCase(""))
                    qty = Integer.valueOf(tv_contetiy.getText().toString());

                if (qty > 1) {
                    qty = qty - 1;
                    tv_contetiy.setText(String.valueOf(qty));
                }

            } else if (id == R.id.tv_subcat_add) {

                HashMap<String, String> map = new HashMap<>();

                map.put("product_id", modelList.get(position).getProductId());
                Log.e("product_id is " , ""+modelList.get(position).getProductId());
                map.put("category_id", modelList.get(position).getCategoryId());
                map.put("product_image", modelList.get(position).getProductImage());
                map.put("increament", modelList.get(position).getIncreament());
                map.put("offers_persent", modelList.get(position).getOffersPersent());

                map.put("product_name", modelList.get(position).getProductName());
                map.put("price", modelList.get(position).getPrice());
                map.put("stock", modelList.get(position).getInStock());
                map.put("title", modelList.get(position).getTitle());
                map.put("unit", modelList.get(position).getUnit());
                map.put("delivery_charg", modelList.get(position).getDelivery_charg());
                map.put("Mrp", modelList.get(position).getMrp());
                map.put("unit_value", modelList.get(position).getUnitValue());
                map.put("cod", modelList.get(position).getCod());
//                map.put("size",modelList.get(position).getColour());
//                map.put("colour",modelList.get(position).getSize());
//                map.put("colour",promodecolourandsizeList.get(position).getColour());
                if(!modelList.get(position).getCloth_color().isEmpty()) {
                    // map.put("cloth_colour", modelList.get(position).getCloth_color());
                    // map.put("colour", modelList.get(position).getCloth_color());
                    map.put("colour",promodecolourandsizeList.get(position).getColour());
                }else
                if(!modelList.get(position).getS_clolor().isEmpty()) {
                    //map.put("s_colour", modelList.get(position).getS_clolor());
//                    map.put("colour", modelList.get(position).getS_clolor());
                    map.put("colour",promodecolourandsizeList.get(position).getColour());
                }else {
                    map.put("colour","None");
                }
                Log.e("s_colour is",""+ modelList.get(position).getS_clolor());
                if(!modelList.get(position).getS_size().isEmpty()) {
                    //  map.put("size", modelList.get(position).getS_size());
                    map.put("size",promodecolourandsizeList.get(position).getSize());
                } else
                if(!modelList.get(position).getCloth_size().isEmpty()) {
                    //  map.put("size", modelList.get(position).getCloth_size());
                    map.put("size",promodecolourandsizeList.get(position).getSize());
                }else {
                    map.put("size","None");
                }

                if (!tv_contetiy.getText().toString().equalsIgnoreCase("0")) {

                    if (dbcart.isInCart(map.get("product_id"))) {
                        if(map.get("size").equals("None") || map.get("colour").equals("None")) {
//                            map.put("size" , "None");
//                            map.put("colour" , "None");
                       //  Boolean bx= dbcart.setCartwithoutsize(map, Float.valueOf(tv_contetiy.getText().toString()));
                         Boolean bx=  dbcart.setCart(map, Float.valueOf(tv_contetiy.getText().toString()));

                            tv_add.setText(context.getResources().getString(R.string.tv_pro_update));
//
                        }else {
//                            map.put("size" , "None");
//                            map.put("colour" , "None");
                            Boolean bx=   dbcart.setCart(map, Float.valueOf(tv_contetiy.getText().toString()));
//                            dbcart.setCartwithoutsize(map, Float.valueOf(tv_contetiy.getText().toString()));
                            tv_add.setText(context.getResources().getString(R.string.tv_pro_add));
                        }
                    } else {
//                        map.put("size" , "None");
//                        map.put("colour" , "None");
                        Boolean bx=  dbcart.setCart(map, Float.valueOf(tv_contetiy.getText().toString()));
                        tv_add.setText(context.getResources().getString(R.string.tv_pro_update));
                    }
                } else {
                    dbcart.removeItemFromCart(map.get("product_id"));
                    tv_add.setText(context.getResources().getString(R.string.tv_pro_add));
                }

                Double items = Double.parseDouble(dbcart.getInCartItemQty(map.get("product_id")));
                Double price = Double.parseDouble(map.get("price"));

                tv_total.setText("" + price * items);

                //      mrpPrice.setText(map.get("Mrp"));
                ((MainActivity) context).setCartCounter("" + dbcart.getCartCount());

            } else if (id == R.id.iv_subcat_img) {
                //showImage(modelList.get(position).getProduct_image());

                Bundle args = new Bundle();
                Fragment fm = new Show_pro_detail_fragment();
                args.putString("product_id", modelList.get(position).getProductId());
                args.putString("standard_d_date", modelList.get(position).getStandard_d_date());
                args.putString("category_id", modelList.get(position).getCategoryId());
                args.putString("product_image", modelList.get(position).getProductImage());
                args.putString("increament", modelList.get(position).getIncreament());
                args.putString("offers_persent", modelList.get(position).getOffersPersent());
                args.putString("product_name", modelList.get(position).getProductName());
                args.putString("price", modelList.get(position).getPrice());
                args.putString("stock", modelList.get(position).getInStock());
                args.putString("title", modelList.get(position).getTitle());
                args.putString("unit", modelList.get(position).getUnit());
                args.putString("Mrp", modelList.get(position).getMrp());
                args.putString("unit_value", modelList.get(position).getUnitValue());
                args.putString("Prod_description", modelList.get(position).getProductDescription());
                args.putString("EMI", modelList.get(position).getEmi());
                args.putString("Warantee", modelList.get(position).getWarranty());
                args.putString("product_offer_image", modelList.get(position).getProductOfferImage());
                args.putString("p_offer_description", modelList.get(position).getPOfferDescription());
                //+++++++++++++Extra++++++++++++++++++++++++++++
                args.putString("cloth_colour", modelList.get(position).getCloth_color());
                args.putString("cod", modelList.get(position).getCod());
                args.putString("s_colour", modelList.get(position).getS_clolor());
                Log.e("s_colour is",""+ modelList.get(position).getS_clolor());
                args.putString("s_size", modelList.get(position).getS_size());
                args.putString("delivery_charg", modelList.get(position).getDelivery_charg());
                args.putString("cloth_size", modelList.get(position).getCloth_size());
                //++++++++++++++++++++++++++++Sfd+++++++++++++++++
                fm.setArguments(args);

                FragmentManager fragmentManager = ((AppCompatActivity)context).getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contentPanel, fm)
                        .addToBackStack(null).commit();
            } else if (id == R.id.card_view) {


                Bundle args = new Bundle();
                Fragment fm = new Show_pro_detail_fragment();
                args.putString("product_id", modelList.get(position).getProductId());
                args.putString("standard_d_date", modelList.get(position).getStandard_d_date());
                args.putString("category_id", modelList.get(position).getCategoryId());
                args.putString("cloth_colour", modelList.get(position).getCloth_color());
                args.putString("cod", modelList.get(position).getCod());
                args.putString("offers_persent", modelList.get(position).getOffersPersent());
                args.putString("s_colour", modelList.get(position).getS_clolor());
                Log.e("s_colour is",""+ modelList.get(position).getS_clolor());
                args.putString("s_size", modelList.get(position).getS_size());
                args.putString("cloth_size", modelList.get(position).getCloth_size());
                args.putString("product_image", modelList.get(position).getProductImage());
                args.putString("increament", modelList.get(position).getIncreament());
                args.putString("product_name", modelList.get(position).getProductName());
                args.putString("price", modelList.get(position).getPrice());
                args.putString("stock", modelList.get(position).getInStock());
                args.putString("title", modelList.get(position).getTitle());
                args.putString("unit", modelList.get(position).getUnit());
                args.putString("Mrp", modelList.get(position).getMrp());
                args.putString("delivery_charg", modelList.get(position).getDelivery_charg());
                args.putString("unit_value", modelList.get(position).getUnitValue());
                args.putString("Prod_description", modelList.get(position).getProductDescription());
                args.putString("EMI", modelList.get(position).getEmi());
                args.putString("Warantee", modelList.get(position).getWarranty());
                args.putString("product_offer_image", modelList.get(position).getProductOfferImage());
                args.putString("p_offer_description", modelList.get(position).getPOfferDescription());


                fm.setArguments(args);

                FragmentManager fragmentManager = ((AppCompatActivity)context).getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contentPanel, fm)
                        .addToBackStack(null).commit();


             /*   showProductDetail(modelList.get(position).getProduct_image(),
                        modelList.get(position).getTitle(),
                        modelList.get(position).getProduct_description(),
                        modelList.get(position).getProduct_name(),
                        position, tv_contetiy.getText().toString());*/
            }
        }
    }

    public Product_adapter(List<Product_model> modelList, Context context) {
        this.modelList = modelList;
        this.mFilteredList = modelList;

        dbcart = new DatabaseHandler(context);
    }

    @Override
    public Product_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_product_rv, parent, false);

        context = parent.getContext();

        return new Product_adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Product_adapter.MyViewHolder holder, int position) {
        Product_model mList = modelList.get(position);
        Log.e("mList.getProductImage()","mList.getProductImage() "+mList.getProductImage());
        Log.e("mList","mList.getProductImage() "+mList.getProductName());
        holder.tv_delchr.setText(modelList.get(position).getDelivery_charg());
        Glide.with(context)
                .load(BaseURL.IMG_PRODUCT_URL + mList.getProductImage())
                .centerCrop()
                .placeholder(R.drawable.aryanmainlo)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(holder.iv_logo);

        holder.tv_title.setText(mList.getProductName());
        holder.mrpPrice.setText(mList.getMrp());
        if(!String.valueOf(mList.getOffersPersent()).equals("0")) {

            //Toast.makeText(context, "value is"+String.valueOf(mList.getOffersPersent()), Toast.LENGTH_SHORT).show();
            holder.tvSubscriptioncart.setText(String.valueOf(mList.getOffersPersent()) + "% off");
            holder.tvSubscriptioncart.setVisibility(View.VISIBLE);
//            notifyDataSetChanged();
        }
//        if(String.valueOf(mList.getOffersPersent()).equals("0"))
//        {
//            Toast.makeText(context, "value at else is"+String.valueOf(mList.getOffersPersent()), Toast.LENGTH_SHORT).show();
//            holder.tvSubscriptioncart.setVisibility(View.GONE);
////            notifyDataSetChanged();
//        }
//        holder.tv_price.setText(context.getResources().getString(R.string.tv_pro_price) + mList.getUnitValue() + " " +
//                mList.getUnit() + " " + context.getResources().getString(R.string.currency) + " " + mList.getPrice());
        holder.tv_price.setText(" " + context.getResources().getString(R.string.currency) + " " + mList.getPrice());
        try {
            if(!promodecolourandsizeList.get(position).getColour().equals("None")) {
                holder.tv_colournsize.setText("Selected Colour " + promodecolourandsizeList.get(position).getSize() + " With " + promodecolourandsizeList.get(position).getColour());
            } {
                holder.tv_colournsize.setVisibility(View.GONE);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        if (dbcart.isInCart(mList.getProductId())) {
            holder.tv_add.setText(context.getResources().getString(R.string.tv_pro_update));
            holder.tv_contetiy.setText(dbcart.getCartItemQty(mList.getProductId()));

        } else {
            holder.tv_add.setText(context.getResources().getString(R.string.tv_pro_add));
        }

        Double items = Double.parseDouble(dbcart.getInCartItemQty(mList.getProductId()));
        Double price = Double.parseDouble(mList.getPrice());

        holder.tv_total.setText("" + price * items);

    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = modelList;
                } else {

                    ArrayList<Product_model> filteredList = new ArrayList<>();

                    for (Product_model androidVersion : modelList) {

                        if (androidVersion.getProductName().toLowerCase().contains(charString)) {

                            filteredList.add(androidVersion);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Product_model>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

//    private void showImage(String image) {
//
//        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.product_image_dialog);
//        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//        dialog.show();
//
//        ImageView iv_image_cancle = (ImageView) dialog.findViewById(R.id.iv_dialog_cancle);
//        ImageView iv_image = (ImageView) dialog.findViewById(R.id.iv_dialog_img);
//
//        Glide.with(context)
//                .load(BaseURL.IMG_PRODUCT_URL + image)
//                .centerCrop()
//                .placeholder(R.drawable.aryanmainlo)
//                .crossFade()
//                .into(iv_image);
//
//        iv_image_cancle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//    }

//    private void showProductDetail(String image, String title, String description, String detail, final int position, String qty) {
//
//        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.dialog_product_detail);
//        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//        dialog.show();
//
//        ImageView iv_image = (ImageView) dialog.findViewById(R.id.iv_product_detail_img);
//        ImageView iv_minus = (ImageView) dialog.findViewById(R.id.iv_subcat_minus);
//        ImageView iv_plus = (ImageView) dialog.findViewById(R.id.iv_subcat_plus);
//        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_product_detail_title);
//        TextView tv_detail = (TextView) dialog.findViewById(R.id.tv_product_detail);
//        final TextView tv_contetiy = (TextView) dialog.findViewById(R.id.tv_subcat_contetiy);
//        final TextView tv_add = (TextView) dialog.findViewById(R.id.tv_subcat_add);
//
//        tv_title.setText(title);
//        tv_detail.setText(detail);
//        tv_contetiy.setText(qty);
//        tv_detail.setText(description);
//
//        Glide.with(context)
//                .load(BaseURL.IMG_PRODUCT_URL + image)
//                .centerCrop()
//                .placeholder(R.drawable.aryanmainlo)
//                .crossFade()
//                .into(iv_image);
//
//        if (dbcart.isInCart(modelList.get(position).getProductId())) {
//            tv_add.setText(context.getResources().getString(R.string.tv_pro_update));
//            tv_contetiy.setText(dbcart.getCartItemQty(modelList.get(position).getProductId()));
//        } else {
//            tv_add.setText(context.getResources().getString(R.string.tv_pro_add));
//        }
//
//        tv_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                HashMap<String, String> map = new HashMap<>();
//
//                map.put("product_id", modelList.get(position).getProductId());
//                map.put("category_id", modelList.get(position).getCategoryId());
//                map.put("product_image", modelList.get(position).getProductImage());
//                map.put("increament", modelList.get(position).getIncreament());
//                map.put("product_name", modelList.get(position).getProductName());
//                if(!modelList.get(position).getCloth_color().isEmpty()) {
//                    // map.put("cloth_colour", modelList.get(position).getCloth_color());
//                    // map.put("colour", modelList.get(position).getCloth_color());
//                    Log.e("colour is",""+promodecolourandsizeList.get(0).getColour());
//                    map.put("colour",promodecolourandsizeList.get(0).getColour());
//                }
//                map.put("cod", modelList.get(position).getCod());
//                if(!modelList.get(position).getS_clolor().isEmpty()) {
//                    //map.put("s_colour", modelList.get(position).getS_clolor());
////                    map.put("colour", modelList.get(position).getS_clolor());
//                    Log.e("colour is",""+promodecolourandsizeList.get(0).getColour());
//                    map.put("colour",promodecolourandsizeList.get(0).getColour());
//                }
//                Log.e("s_colour is",""+ modelList.get(position).getS_clolor());
//                if(!modelList.get(position).getS_size().isEmpty()) {
//                    //  map.put("size", modelList.get(position).getS_size());
//                    Log.e("colour is",""+promodecolourandsizeList.get(0).getSize());
//                    map.put("size",promodecolourandsizeList.get(0).getSize());
//                }
//                if(!modelList.get(position).getCloth_size().isEmpty()) {
//                    //  map.put("size", modelList.get(position).getCloth_size());
//                    Log.e("colour is",""+promodecolourandsizeList.get(0).getSize());
//                    map.put("size",promodecolourandsizeList.get(0).getSize());
//                }
////                map.put("cloth_colour", modelList.get(position).getCloth_color());
////                map.put("cod", modelList.get(position).getCod());
////                map.put("s_colour", modelList.get(position).getS_clolor());
////                Log.e("s_colour is",""+ modelList.get(position).getS_clolor());
////                map.put("s_size", modelList.get(position).getS_size());
////                map.put("cloth_size", modelList.get(position).getCloth_size());
//                map.put("price", modelList.get(position).getPrice());
//                map.put("stock", modelList.get(position).getInStock());
//                map.put("title", modelList.get(position).getTitle());
//                map.put("unit", modelList.get(position).getUnit());
//
//                map.put("unit_value", modelList.get(position).getUnitValue());
//
//                if (!tv_contetiy.getText().toString().equalsIgnoreCase("0")) {
//
//                    if (dbcart.isInCart(map.get("product_id"))) {
//                        dbcart.setCart(map, Float.valueOf(tv_contetiy.getText().toString()));
//                        tv_add.setText(context.getResources().getString(R.string.tv_pro_update));
//                    } else {
//                        dbcart.setCart(map, Float.valueOf(tv_contetiy.getText().toString()));
//                        tv_add.setText(context.getResources().getString(R.string.tv_pro_update));
//                    }
//                } else {
//                    dbcart.removeItemFromCart(map.get("product_id"));
//                    tv_add.setText(context.getResources().getString(R.string.tv_pro_add));
//                }
//
//                Double items = Double.parseDouble(dbcart.getInCartItemQty(map.get("product_id")));
//                Double price = Double.parseDouble(map.get("price"));
//
//                ((MainActivity) context).setCartCounter("" + dbcart.getCartCount());
//
//                notifyItemChanged(position);
//
//            }
//        });
//
//        iv_plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int qty = Integer.valueOf(tv_contetiy.getText().toString());
//                qty = qty + 1;
//
//                tv_contetiy.setText(String.valueOf(qty));
//            }
//        });
//
//        iv_minus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int qty = 0;
//                if (!tv_contetiy.getText().toString().equalsIgnoreCase(""))
//                    qty = Integer.valueOf(tv_contetiy.getText().toString());
//
//                if (qty > 0) {
//                    qty = qty - 1;
//                    tv_contetiy.setText(String.valueOf(qty));
//                }
//            }
//        });
//
//    }

}