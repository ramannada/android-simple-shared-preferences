package com.blogspot.ramannada.samplesharedpreferences;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private String email = "ramannada@gmail.com";
    private final String password = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedData.init(LoginActivity.this);

        checkLoginStatus();

        final TextView etEmail = findViewById(R.id.et_email);
        final TextView etPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
                    etEmail.setError("Check your email address");
                    etEmail.requestFocus();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etEmail.getText().toString().isEmpty()) {
                    etEmail.setError("email can't be empty");
                    etEmail.requestFocus();
                }

                if (etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("password can't be empty");
                    etPassword.requestFocus();
                }

                if (etEmail.getText().toString().equals(email) &&
                        etPassword.getText().toString().equals(password)) {

                    SharedData.getSharedData().saveLoginStatus(true);

                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Your email or password is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    public void checkLoginStatus() {
        if(SharedData.getSharedData().getLoginStatus()) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }
}
