package com.arifin.daringschool.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.arifin.daringschool.Activity.UiParent.MainActivityParent;
import com.arifin.daringschool.Activity.UiStudent.MainActivityStudent;
import com.arifin.daringschool.Activity.UiTeacher.MainActivityTeacher;
import com.arifin.daringschool.Model.Login.preferences;
import com.arifin.daringschool.R;

public class SplashScreenActivity extends AppCompatActivity {
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, IntroSliderActivity.class);
                startActivity(i);
                finish();
            }
        }, 2000);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (preferences.getDataLogin(this)) {
            if (preferences.getDataAs(this).equals("admin")) {
                startActivity(new Intent(this, MainActivityTeacher.class));
                finish();
            } else if (preferences.getDataAs(this).equals("user")){
                startActivity(new Intent(this, MainActivityStudent.class));
                finish();
            } else {
                startActivity(new Intent(this, MainActivityParent.class));
                finish();
            }
        }
    }
}