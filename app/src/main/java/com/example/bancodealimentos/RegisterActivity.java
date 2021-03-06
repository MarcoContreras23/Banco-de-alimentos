package com.example.bancodealimentos;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
 * Clase enlazada a la vista de registro
 */
public class RegisterActivity extends AppCompatActivity {

    /*
     * Definición de variables
     */
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
    Spinner dropdown;
    private FirebaseAuth auth;


    /*
     * Método que se ejecuta al iniciar la aplicación
     */
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
        dropdown = findViewById(R.id.role);
        String[] items = new String[]{"Administrador", "Usuario"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        auth = FirebaseAuth.getInstance();

        /*
         * Métdodo que retorna a la vista de home
         */
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        /*
         * Método que valida si el nombre de usuario es valido
         */
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String getText = username.getText().toString();
                String Expr = "^[a-z0-9_-]{3,15}$";

                if(getText.matches(Expr) || getText.length() == 0) {
                    username.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
                    username.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_name, 0, 0, 0);
                    if(getText.length() == 0) {
                        user = false;
                        validateRegister();
                    } else {
                        user = true;
                        validateRegister();
                    }
                } else {
                    username.setBackgroundTintList(getResources().getColorStateList(R.color.RED));
                    username.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_name, 0, R.drawable.close, 0);
                    user = false;
                    validateRegister();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*
         * Método que valida si el nombre ingresado es valido
         */
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String getText = name.getText().toString();

                if(getText.length() > 0 && getText.length() <= 8 || getText.length() == 0) {
                    name.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
                    name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_name, 0, 0, 0);
                    nam = true;
                    validateRegister();
                } else {
                    name.setBackgroundTintList(getResources().getColorStateList(R.color.RED));
                    name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_name, 0, R.drawable.close, 0);
                    nam = false;
                    validateRegister();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /*
         * Método que valida si el email de usuario es valido
         */
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

        /*
         * Método que valida si el password de usuario es valido
         */
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

        /*
         * Método que valida si el password y la confirmación son iguales
         */
        repassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String getText = repassword.getText().toString();

                if(getText.equals(password.getText().toString())) {
                    repassword.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
                    repassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_name, 0, 0, 0);
                    Repass = true;
                    validateRegister();
                } else {
                    repassword.setBackgroundTintList(getResources().getColorStateList(R.color.RED));
                    repassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_name, 0, R.drawable.close, 0);
                    Repass = false;
                    validateRegister();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /*
     * Método que valida si el usuario está en sesión
     */
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        System.out.println(currentUser);
        if(currentUser != null) {
            Intent i = new Intent(RegisterActivity.this, MenuActivity.class);
            startActivity(i);
        } else {
            validateRegister();
        }
    }

    /*
     * Método que valida si  todos los campos cumplen las condiciones para habilitar el botón
     */
    public void validateRegister() {
        if(Em && Pass && Repass && nam) {
            register.setEnabled(true);
        } else {
            register.setEnabled(false);
        }
    }
}
