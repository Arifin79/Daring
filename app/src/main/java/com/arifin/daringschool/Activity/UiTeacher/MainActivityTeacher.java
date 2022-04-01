package com.arifin.daringschool.Activity.UiTeacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.arifin.daringschool.Activity.UiStudent.Fragment.HomeFragment;
import com.arifin.daringschool.Activity.UiStudent.Fragment.ProfileFragment;
import com.arifin.daringschool.Activity.UiStudent.Fragment.TodoFragment;
import com.arifin.daringschool.Activity.UiTeacher.Fragment.HomeFragmentTeacher;
import com.arifin.daringschool.Activity.UiTeacher.Fragment.ProfileFragmentTeacher;
import com.arifin.daringschool.Activity.UiTeacher.Fragment.TodoFragmentTeacher;
import com.arifin.daringschool.R;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivityTeacher extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

        bottomNavigation = findViewById(R.id.nav_view);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_note));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_account));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()){
                    case 1:
                        fragment = new TodoFragmentTeacher();
                        break;
                    case 2:
                        fragment = new HomeFragmentTeacher();
                        break;
                    case 3:
                        fragment = new ProfileFragmentTeacher();
                        break;
                }
                loadFragment(fragment);
            }
        });

//        bottomNavigation.setCount(1, "10");
//
        bottomNavigation.show(2, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
//                Toast.makeText(getApplicationContext()
//                , "You Clicked " + item.getId()
//                , Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
//                Toast.makeText(getApplicationContext()
//                        , "You Reselected " + item.getId()
//                        , Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit();
    }
}