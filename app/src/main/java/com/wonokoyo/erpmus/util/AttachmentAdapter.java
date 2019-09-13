package com.wonokoyo.erpmus.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Attachment;

import java.util.List;

public class AttachmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private FragmentManager mFragmentManager;
    private List<Attachment> mAttachment;

    class ViewHolderPhoto extends RecyclerView.ViewHolder {

        public ViewHolderPhoto(@NonNull View itemView) {
            super(itemView);
        }
    }

    class ViewHolderVideo extends RecyclerView.ViewHolder {

        public ViewHolderVideo(@NonNull View itemView) {
            super(itemView);
        }
    }

    public AttachmentAdapter(FragmentManager mFragmentManager, List<Attachment> mAttachment) {
        this.mFragmentManager = mFragmentManager;
        this.mAttachment = mAttachment;
    }

    @Override
    public int getItemViewType(int position) {
        // type = 0 (photo), type = 1 video
        return mAttachment.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = null;
        if (viewType == 0) {
            view = inflater.inflate(R.layout.view_photo, parent, false);
            return new ViewHolderPhoto(view);
        } else {
            view = inflater.inflate(R.layout.view_video, parent, false);
            return new ViewHolderVideo(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                break;

            case 1:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mAttachment.size();
    }
}
