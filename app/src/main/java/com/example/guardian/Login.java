package com.example.guardian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://guardian-498c6-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText phone = findViewById(R.id.phone);
        final EditText password = findViewById(R.id.password);
        final Button loginBtn = findViewById(R.id.loginBtn);
        final TextView registerNow = findViewById(R.id.registerNow);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String phoneTxt = phone.getText().toString();
                final String passwordTxt = password.getText().toString();

                if(phoneTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(Login.this, "Please enter Mobile number or password", Toast.LENGTH_SHORT).show();
                }
                else{
                 databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {

                         //check if mobile number exists in firebase database
                         if(snapshot.hasChild(phoneTxt)){
                             final String getPassword = snapshot.child(phoneTxt).child("password").getValue(String.class);

                             if(getPassword.equals(passwordTxt)){
                                 Toast.makeText(Login.this, "Successfuly Logged in!", Toast.LENGTH_SHORT).show();
                                 startActivity(new Intent(Login.this,HomPage.class));
                                 finish();
                             }
                             else{
                                 Toast.makeText(Login.this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
                             }

                         }
                         else{
                             Toast.makeText(Login.this, "Incorrect Mobile Number!", Toast.LENGTH_SHORT).show();
                         }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {

                     }
                 });
                }
            }
        });
        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,register.class));

            }
        });
    }
}