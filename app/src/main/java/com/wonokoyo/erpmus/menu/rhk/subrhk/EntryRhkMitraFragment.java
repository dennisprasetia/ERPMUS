package com.wonokoyo.erpmus.menu.rhk.subrhk;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
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
    private ConstraintLayout clTitle;

    private OnFragmentInteractionListener mListener;

    public EntryRhkMitraFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entry_rhk_mitra, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final NavController navController = Navigation.findNavController(view);

        Mitra mitra = new Mitra();
        mitra.setNama("Dennis");
        mitra.setKandang(1);
        mitra.setPopulasi(22000);
        mitra.setUmur(40);

        final Rhk rhk = new Rhk();
        rhk.setMitra(mitra);

        // navigation
        btnBerikut = view.findViewById(R.id.btnBerikutMitra);
        btnBerikut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntryRhkMitraFragmentDirections.ViewSekatFragment actions =
                        EntryRhkMitraFragmentDirections.viewSekatFragment(rhk);
                navController.navigate(actions);
            }
        });

        imgBack = view.findViewById(R.id.imgBackSolusi);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

//        clTitle = view.findViewById(R.id.clMitra);
//        LayoutInflater inflater = LayoutInflater.from(this.getContext());
//        View title = inflater.inflate(R.layout.isi_sekat, null, false);
//        clTitle.addView(title);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
