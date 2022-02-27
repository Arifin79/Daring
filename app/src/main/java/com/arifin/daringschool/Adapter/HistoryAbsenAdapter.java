package com.arifin.daringschool.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arifin.daringschool.Model.Absen;
import com.arifin.daringschool.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HistoryAbsenAdapter extends RecyclerView.Adapter<HistoryAbsenAdapter.HistoryHolder> {

    private List<Absen> list;
    private Context context;

    public HistoryAbsenAdapter(List<Absen> list, Context context) {
        this.context=context;
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
        Absen absen = new Absen();
        absen = list.get(position);

        holder.namaSiswa.setText(absen.getNamaSiswa());
        holder.kelas.setText(absen.getKelas());
        holder.absenPadaJam.setText(absen.getAbsenPadaJam());
        holder.tanggal.setText(absen.getTanggal());
        holder.keterangan.setText(absen.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {
        TextView namaSiswa;
        TextView kelas;
        TextView absenPadaJam;
        TextView tanggal;
        TextView keterangan;

        public HistoryHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            namaSiswa = itemView.findViewById(R.id.tv_nama_siswa);
            kelas = itemView.findViewById(R.id.tv_kelas);
            absenPadaJam = itemView.findViewById(R.id.tv_absen_siswa);
            tanggal = itemView.findViewById(R.id.tv_tgl_siswa);
            keterangan = itemView.findViewById(R.id.tv_keterangan);
        }
    }
}
