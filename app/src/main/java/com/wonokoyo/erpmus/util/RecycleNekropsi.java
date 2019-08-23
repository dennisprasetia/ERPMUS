package com.wonokoyo.erpmus.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Nekropsi;

public class RecycleNekropsi extends RecyclerView.Adapter<RecycleNekropsi.RecycleViewHolder> {

    private FragmentManager mFragmentManager;

    public RecycleNekropsi(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_item_nekropsi, parent, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        Nekropsi mNekropsi = EnumNekropsi.listNekropsi().get(position);

        holder.txtParameter.setText(mNekropsi.getNama());
        if (mNekropsi.getStatus() == 1) {
            holder.chip.setChecked(true);
        }
        holder.etKeterangan.setText(mNekropsi.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        TextView txtParameter;
        Chip chip;
        EditText etKeterangan;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);

            txtParameter = itemView.findViewById(R.id.txtParameter);
            chip = itemView.findViewById(R.id.chip);
            etKeterangan = itemView.findViewById(R.id.etKeterangan);
        }
    }
}
