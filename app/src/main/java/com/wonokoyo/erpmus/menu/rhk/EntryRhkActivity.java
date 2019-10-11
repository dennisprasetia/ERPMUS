package com.wonokoyo.erpmus.menu.rhk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.database.RetrofitInstance;
import com.wonokoyo.erpmus.sqlite.DBHelper;
import com.wonokoyo.erpmus.util.SharedPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntryRhkActivity extends AppCompatActivity {

    DBHelper dbHelper = new DBHelper(this);
    SharedPreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_entry_rhk);

        // ambil data mitra simpan ke lokal
        saveToDatabaseSqlite();
        preferenceManager = new SharedPreferenceManager(this);
        getIdRhk();
    }

    public void saveToDatabaseSqlite() {
        Call<ResponseBody> callMitra = RetrofitInstance.rhkService().getListMitra();
        callMitra.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("content");

                        if (jsonArray.length() > 0 && dbHelper.jumlahMitra() == 0) {
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);

                                String noreg = object.getString("noreg");
                                String kandang = noreg.substring(noreg.length()-2);
                                dbHelper.insertMitra(object.getString("id"), object.getString("nama"),
                                        object.getString("noreg"), kandang, object.getInt("populasi"),
                                        object.getInt("umur"));
                            }
                        } else if (jsonArray.length() > 0 && dbHelper.jumlahMitra() < jsonArray.length()) {
                            dbHelper.hapusSemuaMitra();

                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);

                                String noreg = object.getString("noreg");
                                String kandang = noreg.substring(noreg.length()-2);
                                dbHelper.insertMitra(object.getString("id"), object.getString("nama"),
                                        object.getString("noreg"), kandang, object.getInt("populasi"),
                                        object.getInt("umur"));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (t.getMessage().equalsIgnoreCase("timeout")) {
                    Toast.makeText(EntryRhkActivity.this, "Request Timeout !", Toast.LENGTH_SHORT).show();
                }

                if (t.getMessage().contains("failed to connect")) {
                    Toast.makeText(EntryRhkActivity.this, "Please check your connection !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getIdRhk() {
        Call<ResponseBody> callMitra = RetrofitInstance.rhkService().getMaxIdRhk();
        callMitra.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        int id = jsonObject.getInt("content");
                        preferenceManager.saveSPInt(SharedPreferenceManager.SP_NO_RHK, id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (t.getMessage().equalsIgnoreCase("timeout")) {
                    Toast.makeText(EntryRhkActivity.this, "Request Timeout !", Toast.LENGTH_SHORT).show();
                }

                if (t.getMessage().contains("failed to connect")) {
                    Toast.makeText(EntryRhkActivity.this, "Please check your connection !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
