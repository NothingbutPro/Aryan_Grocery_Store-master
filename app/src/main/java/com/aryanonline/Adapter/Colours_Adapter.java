package com.aryanonline.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aryanonline.Config.BaseURL;
import com.aryanonline.Model.DealsModel;
import com.aryanonline.R;
import com.aryanonline.util.DatabaseHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.HashMap;

import static com.aryanonline.Fragment.Product_fragment.promodecolourandsizeList;
import static com.aryanonline.Fragment.Show_pro_detail_fragment.colour_sel;
import static com.aryanonline.Fragment.Show_pro_detail_fragment.colour_sel2;

public class Colours_Adapter  extends RecyclerView.Adapter<Colours_Adapter.ViewHolder>  {
    private static final String TAG = "DealsAdapter";
    private ArrayList<String> dealList;
    public Context context;
    String resId = "";
    String finalStatus = "";
    String Image;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView color_text ;
        View colour_id;


        public ViewHolder(View view) {
            super(view);

            color_text = (TextView) view.findViewById(R.id.color_text);
            colour_id = view.findViewById(R.id.colour_id);
            //   card = (LinearLayout) view.findViewById(R.id.card_view);
        }
    }

    public static Context mContext;

    public Colours_Adapter(Context mContext, ArrayList<String> deals_list) {
        context = mContext;
        dealList = deals_list;

    }

    @Override
    public Colours_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.colorfor, parent, false);

        return new Colours_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Colours_Adapter.ViewHolder viewHolder, final int position) {
        String Colour = dealList.get(position);
        Log.d("colour" , "is"+Colour);
        //  viewHolder.colour_id.setBackgroundColor(Color.RED);
        try {
            viewHolder.colour_id.setBackgroundColor(Color.parseColor(Colour));
            viewHolder.color_text.setText(Colour);
            if(position ==0)
            {
                promodecolourandsizeList.get(position).setColour(String.valueOf(Color.parseColor(Colour)));
                colour_sel.setText(dealList.get(0));
                colour_sel2.setText(dealList.get(0));
                colour_sel.setBackgroundColor(Color.parseColor(Colour));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                colour_sel.setText(dealList.get(position));
                colour_sel2.setText(dealList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return dealList.size();
    }

}
