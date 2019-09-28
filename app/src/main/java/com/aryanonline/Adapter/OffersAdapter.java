package com.aryanonline.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aryanonline.Config.BaseURL;
import com.aryanonline.Model.DealsModel;
import com.aryanonline.Model.Offers;
import com.aryanonline.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder>  {
    private static final String TAG = "DealsAdapter";
    private ArrayList<Offers> dealList;
    public Context context;
    String resId = "";
    String finalStatus = "";
    String Image;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView idProductName1;
        LinearLayout card;
        WebView txtImage1;
        int pos;

        public ViewHolder(View view) {
            super(view);

        //    idProductName1 = (TextView) view.findViewById(R.id.idProductName1);
            txtImage1 = (WebView) view.findViewById(R.id.txtImage1);
            //   card = (LinearLayout) view.findViewById(R.id.card_view);
        }
    }

    public static Context mContext;

    public OffersAdapter(Context mContext, ArrayList<Offers> deals_list) {
        context = mContext;
        dealList = deals_list;

    }

    @Override
    public OffersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offers, parent, false);

        return new OffersAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final OffersAdapter.ViewHolder viewHolder, final int position) {
        Offers dealsModel = dealList.get(position);
        Image = dealsModel.getLocationOffers();
        viewHolder.txtImage1.loadData(Image, "text/html; charset=UTF-8", null);
//        viewHolder.txtImage1.setText(Image);
//        Glide.with(context)
//                .load(BaseURL.IMG_PRODUCT_URL+Image)
//                .placeholder(R.drawable.aryanmainlo)
//                .crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .dontAnimate()
//                .into(viewHolder.Image1);
        // viewHolder.card.setTag(viewHolder);
        viewHolder.pos = position;

    }

    @Override
    public int getItemCount() {
        return dealList.size();
    }

}
