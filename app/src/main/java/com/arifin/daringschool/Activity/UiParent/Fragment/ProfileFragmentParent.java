package com.arifin.daringschool.Activity.UiParent.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arifin.daringschool.Activity.LoginActivity;
import com.arifin.daringschool.Activity.UiParent.Activity.EditProfileParent;
import com.arifin.daringschool.Activity.UiStudent.Activity.ChangePasswordActivity;
import com.arifin.daringschool.Activity.UiStudent.Activity.EditProfileActivity;
import com.arifin.daringschool.Model.Login.preferences;
import com.arifin.daringschool.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragmentParent extends Fragment {

   @BindView(R.id.btn_logout)
    LinearLayout btnLogout;
   @BindView(R.id.lnr_changePw)
   LinearLayout btnChangePw;
   @BindView(R.id.lnr_editProfile)
   LinearLayout btnEditProfile;
   @BindView(R.id.img_profile_account_parent)
    CircleImageView imgAccount;
   @BindView(R.id.tv_profile_account_parent)
    TextView nameAccount;

   DatabaseReference studentAccount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_parent, container, false);
        ButterKnife.bind(this, view );

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                preferences.clearData(getActivity());
                getActivity().startActivity(intent);
            }
        });

        btnChangePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ChangePasswordActivity.class);
                getActivity().startActivity(intent);
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), EditProfileParent.class);
                getActivity().startActivity(intent);
            }
        });

        studentAccount = FirebaseDatabase.getInstance().getReference("login/Ahmad Satria Wijaya").child("Profile");

        studentAccount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    Picasso.get().load(ds.child("imgProfile").getValue().toString()).into(imgAccount);
                    nameAccount.setText(ds.child("nameProfile").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        return view ;
    }
}