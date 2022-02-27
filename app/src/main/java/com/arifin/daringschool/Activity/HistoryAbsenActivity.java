package com.arifin.daringschool.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.arifin.daringschool.Adapter.HistoryAbsenAdapter;
import com.arifin.daringschool.Model.Absen;
import com.arifin.daringschool.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAbsenActivity extends AppCompatActivity {

    @BindView(R.id.action_bar_history)
    Toolbar actionBar;

    @BindView(R.id.rv_history_absen)
    RecyclerView rvHistoryAbsen;

    HistoryAbsenAdapter historyAdapter;
    LinearLayoutManager layoutManagerHistory;
    ArrayList<Absen> absenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_absen);
        ButterKnife.bind(this);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_right1);
        getSupportActionBar().setTitle("History Absen");

        listHistory();

        historyAdapter = new HistoryAbsenAdapter(absenList, HistoryAbsenActivity.this);
        layoutManagerHistory = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvHistoryAbsen.setLayoutManager(layoutManagerHistory);
        rvHistoryAbsen.setAdapter(historyAdapter);


    }

    private void listHistory() {
        absenList = new ArrayList<>();
        for (int i = 0; i<3 ; i++){
            Absen absen = new Absen();
            absen.setNamaSiswa("Irfan Wijaya");
            absen.setKelas("12");
            absen.setAbsenPadaJam("07:12");
            absen.setTanggal("27 Mei 2022");
            absen.setKeterangan("Masuk");
            absenList.add(absen);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }
}