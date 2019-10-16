package com.wonokoyo.erpmus.menu.rhk.subrhk;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
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
import com.wonokoyo.erpmus.classes.Rhk;
import com.wonokoyo.erpmus.util.EnumNekropsi;
import com.wonokoyo.erpmus.util.NekropsiAdapter;

import java.util.List;

public class EntryRhkNekropsiFragment extends Fragment {

    // variable layout
    private RecyclerView recyclerView;
    private Button btnBerikut;
    private ImageView imgBack;

    // variable args
    Rhk rhk;

    private OnFragmentInteractionListener mListener;

    private List<Nekropsi> listNekropsi;

    public EntryRhkNekropsiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get arguments
        if (getArguments() != null)
            rhk = EntryRhkNekropsiFragmentArgs.fromBundle(getArguments()).getRhk();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entry_rhk_nekropsi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        final NavController navController = Navigation.findNavController(view);

        listNekropsi = EnumNekropsi.listNekropsi();
        recyclerView = view.findViewById(R.id.recycleNekropsi);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(new NekropsiAdapter(listNekropsi));
            }
        }, 400);

        // navigation
        btnBerikut = view.findViewById(R.id.btnBerikutNekropsi);
        btnBerikut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rhk.setNekropsies(listNekropsi);

                EntryRhkNekropsiFragmentDirections.ViewAttachmentFragment action =
                        EntryRhkNekropsiFragmentDirections.viewAttachmentFragment(rhk);
                navController.navigate(action);
            }
        });

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
