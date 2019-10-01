package com.wonokoyo.erpmus.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Sekat;

import java.util.ArrayList;
import java.util.List;

public class SekatAdapter extends RecyclerView.Adapter<SekatAdapter.RecycleViewSekat> {

    private List<Sekat> mSekats;
    private Context mContext;

    public SekatAdapter(Context context) {
        this.mContext = context;
        this.mSekats = new ArrayList<>();
    }

    public void addSekat(List<Sekat> sekatList) {
        if (sekatList.size() > 0) {
            mSekats.clear();
            mSekats.addAll(sekatList);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public SekatAdapter.RecycleViewSekat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.isi_sekat, parent, false);
        return new RecycleViewSekat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SekatAdapter.RecycleViewSekat holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecycleViewSekat extends RecyclerView.ViewHolder {
        public RecycleViewSekat(@NonNull View itemView) {
            super(itemView);
        }
    }
}
