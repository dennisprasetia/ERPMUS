package com.wonokoyo.erpmus.menu.rhk.subrhk;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Mitra;
import com.wonokoyo.erpmus.classes.Rhk;

public class EntryRhkMitraFragment extends Fragment {

    private Button btnBerikut;
    private ImageView imgBack;

    private OnFragmentInteractionListener mListener;

    public EntryRhkMitraFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entry_rhk_mitra, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Mitra mitra = new Mitra();
        mitra.setNama("Dennis");
        mitra.setKandang(1);
        mitra.setPopulasi(22000);
        mitra.setUmur(40);

        final Rhk rhk = new Rhk();
        rhk.setMitra(mitra);

        btnBerikut = view.findViewById(R.id.btnBerikutMitra);
        btnBerikut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntryRhkMitraFragmentDirections.ViewSekatFragment actions =
                        EntryRhkMitraFragmentDirections.viewSekatFragment(rhk);
                Navigation.findNavController(view).navigate(actions);
            }
        });

        imgBack = view.findViewById(R.id.imgBackMitra);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
