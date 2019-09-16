package com.wonokoyo.erpmus.menu.rhk.subrhk;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Rhk;

public class EntryRhkSekatFragment extends Fragment {

    // variable layout
    private ImageView imgBack;
    private Button btnBerikut;

    // variable arg
    Rhk rhk = EntryRhkSekatFragmentArgs.fromBundle(getArguments()).getRhk();

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
        imgBack = view.findViewById(R.id.imgBackSekat);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        btnBerikut = view.findViewById(R.id.btnBerikutSekat);
        btnBerikut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntryRhkSekatFragmentDirections.ViewPakanKematianFragment actions =
                        EntryRhkSekatFragmentDirections.viewPakanKematianFragment(rhk);
                Navigation.findNavController(view).navigate(actions);
            }
        });
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
