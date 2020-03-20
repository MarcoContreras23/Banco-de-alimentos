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

/*
 * Clase principal de la aplicación
 */
public class MainActivity extends AppCompatActivity {

    /*
    * Definción de las variables
    */
    Button btn;
    EditText Email;
    EditText Password;
    Boolean validE;
    Boolean validP;
    Button register;
    private FirebaseAuth auth;

    @Override
    /*
    * Método que se ejecuta cuando se crea la aplicación, o se ejecuta por primera vez que es el que congiene los
    * listeners de los botones y de los campos de texto
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Definición de variables
         */
        Email = (EditText)findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.button);
        register = (Button) findViewById(R.id.button2);
        Password = (EditText) findViewById((R.id.editText2));
        validE = false;
        validP = false;
        auth = FirebaseAuth.getInstance();
        //final FirebaseUser currentUser = auth.getCurrentUser();

        /*
        * Método que escucha los eventos del botón de ingreso
        */
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

        /*
        * Método que abre la vista de registro
        */
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });


        /*
        * Método que se ejecuta cuando hay algún cambio en el EditText de Email
        * y dentro del listener se valida cuando cambia y que sea un email válido con una expresión regular
         */
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

        /*
         * Método que se ejecuta cuando hay algún cambio en el EditText de password
         * y dentro del listener se valida cuando cambia y que sea un password válido con una expresión regular
         */
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

    /*
    * Función que se ejecuta cada que se inicia la aplicación para validar si esta o no en sesión
     */
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null) {
            //FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(i);
        } else {
            validateLogin();
        }
    }

    /*
    * Método que valida si los campos de Email y Password cumplen las condiciones necesarias para poder
    * iniciar sesión
     */
    public void validateLogin() {
        if(validE && validP) {
            btn.setEnabled(true);
        } else {
            btn.setEnabled(false);
        }
    }
}
