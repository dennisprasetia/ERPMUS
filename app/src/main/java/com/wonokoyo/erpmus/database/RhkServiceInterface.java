package com.wonokoyo.erpmus.database;

import com.wonokoyo.erpmus.util.Path;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RhkServiceInterface {
    @GET(Path.GET_MITRA)
    Call<ResponseBody> getListMitra();

    @GET(Path.SAVE_RHK)
    Call<ResponseBody> saveRhk(@Query("data_rhk") String arrayJson);

    @Multipart
    @POST(Path.UPLOAD_PHOTO)
    Call<ResponseBody> uploadPhoto(@Part MultipartBody.Part photo);

    @Multipart
    @POST(Path.UPLOAD_VIDEO)
    Call<ResponseBody> uploadVideo(@Part MultipartBody.Part video);
}
