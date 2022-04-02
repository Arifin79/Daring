package com.arifin.daringschool.Activity.UiParent.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arifin.daringschool.Activity.LoginActivity;
import com.arifin.daringschool.Activity.UiStudent.Activity.ChangePasswordActivity;
import com.arifin.daringschool.Activity.UiStudent.Activity.EditProfileActivity;
import com.arifin.daringschool.Model.Login.preferences;
import com.arifin.daringschool.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfileFragmentParent extends Fragment {

   @BindView(R.id.btn_logout)
    LinearLayout btnLogout;
   @BindView(R.id.lnr_changePw)
   LinearLayout btnchangePw;
   @BindView(R.id.lnr_editProfile)
   LinearLayout btneditProfile;

//    public ProfileFragmentParent() {
//        // Required empty public constructor
//    }

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

        btnchangePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ChangePasswordActivity.class);
                getActivity().startActivity(intent);
            }
        });

        btneditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), EditProfileActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return view ;
    }
}