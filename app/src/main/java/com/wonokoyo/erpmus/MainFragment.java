package com.wonokoyo.erpmus;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

    NavController navController;
    NavDirections navDirections;

    private OnFragmentInteractionListener mListener;

    private Button btnHarianKandang;
    private Button btnTimPanen;
    private Button btnPenjualan;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnHarianKandang = view.findViewById(R.id.btnHarianKandang);
        btnHarianKandang.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_mainFragment_to_mainRhkFragment));

        btnPenjualan = view.findViewById(R.id.btnPenjualan);
        btnPenjualan.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_mainFragment_to_mainPenjualanFragment));
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
