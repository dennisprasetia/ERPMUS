package com.wonokoyo.erpmus.menu.rhk.subrhk;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Mitra;
import com.wonokoyo.erpmus.classes.Rhk;
import com.wonokoyo.erpmus.sqlite.DBHelper;

public class EntryRhkMitraFragment extends Fragment {

    private Button btnBerikut;
    private ImageView imgBack;
    private Spinner spMitra;
    private Spinner spNoreg;
    private EditText etKandang;
    private EditText etPopulasi;
    private EditText etUmur;

    private OnFragmentInteractionListener mListener;

    DBHelper dbHelper;

    // variable argument
    Rhk rhk = new Rhk();

    public EntryRhkMitraFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_entry_rhk_mitra, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        dbHelper = new DBHelper(getContext());
        final NavController navController = Navigation.findNavController(view);

        spMitra = view.findViewById(R.id.spMitra);
        spNoreg = view.findViewById(R.id.spNoreg);
        etKandang = view.findViewById(R.id.etKandangMitra);
        etPopulasi = view.findViewById(R.id.etPopulasiKandangMitra);
        etUmur = view.findViewById(R.id.etUmurPopulasiKandangMitra);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,
                        listMitra());
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spMitra.setAdapter(spinnerAdapter);
            }
        },1000);

        spMitra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String nama = spMitra.getItemAtPosition(i).toString();

                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,
                        listNoregMitra(nama));
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spNoreg.setAdapter(spinnerAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spNoreg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String noreg = spNoreg.getItemAtPosition(i).toString();

                setDetailMitraKandang(noreg);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // navigation
        btnBerikut = view.findViewById(R.id.btnBerikutMitra);
        btnBerikut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mitra mitra = new Mitra();
                mitra.setNama(spMitra.getSelectedItem().toString());
                mitra.setNoreg(spNoreg.getSelectedItem().toString());
                mitra.setKandang(Integer.valueOf(etKandang.getText().toString()));
                mitra.setPopulasi(Integer.valueOf(etPopulasi.getText().toString()));
                mitra.setUmur(Integer.valueOf(etUmur.getText().toString()));

                rhk.setMitra(mitra);

                EntryRhkMitraFragmentDirections.ViewSekatFragment actions =
                        EntryRhkMitraFragmentDirections.viewSekatFragment(rhk);
                navController.navigate(actions);
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
        void onFragmentInteraction(Uri uri);
    }

    public String[] listMitra() {
        Cursor c = dbHelper.ambilSemuaMitra();
        String[] list = new String[c.getCount()];

        for (int a = 0; a < c.getCount(); a++) {
            c.moveToNext();
            String nama = c.getString(c.getColumnIndex("nama"));

            list[a] = nama;
        }

        return list;
    }

    public String[] listNoregMitra(String nama) {
        Cursor c = dbHelper.ambilNoregMitra(nama);
        String[] list = new String[c.getCount()];

        for (int a = 0; a < c.getCount(); a++) {
            c.moveToNext();
            String noreg = c.getString(c.getColumnIndex("noreg"));

            list[a] = noreg;
        }

        return list;
    }

    public void setDetailMitraKandang(String noreg) {
        Cursor c = dbHelper.ambilDetailMitra(noreg);
        c.moveToLast();

        etKandang.setText(c.getString(c.getColumnIndex("kandang")));
        etPopulasi.setText(c.getString(c.getColumnIndex("populasi")));
        etUmur.setText(c.getString(c.getColumnIndex("umur")));
    }
}
