package com.aryanonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.aryanonline.R;

import java.util.ArrayList;
import java.util.List;

public class MyReviewsAdapter extends RecyclerView.Adapter<MyReviewsAdapter.MyViewHolder> {
    private Context mContext;
    public static ArrayList<String> Myproducttiles = new ArrayList<>();
    int pos_try;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    public List<AddMyReviews> Doc;
    private AddMyReviews addNews141;

    public MyReviewsAdapter(Context context, List<AddMyReviews> doc) {
        mContext=context;
        this.Doc = doc;
        setHasStableIds(true);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView manu_img;
        Button removenew , addnewf;
        EditText addnewkey;

        public MyViewHolder(View itemView) {
            super(itemView);
            //   mname = (TextView) itemView.findViewById(R.id.mname);
            //  bra_bus =  itemView.findViewById(R.id.bra_bus);
            // manu_img =  itemView.findViewById(R.id.manu_img);
           // removenew =  itemView.findViewById(R.id.removenew);
            addnewf =  itemView.findViewById(R.id.addnewf);
          //  addnewkey =  itemView.findViewById(R.id.addnewkey);
            //     adddes =  itemView.findViewById(R.id.adddes);


        }
    }


    @Override
    public MyReviewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newkeywords, parent, false);
        return new MyReviewsAdapter.MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final MyReviewsAdapter.MyViewHolder holder, final int position) {
        addNews141 = Doc.get(position);
        this.pos_try = position;

        holder.removenew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Doc.remove(position);
                notifyDataSetChanged();
                if (position == 0) {
                    //  Toast.makeText(mContext, "xyz "+holder.addnewkey.getText().toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent("filter_string");
                    //   Intent intent = new Intent(v.getContext() , AddProductWay.class);
                    intent.putExtra("removekey", "3");
                    // put your all data using put extra

                    LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);
                }
            }
        });
        holder.addnewf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.addnewkey.setText(holder.addnewkey.getText().toString());
              //  addNews141.setNamesDetails(holder.addnewkey.getText().toString());
                //   holder.addnewkey.setText(holder.addnewkey.getText().toString());
                //    addNews141.setName(holder.addnewkey);

                //  Myproducttiles.add(holder.addnewkey.getText().toString());
                //    Myproducttiles.add(holder.addnewkey.getText().toString());
                //  Toast.makeText(mContext, "xyz "+holder.addnewkey.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("filter_string");
                //   Intent intent = new Intent(v.getContext() , AddProductWay.class);
                //   intent.putExtra("Add_More_Details_Key", holder.addnewkey.getText().toString());
                intent.putExtra("Add_More_Details_Key", holder.addnewkey.getText().toString());
                // put your all data using put extra

                LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);
//                holder.mname.getText().toString();
//                holder.mname.setText();
                // Myproductsdescrip.add(holder.adddes.getText().toString());
                try {
                    //   Toast.makeText(mContext, "" + Myproductsdescrip.get(position), Toast.LENGTH_SHORT).show();
                    //   Toast.makeText(mContext, "" + Myproducttiles.get(position), Toast.LENGTH_SHORT).show();
                    Toast.makeText(mContext, "Working on api", Toast.LENGTH_SHORT).show();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
//                Myproducttiles.add(holder.addnewkey.getText().toString());
//                Myproducttiles.add(holder.addnewkey.getText().toString());
////                holder.mname.getText().toString();
////                holder.mname.setText();
//                // Myproductsdescrip.add(holder.adddes.getText().toString());
//                try {
//                    //   Toast.makeText(mContext, "" + Myproductsdescrip.get(position), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(mContext, "" + Myproducttiles.get(position), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(mContext, "Working on api", Toast.LENGTH_SHORT).show();
//                }catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
            }

        });
//        searching_manufacturers_data = Doc.get(pos_try);
//        Log.e("Position","is "+pos_try);
//        document = searching_manufacturers_data.getBrandName();
        StrictMode.setVmPolicy(builder.build());

    }





//    private void Try_Sent_Reuqest(AddNewsAdapter.MyViewHolder holder, Context context, int us, String id) {
//        Log.d("us is" ,""+ us);
//        Log.d("their is" ,""+ id);
//        ProgressDialog progressDialog = new ProgressDialog(context);
//        progressDialog.setTitle("Getting Country");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.show();
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(100, TimeUnit.SECONDS)
//                .readTimeout(100,TimeUnit.SECONDS).build();
//        Retrofit RetroLogin = new Retrofit.Builder()
//                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
//                .build();
//        Api AbloutApi = RetroLogin.create(Api.class);
//        Log.d("sortname is" , id);
//        Call<Connection_Requests> Get_All_Country_New = AbloutApi.CONNECTION_REQUESTS_CALL( String.valueOf(us),id);
//        Get_All_Country_New.enqueue(new Callback<Connection_Requests>() {
//            @Override
//            public void onResponse(Call<Connection_Requests> call, Response<Connection_Requests> response) {
//                Log.e("getcity" , ""+response.toString());
//                if (response!=null){
//                    Log.e("Get_City",""+response.body().getResponce());
//                    try {
//                        if(response.body().getResponce() == true)
//                        {
//                            Toast.makeText(context, ""+response.body().getData(), Toast.LENGTH_SHORT).show();
//
//                            holder.conbtn.setText("Request Sent");
//                        }else {
//                            Toast.makeText(context, "Problem in sending request", Toast.LENGTH_SHORT).show();
//                        }
//                        // countyed.showDropDown();
//                        // Toast.makeText(RegistrationActivityTwo.this, "true", Toast.LENGTH_SHORT).show();
//                    }catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//
//                progressDialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<Connection_Requests> call, Throwable t) {
//                progressDialog.dismiss();
//                Log.e("error_country",""+t.getMessage());
//                Toast.makeText(context, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//
//    }


    @Override
    public int getItemCount() {
        return Doc.size();
    }

    @Override
    public long getItemId(int position) {
//        return super.getItemId(position);
        return  position;
    }
}
