package id.belajar.donasi.activity.Donasi.Yayasan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import id.belajar.donasi.MainActivity;
import id.belajar.donasi.connection.Connection;
import id.belajar.donasi.databinding.ActivityYayasanBinding;
import id.belajar.donasi.databinding.ActivityYayasanDetailBinding;
import id.belajar.donasi.entity.BaseResponse;
import id.belajar.donasi.entity.User;
import id.belajar.donasi.entity.Yayasan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YayasanDetailActivity extends AppCompatActivity {
    private ActivityYayasanDetailBinding binding;
    private Yayasan yayasan;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityYayasanDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        if (extras != null ){
            String yayasanExtra = extras.getString("extra_yayasan");
            yayasan = new Gson().fromJson(yayasanExtra,Yayasan.class);
            getProfileDetail();
        }

    }

    private void getProfileDetail() {
       Connection.getInstance().getServiceEndPoint().getYayasanDetail(yayasan.id).enqueue(new Callback<BaseResponse<Yayasan>>() {
           @Override
           public void onResponse(Call<BaseResponse<Yayasan>> call, Response<BaseResponse<Yayasan>> response) {
               if (response.isSuccessful()){
                   BaseResponse<Yayasan> res = response.body();
                   if (res.code.equals("00")){
                       mappingUserView(res.data);
                       binding.namaYayasan.setText(yayasan.nama_lengkap);
                   }else {
                       Toast.makeText(YayasanDetailActivity.this,"iooo", Toast.LENGTH_LONG).show();
                   }
               }else {
                   Toast.makeText(YayasanDetailActivity.this,"oiiii",Toast.LENGTH_LONG).show();
               }
           }

           @Override
           public void onFailure(Call<BaseResponse<Yayasan>> call, Throwable t) {

           }
       });
    }
    private void mappingUserView(Yayasan yayasan){
        binding.namaYayasan.setText(yayasan.nama_lengkap);

    }
}