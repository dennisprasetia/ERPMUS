package com.wonokoyo.erpmus.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Attachment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AttachmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Attachment> mAttachment;
    private Context context;

    class ViewHolderPhoto extends RecyclerView.ViewHolder {
        private ImageView iv;

        public ViewHolderPhoto(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.imgViewPhoto);
        }
    }

    class ViewHolderVideo extends RecyclerView.ViewHolder {
        private VideoView vv;
        private ImageView imgPlay;

        public ViewHolderVideo(@NonNull View itemView) {
            super(itemView);
            vv = itemView.findViewById(R.id.videoView);
            imgPlay = itemView.findViewById(R.id.imgPlayVideo);
            imgPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    vv.start();
                }
            });
        }
    }

    public AttachmentAdapter(Context context) {
        this.context = context;
        this.mAttachment = new ArrayList<>();
    }

    public void addAttach(List<Attachment> attachments) {
        if (attachments != null && attachments.size() > 0) {
            mAttachment.clear();
            mAttachment.addAll(attachments);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        // type = 0 (photo), type = 1 video
        return mAttachment.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
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
                Uri uri = Uri.parse(attachment.getUrl());
                ((ViewHolderVideo) holder).vv.setVideoURI(uri);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mAttachment.size();
    }
}
