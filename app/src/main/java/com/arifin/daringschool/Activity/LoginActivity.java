package com.arifin.daringschool.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.arifin.daringschool.Activity.UiParent.MainActivityParent;
import com.arifin.daringschool.Activity.UiStudent.MainActivityStudent;
import com.arifin.daringschool.Activity.UiTeacher.MainActivityTeacher;
import com.arifin.daringschool.Model.Login.preferences;
import com.arifin.daringschool.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.loginActivity)
    RelativeLayout constraintLayout;
//    @BindView(R.id.tv_signUp)
//    TextView tvSignUp;
    @BindView(R.id.txtUserName)
    EditText username;
    @BindView(R.id.txtPassword)
    EditText password;
    @BindView(R.id.show_password)
    ImageView showPassword;
    @BindView(R.id.active)
    Switch active;

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
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("login").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String input1 = username.getText().toString();
                        String input2 = password.getText().toString();

                        if (dataSnapshot.child(input1).exists()) {
                            if (dataSnapshot.child(input1).child("password").getValue(String.class).equals(input2)) {
                                if (active.isChecked()) {
                                    if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("admin")) {
                                        preferences.setDataLogin(LoginActivity.this, true);
                                        preferences.setDataAs(LoginActivity.this, "admin");
                                        startActivity(new Intent(LoginActivity.this, MainActivityTeacher.class));
                                    } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("user")){
                                        preferences.setDataLogin(LoginActivity.this, true);
                                        preferences.setDataAs(LoginActivity.this, "user");
                                        startActivity(new Intent(LoginActivity.this, MainActivityStudent.class));
                                    } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("parent")){
                                        preferences.setDataLogin(LoginActivity.this, true);
                                        preferences.setDataAs(LoginActivity.this, "parent");
                                        startActivity(new Intent(LoginActivity.this, MainActivityParent.class));
                                    }
                                } else {
                                    if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("admin")) {
                                        preferences.setDataLogin(LoginActivity.this, false);
                                        startActivity(new Intent(LoginActivity.this, MainActivityTeacher.class));

                                    } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("user")){
                                        preferences.setDataLogin(LoginActivity.this, false);
                                        startActivity(new Intent(LoginActivity.this, MainActivityStudent.class));

                                    } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("parent")){
                                        preferences.setDataLogin(LoginActivity.this, false);
                                        startActivity(new Intent(LoginActivity.this, MainActivityParent.class));
                                    }
                                }

                            } else {
                                Toast.makeText(LoginActivity.this, "Kata sandi salah", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Data belum terdaftar", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

//        tvSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });

        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show_stat == 0) {
                    show_stat = 1;
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPassword.setImageResource(R.drawable.ic_visibility_off);
                    password.setSelection(password.getText().length());
                } else {
                    show_stat = 0;
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showPassword.setImageResource(R.drawable.ic_show_password);
                    password.setSelection(password.getText().length());
                }
            }
        });
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