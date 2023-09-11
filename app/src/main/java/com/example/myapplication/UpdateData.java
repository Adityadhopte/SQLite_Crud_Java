package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database.Databasehandler;
import com.example.myapplication.model.User;

public class UpdateData extends AppCompatActivity {

    EditText etName, etEmail, etDob, et_radiobutton;
    Button btnUpdate;
    Databasehandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        dbHandler = new Databasehandler(this);

        etName = findViewById(R.id.update_name);
        etEmail = findViewById(R.id.update_email);
        etDob = findViewById(R.id.update_dob);
        et_radiobutton = findViewById(R.id.update_gender);
        btnUpdate = findViewById(R.id.Upadte_data);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userIdToUpdate = 1;

                String updatedName = etName.getText().toString();
                String updatedEmail = etEmail.getText().toString();
                String updatedDob = etDob.getText().toString();
                String selectedRadioButtonId = et_radiobutton.getText().toString();

                User updatedUser = new User();
                updatedUser.setId(userIdToUpdate);
                updatedUser.setUserName(updatedName);
                updatedUser.setUserEmail(updatedEmail);
                updatedUser.setUserDob(updatedDob);
                updatedUser.setUserRadioButton(selectedRadioButtonId);

                dbHandler.updateUser(updatedUser);

                Intent i = new Intent(UpdateData.this, ShowData.class);
                startActivity(i);
                Toast.makeText(UpdateData.this, "User data updated successfully", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
