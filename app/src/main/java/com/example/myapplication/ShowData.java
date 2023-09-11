package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Database.Databasehandler;
import com.example.myapplication.model.User;

import java.util.List;

public class ShowData extends AppCompatActivity {

    TextView tv_userName, tv_userEmail, tv_userDob, tv_gender;
    Button Update, Delete;
    Databasehandler databasehandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        tv_userName = findViewById(R.id.show_name);
        tv_userEmail = findViewById(R.id.show_email);
        tv_userDob = findViewById(R.id.show_dob);
        tv_gender = findViewById(R.id.show_male);
        Update = findViewById(R.id.update);
        Delete = findViewById(R.id.delete);

        databasehandler = new Databasehandler(this);

        List<User> userList = databasehandler.getAllUsers();

        if (!userList.isEmpty()) {
            User user = userList.get(0);
            tv_userName.setText("Name: " + user.getUserName());
            tv_userEmail.setText("Email: " + user.getUserEmail());
            tv_userDob.setText("DOB: " + user.getUserDob());

            String genderString = "";
            String userGender = user.getUserRadioButton();
            if (userGender.equals("male")) {
                genderString = "Male";
            } else if (userGender.equals("female")) {
                genderString = "Female";
            } else {
                genderString = "Other";
            }
            tv_gender.setText("GENDER: " + genderString);
        } else {
            tv_userName.setText("No user data available");

        }

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UpdateData.class);
                startActivity(i);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userIdToDelete = 1;
                databasehandler.deleteUser(userIdToDelete);
                Toast.makeText(ShowData.this, "User deleted successfully", Toast.LENGTH_SHORT).show();
                tv_userName.setText("");
                tv_userEmail.setText("");
                tv_userDob.setText("");
                tv_gender.setText("No user data available");
            }
        });

    }
}
