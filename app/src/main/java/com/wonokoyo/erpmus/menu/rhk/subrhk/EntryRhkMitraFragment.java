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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Rhk;
import com.wonokoyo.erpmus.sqlite.DBHelper;

public class EntryRhkMitraFragment extends Fragment {

    private Button btnBerikut;
    private ImageView imgBack;
    private Spinner spMitra;

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

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,
                        listMitra());
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spMitra.setAdapter(spinnerAdapter);
            }
        },1000);

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
        int depth = dbHelper.jumlahMitra();
        String[] list = new String[depth];

        Cursor c = dbHelper.ambilSemuaMitra();
        for (int a = 0; a < c.getCount(); a++) {
            c.moveToNext();
            String namaNoreg = c.getString(c.getColumnIndex("nama")) + " | " + c.getString(c.getColumnIndex("noreg"));

            list[a] = namaNoreg;
        }

        return list;
    }
}
