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
import android.widget.EditText;
import android.widget.ImageView;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.PakanDanKematian;
import com.wonokoyo.erpmus.classes.Rhk;

public class EntryRhkPakanKematianFragment extends Fragment {

    //variable layout
    private Button btnBerikut;
    private ImageView imgBack;
    private EditText etPenerimaanPakan;
    private EditText etSisaPakan;
    private EditText etAngkaKematian;
    private EditText etKeteranganKematian;

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

        etPenerimaanPakan = view.findViewById(R.id.etTerimaPakan);
        etSisaPakan = view.findViewById(R.id.etSisaPakan);
        etAngkaKematian = view.findViewById(R.id.etKematianAyam);
        etKeteranganKematian = view.findViewById(R.id.etKeteranganPakanKematian);

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
                PakanDanKematian pdk = new PakanDanKematian();
//                pdk.setPenerimaan(Integer.valueOf(etPenerimaanPakan.getText().toString()));
//                pdk.setSisa(Integer.valueOf(etSisaPakan.getText().toString()));
//                pdk.setKematian(Integer.valueOf(etAngkaKematian.getText().toString()));
//                pdk.setKeterangan(etPenerimaanPakan.getText().toString());

                pdk.setPenerimaan(0);
                pdk.setSisa(0);
                pdk.setKematian(0);
                pdk.setKeterangan(etPenerimaanPakan.getText().toString());

                rhk.setPakanDanKematian(pdk);

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
