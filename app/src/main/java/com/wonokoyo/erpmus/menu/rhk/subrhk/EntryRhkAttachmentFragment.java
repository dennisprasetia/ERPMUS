package com.wonokoyo.erpmus.menu.rhk.subrhk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Rhk;
import com.wonokoyo.erpmus.util.AttachmentAdapter;

public class EntryRhkAttachmentFragment extends Fragment {

    // variable layout
    private ImageView imgBtnPhoto;
    private ImageView imgBtnVideo;
    private Button btnBerikut;
    private RecyclerView recyclerView;

    // variable args
    Rhk rhk = null;

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
        final NavController navController = Navigation.findNavController(view);

        if (getArguments() != null)
            rhk = EntryRhkNekropsiFragmentArgs.fromBundle(getArguments()).getRhk();

        imgBtnPhoto = view.findViewById(R.id.imgBtnPhoto);
        imgBtnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntryRhkAttachmentFragmentDirections.ViewFotoFragment action =
                        EntryRhkAttachmentFragmentDirections.viewFotoFragment(rhk);
                navController.navigate(action);
            }
        });

        imgBtnVideo = view.findViewById(R.id.imgBtnVideo);
        imgBtnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntryRhkAttachmentFragmentDirections.ViewVideoFragment action =
                        EntryRhkAttachmentFragmentDirections.viewVideoFragment(rhk);
                navController.navigate(action);
            }
        });

        btnBerikut = view.findViewById(R.id.btnBerikutAttachment);
        btnBerikut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntryRhkAttachmentFragmentDirections.ViewSolusiFragment action =
                        EntryRhkAttachmentFragmentDirections.viewSolusiFragment(rhk);
                navController.navigate(action);
            }
        });

        recyclerView = view.findViewById(R.id.recycleAttachment);
        AttachmentAdapter adapter = new AttachmentAdapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.addAttach(rhk.getAttachments());
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
