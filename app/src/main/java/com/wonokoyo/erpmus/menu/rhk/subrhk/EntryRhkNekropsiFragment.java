package com.wonokoyo.erpmus.menu.rhk.subrhk;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Nekropsi;
import com.wonokoyo.erpmus.util.EnumNekropsi;
import com.wonokoyo.erpmus.util.RecycleNekropsi;

import java.util.List;

public class EntryRhkNekropsiFragment extends Fragment {

    // variable layout
    private RecyclerView recyclerView;
    private Button btnBerikut;
    private ImageView imgBack;

    private OnFragmentInteractionListener mListener;

    private List<Nekropsi> listNekropsi;

    public EntryRhkNekropsiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entry_rhk_nekropsi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        listNekropsi = EnumNekropsi.listNekropsi();
        Log.e("CEK LIST", EnumNekropsi.showListInString());

        recyclerView = view.findViewById(R.id.recycleNekropsi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RecycleNekropsi(listNekropsi, getFragmentManager()));

        btnBerikut = view.findViewById(R.id.btnBerikutNekropsi);
        btnBerikut.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.viewAttachmentFragment));

        imgBack = view.findViewById(R.id.imgBackNekropsi);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
