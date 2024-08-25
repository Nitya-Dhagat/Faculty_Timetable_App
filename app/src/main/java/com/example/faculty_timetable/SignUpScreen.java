package com.example.faculty_timetable;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpScreen extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText retype_password;
    private Button signUp;
    private SharedPreferences sharedprefs;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = (EditText) findViewById(R.id.textInput_signupScreen_username);
        password = (EditText) findViewById(R.id.textInput_signupScreen_password);
        retype_password = (EditText) findViewById(R.id.textInput_signupScreen_retype_password);
        signUp = (Button) findViewById(R.id.button_signupScreen_submitBtn);
        sharedprefs = getPreferences(Context.MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(username.getText().length() == 0){
                    Toast.makeText(getApplicationContext(),"Please enter username", LENGTH_SHORT).show();
                }
                if(password.getText().length() == 0){
                    Toast.makeText(getApplicationContext(),"Please enter password",LENGTH_SHORT).show();
                }
                if(retype_password.getText().length() == 0){
                    Toast.makeText(getApplicationContext(),"Please retype password",LENGTH_SHORT).show();
                }
                if(password.getText().length()!=0 && retype_password.getText().length()!=0) {
                    if (!password.getText().toString().equals(retype_password.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Both the passwords don't match", LENGTH_SHORT).show();
                    }
                    else{
                        mAuth.createUserWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            SharedPreferences.Editor editor = sharedprefs.edit();
                                            editor.putString("username",username.getText().toString());
                                            editor.putString("password",password.getText().toString());
                                            editor.commit();
                                            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(SignUpScreen.this, LoginScreen.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            // Account creation failed

                                            Log.w("createUserWithEmail:failure", task.getException());
                                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            }
        });
    }
}