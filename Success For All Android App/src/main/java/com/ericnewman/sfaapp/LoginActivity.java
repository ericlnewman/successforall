package com.ericnewman.sfaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ericnewman.sfaapp.database.SQLiteHelper;

public class LoginActivity extends AppCompatActivity {

    Button logInButton,signup, forget;
    EditText email, password;
    String emailHolder, passwordHolder;
    Boolean aBoolean;
    SQLiteDatabase sqLiteDatabaseObj;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String not_found = "NOT_FOUND" ;
    public static final String USERMAIL = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logInButton = findViewById(R.id.loginBtn);
        signup = findViewById(R.id.goToSignup);
        forget = findViewById(R.id.goToForgotPw);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        sqLiteHelper = new SQLiteHelper(this);
        //Adding click listener to log in button.
        logInButton.setOnClickListener(view -> {
            // Calling EditText is empty or no method.
            CheckEditTextStatus();
            // Calling login method.
            LoginFunction();
        });
        // Adding click listener to register button.
        signup.setOnClickListener(v -> {

            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
        // Adding click listener to forgot passsword button.
        forget.setOnClickListener(v -> {

            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });
    }


    // Login function starts from here.
    @SuppressLint("Range")
    public void LoginFunction(){
        if(aBoolean) {
            // Opening SQLite database write permission.
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
            // Adding search email query to cursor.
            cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAM, null, " " +
                            SQLiteHelper.Table_Column_2_Email + "=?", new String[]{emailHolder},
                    null, null, null);
            while (cursor.moveToNext()) {
                if (cursor.isFirst()) {
                    cursor.moveToFirst();
                    // Storing Password associated with entered email.
                    not_found = cursor.getString(
                            cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Password));
                    // Closing cursor.
                    cursor.close();
                }
            }
            // Calling method to check final result ..
            CheckFinalResult();
        }
        else {
            //If any of login EditText empty then this block will be executed.
            Toast.makeText(LoginActivity.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();
        }
    }
    // Checking EditText is empty or not.
    public void CheckEditTextStatus(){
        // Getting value from All EditText and storing into String Variables.
        emailHolder = email.getText().toString();
        passwordHolder = password.getText().toString();
        // Checking EditText is empty or no using TextUtils.
        aBoolean = !TextUtils.isEmpty(emailHolder) && !TextUtils.isEmpty(passwordHolder);
    }
    // Checking entered password from SQLite database email associated password.
    public void CheckFinalResult(){
        if(not_found.equalsIgnoreCase(passwordHolder))
        {
            Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
            // Going to Dashboard activity after login success message.
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(USERMAIL, emailHolder);
            startActivity(intent);
        }
        else {
            Toast.makeText(LoginActivity.this,"Email or Password is Wrong, Please Try Again.",Toast.LENGTH_LONG).show();
        }
        not_found = "NOT_FOUND" ;
    }
}
