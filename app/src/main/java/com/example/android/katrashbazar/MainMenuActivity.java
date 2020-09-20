package com.example.android.katrashbazar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity
{
    private Button btnlist, btnshpo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

  btnlist = (Button)findViewById(R.id.shopping_list_go);
  btnshpo = (Button)findViewById(R.id.online_activity_go);
  btnlist.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v)
      {
          Intent intent = new Intent(MainMenuActivity.this,ShoppingListHomeActivity.class);
          startActivity(intent);
      }
  });

  btnshpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this,HomeActivity.class);
                startActivity(intent);

            }
        });

        btnshpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this,HomeActivity.class);
                startActivity(intent);

            }
        });

    }
}
