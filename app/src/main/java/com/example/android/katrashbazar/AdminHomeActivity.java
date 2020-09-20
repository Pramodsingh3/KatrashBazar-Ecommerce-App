package com.example.android.katrashbazar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminHomeActivity extends AppCompatActivity {
    private Button OnlineShop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);



        OnlineShop = (Button) findViewById(R.id.admin_add_goods);
        OnlineShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this,AdminAddGoods.class);
                startActivity(intent);
            }
        });
    }
}
