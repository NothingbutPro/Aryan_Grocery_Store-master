package com.aryanonline.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aryanonline.R;

import java.util.ArrayList;

import static com.aryanonline.Fragment.Product_fragment.promodecolourandsizeList;
import static com.aryanonline.Fragment.Show_pro_detail_fragment.size_sel;
import static com.aryanonline.Fragment.Show_pro_detail_fragment.size_sel2;

public class SizesAdapter  extends RecyclerView.Adapter<SizesAdapter.ViewHolder>  {
    private static final String TAG = "DealsAdapter";
    private ArrayList<String> dealList;
    public Context context;
    String resId = "";
    String finalStatus = "";
    String Image;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView size_id ;



        public ViewHolder(View view) {
            super(view);

            size_id = (TextView) view.findViewById(R.id.size_id);

            //   card = (LinearLayout) view.findViewById(R.id.card_view);
        }
    }

    public static Context mContext;

    public SizesAdapter(Context mContext, ArrayList<String> deals_list) {
        context = mContext;
        dealList = deals_list;

    }

    @Override
    public SizesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sizefor, parent, false);

        return new SizesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SizesAdapter.ViewHolder viewHolder, final int position) {
        String Colour = dealList.get(position);
        viewHolder.size_id.setText(Colour);
        if(position==0)
        {
            promodecolourandsizeList.get(position).setSize(dealList.get(position));
            size_sel.setText(dealList.get(position));
            size_sel2.setText(dealList.get(position));
        }
        viewHolder.size_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {


//                    if (promodecolourandsizeList.get(position) != null) {
                      //  promodecolourandsizeList.get(position).setSize(dealList.get(position));
                        size_sel.setText(dealList.get(position));
                        size_sel2.setText(dealList.get(position));
//                    }
                }catch (Exception e)
                {
                    size_sel.setText(dealList.get(position));
                    Toast.makeText(context, "it isd null", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dealList.size();
    }

}
