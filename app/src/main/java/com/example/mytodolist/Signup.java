package com.example.mytodolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
        private EditText email, pass;
        private Button sign_up;
        private TextView log_in;
        private String username, password;
        private FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signpage);
            mAuth = FirebaseAuth.getInstance();
            email = findViewById(R.id.email1);
            pass = findViewById(R.id.password1);
            sign_up = findViewById(R.id.button1);
            log_in = findViewById(R.id.text);


            sign_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    username = email.getText().toString();
                    password = pass.getText().toString();

                    createAccount(username, password);
                }
            });

            log_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent re = new Intent(Signup.this, LoginActivity.class);
                    startActivity(re);
                }
            });
        }

        private void createAccount(String username, String password) {
            mAuth.createUserWithEmailAndPassword(this.username, this.password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Sign Up Successful.",
                                        Toast.LENGTH_SHORT).show();
                                Intent r = new Intent(getApplicationContext(), LoginActivity.class);
                                r.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(r);


                            } else {

                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });
        }
    }


