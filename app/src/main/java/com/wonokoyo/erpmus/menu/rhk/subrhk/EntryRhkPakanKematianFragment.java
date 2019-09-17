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

public class EntryRhkPakanKematianFragment extends Fragment {

    //variable layout
    private Button btnBerikut;
    private ImageView imgBack;

    // variable args
    Rhk rhk;

    private OnFragmentInteractionListener mListener;

    public EntryRhkPakanKematianFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entry_rhk_pakan_kematian, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final NavController navController = Navigation.findNavController(view);

        // get arguments
        if (getArguments() != null)
            rhk = EntryRhkPakanKematianFragmentArgs.fromBundle(getArguments()).getRhk();

        // navigation
        imgBack = view.findViewById(R.id.imgBackPakan);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        btnBerikut = view.findViewById(R.id.btnBerikutPakan);
        btnBerikut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntryRhkPakanKematianFragmentDirections.ViewNekropsiFragment actions =
                        EntryRhkPakanKematianFragmentDirections.viewNekropsiFragment(rhk);
                navController.navigate(actions);
            }
        });
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
