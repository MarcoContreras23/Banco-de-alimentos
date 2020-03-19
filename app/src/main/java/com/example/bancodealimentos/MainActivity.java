package com.example.bancodealimentos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText Email;
    EditText Password;
    Boolean validE;
    Boolean validP;
    Button register;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText)findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.button);
        register = (Button) findViewById(R.id.button2);
        Password = (EditText) findViewById((R.id.editText2));
        validE = false;
        validP = false;
        auth = FirebaseAuth.getInstance();
        //final FirebaseUser currentUser = auth.getCurrentUser();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(MainActivity.this, MenuActivity.class);
                                    //i.putExtra("username", currentUser.getDisplayName());
                                    //i.putExtra("rol", currentUser.getEmail());
                                    startActivity(i);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String getText = Email.getText().toString();
                String Expr = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@" + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

                if (getText.matches(Expr) || getText.length() == 0) {
                    Email.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
                    Email.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_name, 0, 0, 0);
                    if(getText.length() == 0) {
                        validE = false;
                        validateLogin();
                    } else {
                        validE = true;
                        validateLogin();
                    }
                } else {
                    Email.setBackgroundTintList(getResources().getColorStateList(R.color.RED));
                    Email.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_name, 0, R.drawable.close, 0);
                    validE = false;
                    validateLogin();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String getText = Password.getText().toString();
                String Expr = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$";

                if (getText.matches(Expr) || getText.length() == 0) {
                    Password.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
                    Password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_name, 0, 0, 0);
                    if(getText.length() == 0) {
                        validP = false;
                        validateLogin();
                    } else {
                        validP = true;
                        validateLogin();
                    }
                } else {
                    Password.setBackgroundTintList(getResources().getColorStateList(R.color.RED));
                    Password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_name, 0, R.drawable.close, 0);
                    validP = false;
                    validateLogin();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null) {
            Intent i = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(i);
        } else {
            validateLogin();
        }
    }

    public void validateLogin() {
        if(validE && validP) {
            btn.setEnabled(true);
        } else {
            btn.setEnabled(false);
        }
    }
}
