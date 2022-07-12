package id.belajar.donasi.connection;

import java.util.HashMap;
import java.util.List;

import id.belajar.donasi.entity.BaseResponse;
import id.belajar.donasi.entity.Berita;
import id.belajar.donasi.entity.Gallery;
import id.belajar.donasi.entity.LoginRequest;
import id.belajar.donasi.entity.LoginResponse;
import id.belajar.donasi.entity.User;
import id.belajar.donasi.entity.Yayasan;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DonasiInterface {

    @POST("api/loginApi")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("api/yayasan")
    Call<BaseResponse<List<Yayasan>>> getListYayasan();

    @GET("api/gallery")
    Call<BaseResponse<List<Gallery>>> getListGallery();

    @GET("/api/detail/{id}")
    Call<BaseResponse<User>> getProfileDetail(@Path("id") String id);

    @GET("api/yayasan/detail/{id}")
    Call<BaseResponse<Yayasan>> getYayasanDetail(@Path("id") String id);

    @GET("api/berita")
    Call<BaseResponse<List<Berita>>> getListBerita();

    @POST("api/donasi/submit")
    Call<BaseResponse<String>> submitDonasi(@Body HashMap<String,String> request);
}
