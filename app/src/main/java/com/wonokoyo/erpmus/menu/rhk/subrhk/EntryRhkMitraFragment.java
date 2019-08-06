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

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Mitra;

public class EntryRhkMitraFragment extends Fragment {

    private Button btnBerikut;

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

        final EntryRhkMitraFragmentDirections.Action_entryRhkMitraFragment_to_entryRhkSekatFragment actions =
                EntryRhkMitraFragmentDirections.action_entryRhkMitraFragment_to_entryRhkSekatFragment();
        actions.setArgMitra(mitra);

        btnBerikut = view.findViewById(R.id.btnBerikut);
        btnBerikut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(actions);
            }
        });
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
