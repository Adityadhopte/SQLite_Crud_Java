package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Database.Databasehandler;
import com.example.myapplication.model.User;

public class Registration extends AppCompatActivity {

    EditText et_userName, et_userEmail, et_userPassword, et_userConfirmPassword, et_userDob;
    RadioGroup radioButtonGroup;
    Button signUpButton;
    Databasehandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        et_userName = findViewById(R.id.name);
        et_userEmail = findViewById(R.id.email);
        et_userPassword = findViewById(R.id.password);
        et_userConfirmPassword = findViewById(R.id.confirm_password);
        et_userDob = findViewById(R.id.dob);
        radioButtonGroup = findViewById(R.id.radiobutton);
        signUpButton = findViewById(R.id.signup);

        databaseHandler = new Databasehandler(this);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = et_userName.getText().toString();
                String userEmail = et_userEmail.getText().toString();
                String userPassword = et_userPassword.getText().toString();
                String userConfirmPassword = et_userConfirmPassword.getText().toString();
                String userDob = et_userDob.getText().toString();

                int selectedRadioButtonId = radioButtonGroup.getCheckedRadioButtonId();


                if (userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty() || userConfirmPassword.isEmpty() || userDob.isEmpty()) {
                    Toast.makeText(Registration.this, "Please enter all data", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!userPassword.equals(userConfirmPassword)) {
                    Toast.makeText(Registration.this, "Passwords do not match", Toast.LENGTH_SHORT).show();

                }

                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String selectedRadioButtonText = selectedRadioButton.getText().toString();

                User user = new User();
                user.setUserName(userName);
                user.setUserEmail(userEmail);
                user.setUserPassword(userPassword);
                user.setUserDob(userDob);

                user.setUserRadioButton(selectedRadioButtonText);


                databaseHandler.insert(user);

                Intent i = new Intent(Registration.this, MainActivity.class);
                startActivity(i);
                Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                et_userName.setText("");
                et_userEmail.setText("");
                et_userPassword.setText("");
                et_userConfirmPassword.setText("");
                et_userDob.setText("");
                radioButtonGroup.clearCheck();
            }
        });

    }
}

