package com.arifin.daringschool.Activity.UiParent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.arifin.daringschool.R;

public class MainActivityParent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parent);
    }
}