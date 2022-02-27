package com.arifin.daringschool.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arifin.daringschool.Adapter.SliderAdapter;
import com.arifin.daringschool.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroSliderActivity extends AppCompatActivity {


    @BindView(R.id.btnNext)
    Button mNextBtn;
    @BindView(R.id.btnBack)
    Button mBackBtn;
    @BindView(R.id.slideViewPager)
    ViewPager mSliderViewPager;
    @BindView(R.id.dotsLayout)
    LinearLayout mDotLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);
        ButterKnife.bind(this);

        sliderAdapter = new SliderAdapter(this);

        mSliderViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        mSliderViewPager.addOnPageChangeListener(viewListener);

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSliderViewPager.getCurrentItem() + 1 < sliderAdapter.getCount()){
                    mSliderViewPager.setCurrentItem(mSliderViewPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
//                mSliderViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSliderViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

    }

    public void addDotsIndicator(int position) {
        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for(int i = 0; i < mDots.length; i++){

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorBackgroundTransparent));

            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length> 0){

            mDots[position].setTextColor(getResources().getColor(R.color.grey));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

            mCurrentPage = position;

            if (position == 0) {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mBackBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("");

            } else if (position == mDots.length -1) {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Finish");
                mBackBtn.setText("Back");

            } else {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("Back");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}