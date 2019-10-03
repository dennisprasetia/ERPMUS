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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Rhk;
import com.wonokoyo.erpmus.classes.Sekat;
import com.wonokoyo.erpmus.util.SekatAdapter;

import java.util.ArrayList;
import java.util.List;

public class EntryRhkSekatFragment extends Fragment {

    // variable layout
    private ImageView imgBack;
    private Button btnBerikut;
    private Button btnAddSekat;
    private EditText etJumlahSekat;
    private EditText etBbRata;
    private RecyclerView rvSekat;

    // variable arg
    Rhk rhk;
    List<Sekat> sekatList = new ArrayList<>();

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

        etJumlahSekat = view.findViewById(R.id.etJumlahSekat);
        etBbRata = view.findViewById(R.id.etBbRata);
        rvSekat = view.findViewById(R.id.rvSekat);
        rvSekat.setLayoutManager(new LinearLayoutManager(getContext()));

        // get arguments
        if (getArguments() != null)
            rhk = EntryRhkSekatFragmentArgs.fromBundle(getArguments()).getRhk();

        // navigation
        imgBack = view.findViewById(R.id.imgBackSekat);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        // set adapter recycler view sekat
        final SekatAdapter sekatAdapter = new SekatAdapter(sekatList);
        rvSekat.setAdapter(sekatAdapter);

        btnAddSekat = view.findViewById(R.id.btnAddSekat);
        btnAddSekat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sekat sekat = new Sekat();
                sekat.setNomor(sekatList.size() + 1);
                sekat.setJumlah(Integer.valueOf(etJumlahSekat.getText().toString()));
                sekat.setBbRata(Double.valueOf(etBbRata.getText().toString()));
                sekatList.add(sekat);

                sekatAdapter.notifyDataSetChanged();
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
