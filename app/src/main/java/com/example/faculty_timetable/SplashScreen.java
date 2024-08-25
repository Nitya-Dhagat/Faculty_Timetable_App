package com.example.faculty_timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashScreen extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    FirebaseAuth mAuth;
    SharedPreferences shared_prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        lottieAnimationView = findViewById(R.id.lottie);

        lottieAnimationView.animate().translationY(1500).setDuration(1000).setStartDelay(5000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //startActivity(new Intent(Splash_Screen.this,MainActivity.class));
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser logged_in_user = mAuth.getCurrentUser();
                shared_prefs = getPreferences(Context.MODE_PRIVATE);
                String email = shared_prefs.getString("username",null);
                String password = shared_prefs.getString("password",null);
                if(logged_in_user!=null) {
//                    if ((logged_in_user.getEmail()).equals(email))
                    Log.w("user",logged_in_user.getEmail());
                    startActivity(new Intent(SplashScreen.this, HomeScreen.class));
                }
                else
                    startActivity(new Intent(SplashScreen.this,LoginScreen.class));

            }
        },6000);

    }
}