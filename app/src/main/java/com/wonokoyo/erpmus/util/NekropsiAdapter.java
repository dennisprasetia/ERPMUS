package com.wonokoyo.erpmus.util;

import android.util.SparseBooleanArray;
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

public class NekropsiAdapter extends RecyclerView.Adapter<NekropsiAdapter.RecycleViewNekropsi> {

    private List<Nekropsi> mNekropsis;
    private final SparseBooleanArray array = new SparseBooleanArray();

    public NekropsiAdapter(List<Nekropsi> nekropsis) {
        this.mNekropsis = nekropsis;
    }

    @NonNull
    @Override
    public RecycleViewNekropsi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_item_nekropsi, parent, false);
        return new RecycleViewNekropsi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewNekropsi holder, int position) {
        Nekropsi nekropsi = mNekropsis.get(position);

        holder.txtParameter.setText(nekropsi.getNama());
        holder.etKeterangan.setText(nekropsi.getKeterangan());
        if (array.get(position)) {
            nekropsi.setStatus(1);
            holder.cbNekropsi.setChecked(true);
        } else {
            nekropsi.setStatus(0);
            holder.cbNekropsi.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return mNekropsis.size();
    }

    protected class RecycleViewNekropsi extends RecyclerView.ViewHolder {
        TextView txtParameter;
        CheckBox cbNekropsi;
        EditText etKeterangan;

        private RecycleViewNekropsi(@NonNull View itemView) {
            super(itemView);

            txtParameter = itemView.findViewById(R.id.txtParameter);
            etKeterangan = itemView.findViewById(R.id.etKeterangan);
            cbNekropsi = itemView.findViewById(R.id.cbNekropsi);
            cbNekropsi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (array.get(getAdapterPosition())) {
                        array.put(getAdapterPosition(), false);
                    } else {
                        array.put(getAdapterPosition(), true);
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }
}
