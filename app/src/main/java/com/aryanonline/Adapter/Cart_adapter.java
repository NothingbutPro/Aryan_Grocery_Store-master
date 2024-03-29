package com.aryanonline.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aryanonline.Config.BaseURL;
import com.aryanonline.R;
import com.aryanonline.util.DatabaseHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart_adapter extends RecyclerView.Adapter<Cart_adapter.ProductHolder> {
    //private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    //ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    ArrayList<HashMap<String, String>> list;
    Activity activity;
    int lastpostion;
    /*CommonClass common;
    DisplayImageOptions options;
    ImageLoaderConfiguration imgconfig;*/
    DatabaseHandler dbHandler;

    public Cart_adapter(Activity activity, ArrayList<HashMap<String, String>> list) {
        this.list = list;
        this.activity = activity;

        dbHandler = new DatabaseHandler(activity);

    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);

        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductHolder holder, final int position) {
        final HashMap<String, String> map = list.get(position);

        Glide.with(activity)
                .load(BaseURL.IMG_PRODUCT_URL + map.get("product_image"))
                //  .centerCrop()
                .placeholder(R.drawable.aryanmainlo)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(holder.iv_logo);
        Log.e("map has adapter" ,""+ map.get("cloth_size"));
        holder.tv_title.setText(map.get("product_name"));
        holder.tv_price.setText(activity.getResources().getString(R.string.currency)+" "+ map.get("price"));
        holder.tv_contetiy.setText(map.get("qty"));

        Double items = Double.parseDouble(dbHandler.getInCartItemQty(map.get("product_id")));
        Double price = Double.parseDouble(map.get("price"));

        holder.tv_total.setText("" + price * items);
        holder.iv_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = 1;
                if (!holder.tv_contetiy.getText().toString().equalsIgnoreCase(""))
                    qty = Integer.valueOf(holder.tv_contetiy.getText().toString());

                if (qty > 1) {
                    qty = qty - 1;
                    holder.tv_contetiy.setText(String.valueOf(qty));
                }

                if (holder.tv_contetiy.getText().toString().equalsIgnoreCase("0")) {
                    dbHandler.removeItemFromCart(map.get("product_id"));
                    list.remove(position);
                    notifyDataSetChanged();

                    updateintent();
                }
                if(!map.get("size").isEmpty() || !map.get("colour").isEmpty()) {
                    map.put("size" , "None");
                    map.put("colour" , "None");
                    dbHandler.setCart(map, Float.valueOf(holder.tv_contetiy.getText().toString()));
                }else {
                    map.put("size" , "None");
                    map.put("colour" , "None");
                    dbHandler.setCartwithoutsize(map, Float.valueOf(holder.tv_contetiy.getText().toString()));
                  //  setCartwithoutsize
                }

                Double items = Double.parseDouble(dbHandler.getInCartItemQty(map.get("product_id")));
                Double price = Double.parseDouble(map.get("price"));

                holder.tv_total.setText("" + price * items );
                //holder.tv_total.setText(activity.getResources().getString(R.string.tv_cart_total) + price * items + " " +activity.getResources().getString(R.string.currency));
                updateintent();

            }
        });

        holder.iv_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int qty = Integer.valueOf(holder.tv_contetiy.getText().toString());
                qty = qty + 1;

                holder.tv_contetiy.setText(String.valueOf(qty));
                if(!map.get("colour").isEmpty() || !map.get("size").isEmpty()  ) {
                    map.put("size" , "None");
                    map.put("colour" , "None");
                    dbHandler.setCart(map, Float.valueOf(holder.tv_contetiy.getText().toString()));
                }else {
                    map.put("size" , "None");
                    map.put("colour" , "None");
                    dbHandler.setCartwithoutsize(map, Float.valueOf(holder.tv_contetiy.getText().toString()));
                }
                Double items = Double.parseDouble(dbHandler.getInCartItemQty(map.get("product_id")));
                Double price = Double.parseDouble(map.get("price"));

                holder.tv_total.setText("" + price * items );
                //holder.tv_total.setText(activity.getResources().getString(R.string.tv_cart_total) + price * items + " " +activity.getResources().getString(R.string.currency));
                updateintent();

            }
        });

        holder.iv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.removeItemFromCart(map.get("product_id"));
                list.remove(position);
                notifyDataSetChanged();

                updateintent();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        public TextView tv_title, tv_price, tv_total, tv_contetiy, tv_add,
                tv_unit,tv_unit_value;
        public ImageView iv_logo, iv_plus, iv_minus, iv_remove;

        public ProductHolder(View view) {
            super(view);

            tv_title = (TextView) view.findViewById(R.id.tv_subcat_title);
            tv_price = (TextView) view.findViewById(R.id.tv_subcat_price);
            tv_total = (TextView) view.findViewById(R.id.tv_subcat_total);
            tv_contetiy = (TextView) view.findViewById(R.id.tv_subcat_contetiy);
            //tv_add = (TextView) view.findViewById(R.id.tv_subcat_add);
            iv_logo = (ImageView) view.findViewById(R.id.iv_subcat_img);
            iv_plus = (ImageView) view.findViewById(R.id.iv_subcat_plus);
            iv_minus = (ImageView) view.findViewById(R.id.iv_subcat_minus);
            iv_remove = (ImageView) view.findViewById(R.id.iv_subcat_remove);

            //tv_add.setText(R.string.tv_pro_update);

        }
    }

    private void updateintent(){
        Intent updates = new Intent("Grocery_cart");
        updates.putExtra("type", "update");
        activity.sendBroadcast(updates);
    }

}

