package com.wonokoyo.erpmus;

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

public class MainRhkFragment extends Fragment {

    private Button btnEntryRhk;
    private ImageView imgBack;

    private OnFragmentInteractionListener mListener;

    public MainRhkFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_rhk, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnEntryRhk = view.findViewById(R.id.btnEntryRhk);
        btnEntryRhk.setOnClickListener(Navigation.createNavigateOnClickListener(
                R.id.action_mainRhkFragment_to_entryRhkActivity));
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
