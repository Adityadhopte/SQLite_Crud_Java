package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Database.Databasehandler;

public class MainActivity extends AppCompatActivity {

    TextView createAccount;
    EditText et_userEmail, et_userPassword;
    String userEmail, userPassword;
    Button login;

    Databasehandler databasehandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databasehandler = new Databasehandler(MainActivity.this);

        et_userEmail = findViewById(R.id.login_email);
        et_userPassword = findViewById(R.id.login_password);
        login = findViewById(R.id.login);
        createAccount = findViewById(R.id.create_account);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Registration.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                userEmail = et_userEmail.getText().toString();
                userPassword = et_userPassword.getText().toString();


                if (userEmail.isEmpty() || userPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                } else {
                    if (databasehandler.isLoginValid(userEmail, userPassword)) {
                        Intent intent = new Intent(MainActivity.this, ShowData.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
