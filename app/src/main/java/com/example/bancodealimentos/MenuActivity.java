package com.example.bancodealimentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

/*
 * Clase perteneciente a la vista de menú
 */
public class MenuActivity extends AppCompatActivity {

    /*
     * Definción de variables
     */
    Button btnOut;
    TextView username;
    Button send;
    TextView info;
    TextView title;
    private FirebaseAuth auth;

    /*
     * Método que ejecuta la primera instancia del código cuando se inicializa
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnOut = (Button) findViewById(R.id.logout);
        username = (TextView) findViewById(R.id.user_name_value);

        /*
         * Se valida el boton de cerrar sesión
         */
        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MenuActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });


        send = (Button) findViewById(R.id.send);
        username = (TextView) findViewById(R.id.user_name_value);

        /*send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                        //.setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle(title)
                        .setContentText(info)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            }
        });*/
    }
}
