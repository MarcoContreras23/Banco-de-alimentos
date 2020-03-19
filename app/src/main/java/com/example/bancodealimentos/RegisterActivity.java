package com.example.bancodealimentos;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText name;
    EditText lastName;
    EditText email;
    EditText password;
    EditText repassword;
    Button cancel;
    Button register;
    Boolean Em;
    Boolean Pass;
    Boolean user;
    Boolean nam;
    Boolean Repass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username);
        name = (EditText) findViewById(R.id.Name);
        lastName = (EditText) findViewById(R.id.LastName);
        email = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        repassword = (EditText) findViewById(R.id.passwordT);
        cancel = (Button) findViewById(R.id.Cancel);
        register = (Button) findViewById(R.id.Register);
        Em = false;
        Pass = false;
        user = false;
        nam = false;
        Repass = false;

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String getText = email.getText().toString();
                String Expr = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@" + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

                if (getText.matches(Expr) || getText.length() == 0) {
                    email.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
                    email.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_name, 0, 0, 0);
                    if(getText.length() == 0) {
                        Em = false;
                        validateRegister();
                    } else {
                        Em = true;
                        validateRegister();
                    }
                } else {
                    email.setBackgroundTintList(getResources().getColorStateList(R.color.RED));
                    email.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_name, 0, R.drawable.close, 0);
                    Em = false;
                    validateRegister();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String getText = password.getText().toString();
                String Expr = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$";

                if (getText.matches(Expr) || getText.length() == 0) {
                    password.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
                    password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_name, 0, 0, 0);
                    if(getText.length() == 0) {
                        Pass = false;
                        validateRegister();
                    } else {
                        Pass = true;
                        validateRegister();
                    }
                } else {
                    password.setBackgroundTintList(getResources().getColorStateList(R.color.RED));
                    password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_name, 0, R.drawable.close, 0);
                    Pass = false;
                    validateRegister();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        repassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void validateRegister() {
        if(Em && Pass && Repass && nam) {
            register.setEnabled(true);
        } else {
            register.setEnabled(false);
        }
    }
}
