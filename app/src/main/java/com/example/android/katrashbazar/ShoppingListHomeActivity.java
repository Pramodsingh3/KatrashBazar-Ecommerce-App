package com.example.android.katrashbazar;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShoppingListHomeActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private FloatingActionButton fab_btn;
    private DatabaseReference mdatabase;
    private FirebaseDatabase mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_home);

        toolbar=findViewById(R.id.home_toolbar);
       setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Daily Shopping List");
        fab_btn=findViewById(R.id.fab_shopping_list);

        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
        });

    }

    private void customDialog(){
        AlertDialog.Builder mydialog=new AlertDialog.Builder(ShoppingListHomeActivity.this);
        LayoutInflater inflater = LayoutInflater.from(ShoppingListHomeActivity.this);
        View myview = inflater.inflate(R.layout.input_data,null);
        final AlertDialog dialog = mydialog.create();
        dialog.setView(myview);
        final EditText type = myview.findViewById(R.id.edt_type);
        final EditText amount = myview.findViewById(R.id.edt_amount);
        final EditText note = myview.findViewById(R.id.edt_note);
      Button btnsave = myview.findViewById(R.id.save_to_list);


      btnsave.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view)
          {
              String mType=type.getText().toString().trim();
              String mAmount=amount.getText().toString().trim();
              String mNote=note.getText().toString().trim();

              if(TextUtils.isEmpty(mType))
              {
                  type.setError("Required field..");
                  return;
              }
              if(TextUtils.isEmpty(mAmount))
              {
                  amount.setError("Requred field...");
                  return;

              }
              if(TextUtils.isEmpty(mNote))
              {
                  note.setError("required field..");
                  return;
              }

              dialog.dismiss();
          }
      });


        dialog.show();
    }

    private void setSupportActionBar(Toolbar toolbar)
    {

    }
}
