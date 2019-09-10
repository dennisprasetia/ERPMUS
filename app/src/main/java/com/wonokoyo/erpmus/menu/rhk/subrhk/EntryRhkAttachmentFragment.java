package com.wonokoyo.erpmus.menu.rhk.subrhk;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.wonokoyo.erpmus.R;

public class EntryRhkAttachmentFragment extends Fragment {

    // variable layout
    private ImageButton imgBtnPhoto;
    private ImageButton imgBtnVideo;

    private OnFragmentInteractionListener mListener;

    public EntryRhkAttachmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entry_rhk_attachment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        imgBtnPhoto = view.findViewById(R.id.imgBtnPhoto);
        imgBtnPhoto.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.viewFotoFragment));

        imgBtnVideo = view.findViewById(R.id.imgBtnVideo);
        imgBtnVideo.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.viewVideoFragment));
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
