package com.wonokoyo.erpmus.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Attachment;

import java.io.File;
import java.util.List;

public class AttachmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Attachment> mAttachment;
    private Context context;

    class ViewHolderPhoto extends RecyclerView.ViewHolder {
        public ImageView iv;

        public ViewHolderPhoto(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.imgViewPhoto);
        }
    }

    class ViewHolderVideo extends RecyclerView.ViewHolder {

        public ViewHolderVideo(@NonNull View itemView) {
            super(itemView);
        }
    }

    public AttachmentAdapter(Context context) {
        this.context = context;
    }

    public void addAttach(List<Attachment> attachments) {
        this.mAttachment.clear();
        this.mAttachment.addAll(attachments);
        notifyDataSetChanged();
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
        Attachment attachment = mAttachment.get(position);

        switch (holder.getItemViewType()) {
            case 0:
                File file = new File(attachment.getUrl());
                Bitmap bitmap = new BitmapDrawable(context.getResources(), file.getAbsolutePath()).getBitmap();
                ((ViewHolderPhoto) holder).iv.setImageBitmap(bitmap);
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
