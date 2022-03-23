package com.arifin.daringschool.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.arifin.daringschool.Model.Login.controller.LoginInterface;
import com.arifin.daringschool.R;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.action_bar_regist)
    Toolbar actionBar;
    @BindView(R.id.activityRegister)
    RelativeLayout constraintLayout;
    @BindView(R.id.txtEmailRegist)
    EditText emailEdit;
    @BindView(R.id.txtPasswordRegist)
    EditText passwordEdit;
    @BindView(R.id.txtPaswordConfirm)
    EditText passwordConfirmEdit;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.show_password1)
    ImageView showPassword1;
    @BindView(R.id.show_password_confirm_regist)
    ImageView showPassword2;

    String rbUsername, rbPassword1, rbPassword2;
    private FirebaseAuth mAuth;
    int show_stat1 = 0;
    int show_stat2 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_right1);
        getSupportActionBar().setTitle("Daftar");
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        showPassword1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show_stat1 == 0) {
                    show_stat1 = 1;
                    passwordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPassword2.setImageResource(R.drawable.ic_visibility_off);
                    passwordEdit.setSelection(passwordEdit.getText().length());
                } else {
                    show_stat1 = 0;
                    passwordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showPassword2.setImageResource(R.drawable.ic_show_password);
                    passwordEdit.setSelection(passwordEdit.getText().length());
                }
            }
        });

        showPassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show_stat2 == 0) {
                    show_stat2 = 1;
                    passwordConfirmEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPassword1.setImageResource(R.drawable.ic_visibility_off);
                    passwordConfirmEdit.setSelection(passwordConfirmEdit.getText().length());
                } else {
                    show_stat2 = 0;
                    passwordConfirmEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showPassword1.setImageResource(R.drawable.ic_show_password);
                    passwordConfirmEdit.setSelection(passwordConfirmEdit.getText().length());
                }
            }
        });


    }

    private void registerUser() {
        rbUsername = emailEdit.getText().toString();
        rbPassword1 = passwordEdit.getText().toString();
        rbPassword2 = passwordConfirmEdit.getText().toString();
        mAuth.createUserWithEmailAndPassword(rbUsername, rbPassword2).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Registrasi Sukses", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Email atau Password anda salah", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
        }
        return true;
    }
}