package com.arifin.daringschool.Activity.UiTeacher.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arifin.daringschool.Activity.UiTeacher.Model.EBook;
import com.arifin.daringschool.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EBookAdapter extends RecyclerView.Adapter<EBookAdapter.EBookViewHolder>{

    private Context mContext;
    private List<EBook> mUploads;
    private OnItemClickListener mListener;

    public EBookAdapter(Context context, List<EBook> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @NotNull
    @Override
    public EBookAdapter.EBookViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_e_book, parent, false);
        return new EBookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull EBookViewHolder holder, int position) {
        EBook uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        holder.textPenulis.setText(uploadCurrent.getmPenulis());
        holder.textPenelaah.setText(uploadCurrent.getmPenelaah());
        holder.textPreview.setText(uploadCurrent.getmPreview());
        holder.textPenerbit.setText(uploadCurrent.getmPenerbit());
        Picasso.get()
                .load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class EBookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView textViewName, textPenulis, textPenelaah, textPreview, textPenerbit;
        public ImageView imageView;

        public EBookViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.tv_judul_EBook);
            imageView = itemView.findViewById(R.id.img_Ebook);
            textPenulis = itemView.findViewById(R.id.tv_penulis);
            textPenelaah = itemView.findViewById(R.id.tv_penelaah);
            textPreview = itemView.findViewById(R.id.tv_preview);
            textPenerbit = itemView.findViewById(R.id.tv_penerbit);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem doWhatever = menu.add(Menu.NONE, 1, 1, "Do whatever");
            MenuItem delete = menu.add(Menu.NONE, 2, 2, "Delete");

            doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {

                    switch (item.getItemId()) {
                        case 1:
                            mListener.onWhatEverClick(position);
                            return true;
                        case 2:
                            mListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onWhatEverClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}
