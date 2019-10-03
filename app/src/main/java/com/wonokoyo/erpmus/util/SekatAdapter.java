package com.wonokoyo.erpmus.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Sekat;

import java.util.List;

public class SekatAdapter extends RecyclerView.Adapter<SekatAdapter.RecycleViewSekat> {

    private List<Sekat> mSekats;

    public SekatAdapter(List<Sekat> sekatList) {
        this.mSekats = sekatList;
    }

    @NonNull
    @Override
    public RecycleViewSekat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.isi_sekat, parent, false);
        return new RecycleViewSekat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewSekat holder, int position) {
        Sekat sekat = mSekats.get(position);

        holder.txtUrutan.setText(String.valueOf(sekat.getNomor()));
        holder.txtJumlahSekat.setText(String.valueOf(sekat.getJumlah()));
        holder.txtBbRata.setText(String.valueOf(sekat.getBbRata()));
    }

    @Override
    public int getItemCount() {
        return mSekats.size();
    }

    public class RecycleViewSekat extends RecyclerView.ViewHolder {
        TextView txtUrutan;
        TextView txtJumlahSekat;
        TextView txtBbRata;

        private RecycleViewSekat(@NonNull View itemView) {
            super(itemView);

            txtUrutan = itemView.findViewById(R.id.txtUrutan);
            txtJumlahSekat = itemView.findViewById(R.id.txtJumlahSekat);
            txtBbRata = itemView.findViewById(R.id.txtBbRata);
        }
    }
}
