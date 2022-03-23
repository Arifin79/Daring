package com.arifin.daringschool.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arifin.daringschool.Model.Login.controller.ApiClient;
import com.arifin.daringschool.Model.Login.model.Login;
import com.arifin.daringschool.Model.Login.controller.LoginInterface;
import com.arifin.daringschool.Model.Login.model.ResponseLogin;
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
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.loginActivity)
    RelativeLayout constraintLayout;
    @BindView(R.id.tv_signUp)
    TextView tvSignUp;
    @BindView(R.id.txtUserName)
    EditText emailEdit;
    @BindView(R.id.txtPassword)
    EditText passwordEdit;
    @BindView(R.id.show_password)
    ImageView showPassword;

    String rbUsername, rbPassword;
    private FirebaseAuth mAuth;
    int show_stat = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show_stat == 0) {
                    show_stat = 1;
                    passwordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPassword.setImageResource(R.drawable.ic_visibility_off);
                    passwordEdit.setSelection(passwordEdit.getText().length());
                } else {
                    show_stat = 0;
                    passwordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showPassword.setImageResource(R.drawable.ic_show_password);
                    passwordEdit.setSelection(passwordEdit.getText().length());
                }
            }
        });
    }

    private void loginUser(){
        rbUsername = emailEdit.getText().toString();
        rbPassword = passwordEdit.getText().toString();
        mAuth.signInWithEmailAndPassword(rbUsername, rbPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Email atau Password anda salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}