package com.example.android.katrashbazar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.katrashbazar.Model.User;
import com.rey.material.widget.CheckBox;
import com.example.android.katrashbazar.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    private EditText InputPhoneNumber, InputPassword   ;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private TextView AdminLink, NotAdminLink;
    private CheckBox chkBoxRememberMe;


    private String parentDbName="Users";







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        LoginButton = (Button) findViewById(R.id.login_btn);

        InputPhoneNumber = (EditText) findViewById(R.id.login_phone_number);
        InputPassword = (EditText) findViewById(R.id.login_password_input);
        loadingBar = new ProgressDialog( this);
        AdminLink = (TextView) findViewById(R.id.admin_panel_link);
        NotAdminLink = (TextView) findViewById(R.id.not_admin_panel_link);

        chkBoxRememberMe = (CheckBox) findViewById(R.id.remember_me_chkb);
        Paper.init(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LoginUser();
            }
        });
        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LoginButton.setText("Login admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbName = "Admins";
            }
        });
        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                LoginButton.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDbName = "Users";
            }
        });
    }

    private void LoginUser() {
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

           if (TextUtils.isEmpty(phone)){
            Toast.makeText( this, "PLEASE WRITE YOUR number..", Toast.LENGTH_LONG);
        }
        else   if (TextUtils.isEmpty(password)){
            Toast.makeText( this, "PLEASE WRITE YOUR password..", Toast.LENGTH_LONG);
        }

        else{

               loadingBar.setTitle("Login Account");
               loadingBar.setMessage("Plesse wait,while we are checking the credentials");
               loadingBar.setCanceledOnTouchOutside(false);
               loadingBar.show();

               AllowAccessToAccount(phone, password);

           }


    }

    private void AllowAccessToAccount(final String phone, final String password) {


        if(chkBoxRememberMe.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey, phone);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }
        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();

        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDbName).child(phone).exists()) {
                    User usersData = dataSnapshot.child(parentDbName).child(phone).getValue(User.class);
                    if (usersData.getPhone().equals(phone)) {
                        if (usersData.getPassword().equals(password))
                        {
                            if(parentDbName.equals("Admins"))
                            {
                                Toast.makeText(LoginActivity.this, "Logged in successfully..", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                                startActivity(intent);
                            }
                            else if (parentDbName.equals("Users"))
                            {
                                Toast.makeText(LoginActivity.this, "Logged in successfully..", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                                Prevalent.currentOnlineUser = usersData ;
                                startActivity(intent);
                            }

                        }
                        else
                            {
                                loadingBar.dismiss();
                                Toast.makeText(LoginActivity.this, "password is incorrent", Toast.LENGTH_SHORT).show();
                            }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Account with this" + phone + "number do not exists", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }
            }

                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){

                }
            });
        }

        }

