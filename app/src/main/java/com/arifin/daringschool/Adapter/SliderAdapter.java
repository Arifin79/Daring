package com.arifin.daringschool.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.arifin.daringschool.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int[] slide_image = {
            R.drawable.slider1,
            R.drawable.slider2,
            R.drawable.slider3
    };

    public String[] slide_headings = {
            "Belajar Daring",
            "Siap Membantu",
            "Menuju Impian"
    };

    public String[] slide_descs = {
            "Daring School Menyediakan Pembelajaran Dari Jarak Jauh Yang Sangat Dibutuhkan Di Masa Pandemi Seperti Sekarang",
            "Daring School Siap Membantu Para Orang Tua/Guru",
            "daring school akan membantu  untuk mencapai impian kalian secara bertahap, kami akan selalu membantu setiap langkah yang kalian lakukan"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view == object;
    }

    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_intro_slider, container, false);

        ImageView slideImageView = view.findViewById(R.id.imageSlider);
        TextView slideHeading = view.findViewById(R.id.headline);
        TextView slideDesc = view.findViewById(R.id.descSlider);

        slideImageView.setImageResource(slide_image[position]);
        slideHeading.setText(slide_headings[position]);
        slideDesc.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
