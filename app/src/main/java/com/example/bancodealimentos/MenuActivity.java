package com.example.bancodealimentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    Button btnOut;
    /*TextView user;
    TextView roldata;*/

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnOut = (Button) findViewById(R.id.logout);

        //  getData();

        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    /*public void getData(){
        Bundle extras  = getIntent().getExtras();
        String username = extras.getString("username");
        String rol = extras.getString("rol");

        user = (TextView) findViewById(R.id.user_name_value);
        user.setText(username);

        roldata = (TextView) findViewById(R.id.rol_value);
        roldata.setText(rol);
    }*/
}
