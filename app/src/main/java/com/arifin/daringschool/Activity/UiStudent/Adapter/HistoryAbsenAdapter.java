package com.arifin.daringschool.Activity.UiStudent.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arifin.daringschool.Activity.UiStudent.Model.StudentAbsen;
import com.arifin.daringschool.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HistoryAbsenAdapter extends RecyclerView.Adapter<HistoryAbsenAdapter.HistoryHolder> {

    private ArrayList<StudentAbsen> list;
    private Context mcontext;

    public HistoryAbsenAdapter(Activity mcontext, ArrayList<StudentAbsen> list) {
        this.mcontext=mcontext;
        this.list = list;
    }

        @NonNull
    @NotNull
    @Override
    public HistoryAbsenAdapter.HistoryHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_absen, parent, false);
        return new  HistoryAbsenAdapter.HistoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HistoryAbsenAdapter.HistoryHolder holder, int position) {

        holder.absenPadaJam.setText(list.get(position).getAbsenPadaJam());
        holder.tanggal.setText(list.get(position).getTanggal());
        holder.keterangan.setText(list.get(position).getKeterangan());
        holder.kelas.setText(list.get(position).getKelas());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {
        TextView kelas;
        TextView absenPadaJam;
        TextView tanggal;
        TextView keterangan;

        public HistoryHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            kelas = itemView.findViewById(R.id.tv_kelas);
            absenPadaJam = itemView.findViewById(R.id.tv_absen_siswa);
            tanggal = itemView.findViewById(R.id.tv_tgl_siswa);
            keterangan = itemView.findViewById(R.id.tv_keterangan);
        }
    }
}
