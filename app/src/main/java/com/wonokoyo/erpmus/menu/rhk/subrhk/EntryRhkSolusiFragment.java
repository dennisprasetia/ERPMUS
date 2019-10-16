package com.wonokoyo.erpmus.menu.rhk.subrhk;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Attachment;
import com.wonokoyo.erpmus.classes.Rhk;
import com.wonokoyo.erpmus.database.RetrofitInstance;
import com.wonokoyo.erpmus.util.SharedPreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntryRhkSolusiFragment extends Fragment {

    // variable layout
    private Button btnSelesai;
    private CheckBox cbSolusi1;
    private CheckBox cbSolusi2;
    private CheckBox cbSolusi3;
    private CheckBox cbSolusi4;

    // variable argument
    Rhk rhk;

    SharedPreferenceManager preferenceManager;

    private OnFragmentInteractionListener mListener;

    public EntryRhkSolusiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferenceManager = new SharedPreferenceManager(getContext());

        // get argument
        if (getArguments() != null)
            rhk = EntryRhkAttachmentFragmentArgs.fromBundle(getArguments()).getRhk();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entry_rhk_solusi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        cbSolusi1 = view.findViewById(R.id.cbSolusi1);
        cbSolusi2 = view.findViewById(R.id.cbSolusi2);
        cbSolusi3 = view.findViewById(R.id.cbSolusi3);
        cbSolusi4 = view.findViewById(R.id.cbSolusi4);

        btnSelesai = view.findViewById(R.id.btnSelesai);
        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> list = new ArrayList<>();

                if (cbSolusi1.isChecked())
                    list.add(cbSolusi1.getText().toString());
                if (cbSolusi2.isChecked())
                    list.add(cbSolusi2.getText().toString());
                if (cbSolusi3.isChecked())
                    list.add(cbSolusi3.getText().toString());
                if (cbSolusi4.isChecked())
                    list.add(cbSolusi4.getText().toString());

                rhk.setListSolusi(list);
            }
        });
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void saveRhkToDatabase(final Rhk upload_rhk) {
        JSONObject objectRhk = new JSONObject();
        JSONObject objectMitra = new JSONObject();
        ArrayList<JSONObject> arrayListSekat = new ArrayList<>();
        JSONObject objectPakanKematian = new JSONObject();
        ArrayList<JSONObject> arrayListNekropsi = new ArrayList<>();
        ArrayList<JSONObject> arrayListSolusi = new ArrayList<>();

        try {
            objectRhk.put("id", preferenceManager.getSpNoRhk());

            // add object mitra
            objectMitra.put("id_rdim", upload_rhk.getMitra().getId());
            objectMitra.put("nama", upload_rhk.getMitra().getNama());
            objectMitra.put("noreg", upload_rhk.getMitra().getNoreg());
            objectMitra.put("kandang", upload_rhk.getMitra().getKandang());
            objectMitra.put("populasi", upload_rhk.getMitra().getPopulasi());
            objectMitra.put("umur", upload_rhk.getMitra().getUmur());

            objectRhk.put("mitra", objectMitra);

            // add object sekat
            for (int a = 0; a < upload_rhk.getSekats().size(); a++) {
                JSONObject sekat = new JSONObject();
                sekat.put("urut", upload_rhk.getSekats().get(a).getNomor());
                sekat.put("jumlah", upload_rhk.getSekats().get(a).getJumlah());
                sekat.put("bb_rata", upload_rhk.getSekats().get(a).getBbRata());

                arrayListSekat.add(sekat);
            }
            objectRhk.put("sekat", arrayListSekat);

            // add object pakan kematian
            objectPakanKematian.put("penerimaan_pakan", upload_rhk.getPakanDanKematian().getPenerimaan());
            objectPakanKematian.put("sisa_pakan", upload_rhk.getPakanDanKematian().getSisa());
            objectPakanKematian.put("angka_kematian", upload_rhk.getPakanDanKematian().getKematian());
            objectPakanKematian.put("keterangan", upload_rhk.getPakanDanKematian().getKeterangan());

            objectRhk.put("pakan_dan_kematian", objectPakanKematian);

            // add object nekropsi
            for (int a = 0; a < upload_rhk.getNekropsies().size(); a++) {
                if (upload_rhk.getNekropsies().get(a).getStatus() == 1) {
                    JSONObject objectNekropsi = new JSONObject();
                    objectNekropsi.put("param", upload_rhk.getNekropsies().get(a).getNama());
                    objectNekropsi.put("keterangan", upload_rhk.getNekropsies().get(a).getKeterangan());

                    arrayListNekropsi.add(objectNekropsi);
                }
            }
            objectRhk.put("nekropsi", arrayListNekropsi);

            // add object solusi
            for (int a = 0; a < upload_rhk.getListSolusi().size(); a++) {
                JSONObject objectSolusi = new JSONObject();
                objectMitra.put("solusi", upload_rhk.getListSolusi().get(a));

                arrayListSolusi.add(objectSolusi);
            }
            objectRhk.put("solusi_rhk", arrayListSolusi);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("CEK", objectRhk.toString());

        final List<Attachment> attachmentList = upload_rhk.getAttachments();
        Call<ResponseBody> bodyCall = RetrofitInstance.rhkService().saveRhk(objectRhk.toString());
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void uploadAttachment(int tipe, String path) {
        File file = new File(path);
        RequestBody body;
        MultipartBody.Part vFile;

        if (tipe == 0) {
            body = RequestBody.create(MediaType.parse("photo/*"), file);
            vFile = MultipartBody.Part.createFormData("photo", file.getName(), body);
        } else {
            body = RequestBody.create(MediaType.parse("video/*"), file);
            vFile = MultipartBody.Part.createFormData("video", file.getName(), body);
        }

        Call<ResponseBody> callUpload = RetrofitInstance.rhkService().uploadVideo(vFile);
        callUpload.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        String msg = jsonObject.getString("message");

                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
