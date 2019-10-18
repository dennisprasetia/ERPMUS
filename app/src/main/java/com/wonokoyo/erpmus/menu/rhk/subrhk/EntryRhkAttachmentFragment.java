package com.wonokoyo.erpmus.menu.rhk.subrhk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Attachment;
import com.wonokoyo.erpmus.classes.Rhk;
import com.wonokoyo.erpmus.sqlite.DBHelper;
import com.wonokoyo.erpmus.util.AttachmentAdapter;
import com.wonokoyo.erpmus.util.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class EntryRhkAttachmentFragment extends Fragment {

    // variable layout
    private ImageView imgBtnPhoto;
    private ImageView imgBtnVideo;
    private Button btnBerikut;
    private RecyclerView recyclerView;

    // variable args
    Rhk rhk = null;

    DBHelper dbHelper;

    SharedPreferenceManager preferenceManager;

    List<Attachment> attachmentList;

    private OnFragmentInteractionListener mListener;

    public EntryRhkAttachmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DBHelper(getContext());

        preferenceManager = new SharedPreferenceManager(getContext());

        // get argument
        if (getArguments() != null)
            rhk = EntryRhkNekropsiFragmentArgs.fromBundle(getArguments()).getRhk();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        attachmentList = getAttachmentList();

        return inflater.inflate(R.layout.fragment_entry_rhk_attachment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final NavController navController = Navigation.findNavController(view);

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
                rhk.setAttachments(attachmentList);

                EntryRhkAttachmentFragmentDirections.ViewSolusiFragment action =
                        EntryRhkAttachmentFragmentDirections.viewSolusiFragment(rhk);
                navController.navigate(action);
            }
        });

        recyclerView = view.findViewById(R.id.recycleAttachment);
        final AttachmentAdapter adapter = new AttachmentAdapter(getContext(), attachmentList);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(adapter);
                Toast.makeText(getContext(), "Jumlah attachment : " + attachmentList.size(), Toast.LENGTH_SHORT).show();
            }
        }, 300);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public List<Attachment> getAttachmentList() {
        List<Attachment> list = new ArrayList<>();

        Cursor c = dbHelper.ambilRhkAttachmentByRhk(preferenceManager.getSpNoRhk());
        if (c.getCount() > 0) {
            for (int a = 0; a < c.getCount(); a++) {
                c.moveToNext();
                int type;
                if (c.getString(c.getColumnIndex("tipe")).equalsIgnoreCase("photo")) {
                    type = 0;
                } else {
                    type = 1;
                }

                Attachment attachment = new Attachment(
                        c.getString(c.getColumnIndex("url")), type, c.getString(c.getColumnIndex("tipe"))
                );

                list.add(attachment);
            }
        }

        return list;
    }
}
