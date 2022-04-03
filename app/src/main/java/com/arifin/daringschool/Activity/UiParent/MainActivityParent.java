package com.arifin.daringschool.Activity.UiParent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.arifin.daringschool.Activity.UiParent.Fragment.HomeFragmentParent;
import com.arifin.daringschool.Activity.UiParent.Fragment.ProfileFragmentParent;
import com.arifin.daringschool.R;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivityParent extends AppCompatActivity {
    
    MeowBottomNavigation bottomNavigation;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parent);
        
        bottomNavigation = findViewById(R.id.nav_view);
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_account));
        
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                
                switch (item.getId()){
                    case 1:
                        fragment = new HomeFragmentParent();
                        break;
                    case 2:
                        fragment =  new ProfileFragmentParent();
                        break;
                }
                loadFragment(fragment);
            }
        });
        bottomNavigation.show(2,true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
            }
        });

    }
    private void loadFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment,fragment)
                .commit();
    }
}