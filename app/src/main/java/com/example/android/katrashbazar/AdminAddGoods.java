package com.example.android.katrashbazar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdminAddGoods extends AppCompatActivity
{
     private ImageView tshirts, sportsTshirts, femaleDresses, sweathers;
    private ImageView glasses, hatsCaps, wallletsbagspurses, shoes;
    private ImageView headPhonesHandFree, Laptops, watches, mobilePhones;

    private Button LogoutBtn, CheckOrderBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_goods);

        LogoutBtn = (Button) findViewById(R.id.admin_Logout_btn);

        CheckOrderBtn = (Button) findViewById(R.id.check_new_order_btn);
        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
              Intent intent = new Intent(AdminAddGoods.this, MainActivity.class)  ;
             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
              startActivity(intent);
              finish();
            }
        });

        CheckOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminAddGoods.this, AdminNewOrdersActivityActivity.class)  ;

                startActivity(intent);

            }
        });

        tshirts = (ImageView) findViewById(R.id.t_shirts);
        sportsTshirts = (ImageView) findViewById(R.id.sports_t_shirts);
        femaleDresses = (ImageView) findViewById(R.id.female_dresses);
        sweathers = (ImageView) findViewById(R.id.sweathers);
       glasses = (ImageView) findViewById(R.id.glasses);
       hatsCaps = (ImageView) findViewById(R.id.hats_caps);
       wallletsbagspurses = (ImageView) findViewById(R.id.purses_bag_wallets);
        shoes = (ImageView) findViewById(R.id.shoes);
        headPhonesHandFree = (ImageView) findViewById(R.id.headphones_handfree);
        Laptops = (ImageView) findViewById(R.id.laptop_pc);
       watches = (ImageView) findViewById(R.id.watches);
        mobilePhones = (ImageView) findViewById(R.id.mobiles);


        tshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminAddGoods.this, AdminUploadGoodsActivity.class);
                intent.putExtra("category","tShirts");
                startActivity(intent);
            }
        });

        sportsTshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminAddGoods.this, AdminUploadGoodsActivity.class);
                intent.putExtra("category","sports tShirts");
                startActivity(intent);
            }
        });

        femaleDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminAddGoods.this, AdminUploadGoodsActivity.class);
                intent.putExtra("category","Female Dresses");
                startActivity(intent);
            }
        });

        sweathers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminAddGoods.this, AdminUploadGoodsActivity.class);
                intent.putExtra("category","Sweathers");
                startActivity(intent);
            }
        });

       glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminAddGoods.this, AdminUploadGoodsActivity.class);
                intent.putExtra("category","Glasses");
                startActivity(intent);
            }
        });

        hatsCaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminAddGoods.this, AdminUploadGoodsActivity.class);
                intent.putExtra("category","Hats Caps");
                startActivity(intent);
            }
        });

        wallletsbagspurses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminAddGoods.this, AdminUploadGoodsActivity.class);
                intent.putExtra("category","Wallets Bags Purses");
                startActivity(intent);
            }
        });

        headPhonesHandFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminAddGoods.this, AdminUploadGoodsActivity.class);
                intent.putExtra("category","Headphones Handfree");
                startActivity(intent);
            }
        });

        Laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminAddGoods.this, AdminUploadGoodsActivity.class);
                intent.putExtra("category","Laptops Pc");
                startActivity(intent);
            }
        });


        watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminAddGoods.this, AdminUploadGoodsActivity.class);
                intent.putExtra("category","Watches");
                startActivity(intent);
            }
        });


       mobilePhones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminAddGoods.this, AdminUploadGoodsActivity.class);
                intent.putExtra("category","Mobile Phones");
                startActivity(intent);
            }
        });

    }
}
