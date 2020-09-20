package com.example.android.katrashbazar;

import android.app.ProgressDialog;
        import android.content.Intent;
        import android.provider.DocumentsContract;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.example.android.katrashbazar.Prevalent.Prevalent;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button CreateAccounrButton ;
    private EditText InputName, InputPhoneNumber, InputPassword;
    private ProgressDialog loadingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        CreateAccounrButton = (Button) findViewById(R.id.register_btn);

        InputName = (EditText) findViewById(R.id.register_username_input);
        InputPhoneNumber = (EditText) findViewById(R.id.register_phone_number);
        InputPassword = (EditText) findViewById(R.id.register_password_input);
        loadingBar = new ProgressDialog( this);

        CreateAccounrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });


    }
    private void CreateAccount(){
        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(name)){
            Toast.makeText( this, "PLEASE WRITE YOUR NAME..", Toast.LENGTH_LONG);
        }
        else    if (TextUtils.isEmpty(phone)){
            Toast.makeText( this, "PLEASE WRITE YOUR number..", Toast.LENGTH_LONG);
        }
        else   if (TextUtils.isEmpty(password)){
            Toast.makeText( this, "PLEASE WRITE YOUR password..", Toast.LENGTH_LONG);
        }
        else{
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Plesse wait,while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatephoneNumber(name, phone, password);
        }



    }

    private void ValidatephoneNumber( final String name, final String phone, final String password) {

        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();

        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(phone).exists())) {

                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("password", password);
                    userdataMap.put("name", name);

                    Rootref.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Congratulation your account has beeen ceated.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

                                        startActivity(intent);
                                    } else {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this, "network error try later", Toast.LENGTH_SHORT);

                                    }
                                }
                            });

                } else {
                    Toast.makeText(RegisterActivity.this, "this" + phone + "already exists", Toast.LENGTH_SHORT);
                    loadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Please try again using different phone number", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }





            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

            ;

        });
    }}
