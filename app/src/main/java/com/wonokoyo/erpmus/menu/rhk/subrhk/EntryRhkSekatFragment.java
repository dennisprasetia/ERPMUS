package com.wonokoyo.erpmus.menu.rhk.subrhk;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Rhk;
import com.wonokoyo.erpmus.classes.Sekat;

import java.util.ArrayList;
import java.util.List;

public class EntryRhkSekatFragment extends Fragment {

    // variable layout
    private ImageView imgBack;
    private Button btnBerikut;
    private Button btnAddSekat;

    // variable arg
    Rhk rhk;

    private OnFragmentInteractionListener mListener;

    public EntryRhkSekatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entry_rhk_sekat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final NavController navController = Navigation.findNavController(view);

        // get arguments
        if (getArguments() != null)
            rhk = EntryRhkSekatFragmentArgs.fromBundle(getArguments()).getRhk();

        final List<Sekat> sekatList = new ArrayList<>();
        for (int a = 0; a < 3; a++) {
            Sekat sekat = new Sekat(a+1, 2, 1.5);
            sekatList.add(sekat);
        }

        // navigation
        imgBack = view.findViewById(R.id.imgBackSekat);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        btnAddSekat = view.findViewById(R.id.btnAddSekat);
        btnAddSekat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnBerikut = view.findViewById(R.id.btnBerikutSekat);
        btnBerikut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rhk.setSekats(sekatList);

                EntryRhkSekatFragmentDirections.ViewPakanKematianFragment actions =
                        EntryRhkSekatFragmentDirections.viewPakanKematianFragment(rhk);
                navController.navigate(actions);
            }
        });
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
