package com.wonokoyo.erpmus.menu.rhk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.wonokoyo.erpmus.R;
import com.wonokoyo.erpmus.classes.Mitra;
import com.wonokoyo.erpmus.database.RetrofitInstance;
import com.wonokoyo.erpmus.sqlite.DBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntryRhkActivity extends AppCompatActivity {

    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_entry_rhk);

        // ambil data mitra simpan ke lokal
        saveToDatabaseSqlite();
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
}
