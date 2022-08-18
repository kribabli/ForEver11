package com.example.yoyoiq.WalletPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yoyoiq.KYC.KYCActivity;
import com.example.yoyoiq.R;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.example.yoyoiq.common.HelperData;
import com.example.yoyoiq.common.SessionManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecentTransactions extends AppCompatActivity {
    TextView backPress;
    ListView listViewNotification;
    SessionManager sessionManager;
    MyAdapter myAdapter;
   ArrayList<transection> list=new ArrayList<>();
    String id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_transactions);
        sessionManager=new SessionManager(getApplicationContext());
        listViewNotification=findViewById(R.id.listViewNotification);
        myAdapter=new MyAdapter();
        listViewNotification.setAdapter(myAdapter);
        backPress=findViewById(R.id.backPress);

        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        Call<ViewTransactionHistoryResponse> call= ApiClient.getInstance().getApi().getTransactionDetails(HelperData.UserId);
         call.enqueue(new Callback<ViewTransactionHistoryResponse>() {
             @Override
             public void onResponse(Call<ViewTransactionHistoryResponse> call, Response<ViewTransactionHistoryResponse> response) {
                 ViewTransactionHistoryResponse viewTransactionHistoryResponse= response.body();
                 if(response.isSuccessful()){
                     if(viewTransactionHistoryResponse!=null){
                         list.clear();
                         String detailsData=new Gson().toJson(viewTransactionHistoryResponse.getDetail());
                         JSONArray jsonArray=null;
                        try{
                            jsonArray=new JSONArray(detailsData);
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String userid=jsonObject.getString("user_id");
                                String amount=jsonObject.getString("amount");
                                String created_date=jsonObject.getString("created_date");
                                String transection_id=jsonObject.getString("transection_id");
                                String type=jsonObject.getString("type");
                                transection dataholder=new transection(userid,type,amount,transection_id,created_date);
                                list.add(dataholder);
                                myAdapter.notifyDataSetChanged();




                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                     }

                 }

             }

             @Override
             public void onFailure(Call<ViewTransactionHistoryResponse> call, Throwable t) {

             }
         });

    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) RecentTransactions.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            transection data=list.get(i);
            view = inflater.inflate(R.layout.transaction_history_layout, null, false);
            TextView reason = (TextView) view.findViewById(R.id.reason);
            TextView symbol_rupees = (TextView) view.findViewById(R.id.symbol_rupees);
            TextView signConvention = (TextView) view.findViewById(R.id.signConvention);
            LinearLayout layout=(LinearLayout) view.findViewById(R.id.layout);
            ImageView Bill_Image = (ImageView) view.findViewById(R.id.expand_activities_button);
            if(data.getType().equalsIgnoreCase("credit")){
                signConvention.setText("+");
                signConvention.setTextColor(Color.parseColor("#64e764"));
            }
            else{
                signConvention.setText("-");
                signConvention.setTextColor(Color.parseColor("#D70101"));
            }
            symbol_rupees.setText(data.getAmount());
            reason.setText("Deposited Cash");
            Bill_Image.setOnClickListener(view1 -> {
                final View deleteDialogView = LayoutInflater.from(RecentTransactions.this).inflate(R.layout.show_transaction_details, null);
                TextView textView1=deleteDialogView.findViewById(R.id.transactionId);
                TextView textView2=deleteDialogView.findViewById(R.id.transactionDate);
                TextView textView3=deleteDialogView.findViewById(R.id.TeamName);

                final AlertDialog deleteDialog = new AlertDialog.Builder(RecentTransactions.this).create();
                deleteDialog.setView(deleteDialogView);
                textView1.setText(""+data.getTransection_id());
                textView2.setText(""+data.getCreated_date());
                textView3.setText(""+sessionManager.getUserData().getUserName());

                deleteDialog.show();
            });
            return view;
        }
    }
}