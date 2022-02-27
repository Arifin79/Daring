package com.arifin.daringschool.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arifin.daringschool.Activity.ChangePasswordActivity;
import com.arifin.daringschool.Activity.LoginActivity;
import com.arifin.daringschool.Activity.MainActivity;
import com.arifin.daringschool.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {

    @BindView(R.id.btn_logout)
    LinearLayout btnLogout;
    @BindView(R.id.lnr_changePw)
    LinearLayout btnChangePw;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
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

        return view;
    }
}