package com.wonokoyo.erpmus.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Nekropsi;

import java.util.List;

public class NekropsiAdapter extends RecyclerView.Adapter<NekropsiAdapter.RecycleViewHolder> {

    private List<Nekropsi> mNekropsis;

    public NekropsiAdapter(List<Nekropsi> nekropsis) {
        this.mNekropsis = nekropsis;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_item_nekropsi, parent, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecycleViewHolder holder, int position) {
        Nekropsi mNekropsi = mNekropsis.get(position);

        holder.txtParameter.setText(mNekropsi.getNama());
        holder.etKeterangan.setText(mNekropsi.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return mNekropsis.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        TextView txtParameter;
        CheckBox cbNekropsi;
        EditText etKeterangan;

        private RecycleViewHolder(@NonNull View itemView) {
            super(itemView);

            txtParameter = itemView.findViewById(R.id.txtParameter);
            cbNekropsi = itemView.findViewById(R.id.cbNekropsi);
            etKeterangan = itemView.findViewById(R.id.etKeterangan);
        }
    }
}
