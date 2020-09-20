package com.example.android.katrashbazar;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.katrashbazar.Model.AdminOrders;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminNewOrdersActivityActivity extends AppCompatActivity

{
    private RecyclerView orderList;
    private DatabaseReference ordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_orders_activity);
        ordersRef = FirebaseDatabase.getInstance().getReference().child("orders");
        orderList = findViewById(R.id.orders_list);
        orderList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<AdminOrders>options =
                new FirebaseRecyclerOptions.Builder<AdminOrders>()
                .setQuery(ordersRef, AdminOrders.class)
                .build();

        FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder> adapter =
          new FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder>(options) {
              @Override
              protected void onBindViewHolder(@NonNull AdminOrdersViewHolder holder, final int position, @NonNull final AdminOrders model)
              {
                  holder.userName.setText("Name:" + model.getName());
                  holder.userPhoneNumber.setText("Phone:" + model.getPhone());
                  holder.userShippingAddress.setText("Shipping Address:- " + model.getAddress() + " , " + model.getCity());
                  holder.userDataTime.setText("Order at:" + model.getDate() +  " " + model.getTime());
                  holder.userTotalPrice.setText("Total Amount = Rs. " + model.getTotalAmount());

                  holder.ShowOrderBtn.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          String uID = getRef(position).getKey();
                          Intent intent = new Intent(AdminNewOrdersActivityActivity.this,AdminUserProductsActivity.class);
                          intent.putExtra("uid",uID);
                          startActivity(intent);
                      }
                  });
                  holder.itemView.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v)
                      {
                          CharSequence options[] = new CharSequence[]
                                  {
                                          "Yes",
                                          "No"
                                  };
                          AlertDialog.Builder builder = new AlertDialog.Builder(AdminNewOrdersActivityActivity.this);
                          builder.setTitle("Have you shipped this order products");
                          builder.setItems(options, new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int i)
                              {
                                  if (i == 0)
                                  {
                                      String uID = getRef(position).getKey();
                                      RemoverOrder(uID);
                                  }
                                  else
                                  {
                                      finish();
                                  }
                              }
                          });
                          builder.show();

                      }
                  });

              }

              @NonNull
              @Override
              public AdminOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
                  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout, parent, false);
                  return new AdminOrdersViewHolder(view);
              }
          };
        orderList.setAdapter(adapter);
        adapter.startListening();
    }


    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder
    {
        public TextView userName ,userPhoneNumber, userTotalPrice, userDataTime, userShippingAddress;
         public Button ShowOrderBtn;


        public AdminOrdersViewHolder(@NonNull View itemView)
        {
            super(itemView);

            userName = itemView.findViewById(R.id.order_user_name);
            userPhoneNumber = itemView.findViewById(R.id.order_phone_number);
            userTotalPrice = itemView.findViewById(R.id.order_total_price);
            userDataTime= itemView.findViewById(R.id.order_date_time);
            userShippingAddress = itemView.findViewById(R.id.order_address_city);
           ShowOrderBtn = itemView.findViewById(R.id.show_all_produuct_btn);
        }
    }

    private void RemoverOrder(String uID)
    {
        ordersRef.child(uID).removeValue();
    }
}
