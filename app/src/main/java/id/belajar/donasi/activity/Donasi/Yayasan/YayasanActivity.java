package id.belajar.donasi.activity.Donasi.Yayasan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import id.belajar.donasi.MainActivity;
import id.belajar.donasi.R;
import id.belajar.donasi.activity.Auth.LoginActivity;
import id.belajar.donasi.activity.Donasi.Add.DonasiActivity;
import id.belajar.donasi.activity.Gallery.GalleryAdapter;
import id.belajar.donasi.connection.Connection;
import id.belajar.donasi.databinding.ActivityYayasanBinding;
import id.belajar.donasi.entity.BaseResponse;
import id.belajar.donasi.entity.User;
import id.belajar.donasi.entity.Yayasan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YayasanActivity extends AppCompatActivity {
    private ActivityYayasanBinding binding;
    private YayasanAdapter adapter;
    private Yayasan yayasan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityYayasanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new YayasanAdapter(this);
        binding.recyclerview.setHasFixedSize(true);
        LinearLayoutManager  layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
        binding.recyclerview.setAdapter(adapter);

        binding.backyayasan.setOnClickListener(v -> {
            finish();
        });

        getYayasan();
    }

    void getYayasan(){
        Connection.getInstance().getServiceEndPoint().getListYayasan().enqueue(new Callback<BaseResponse<List<Yayasan>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Yayasan>>> call, Response<BaseResponse<List<Yayasan>>> response) {
                if (response.isSuccessful()){
                    BaseResponse<List<Yayasan>> res = response.body();
                    if (res.code.equals("00")){
                        adapter.setData(res.data);
                        adapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(YayasanActivity.this, res.message, Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(YayasanActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Yayasan>>> call, Throwable t) {
                Toast.makeText(YayasanActivity.this,"Gagal Masuk", Toast.LENGTH_LONG).show();
            }
        });
    }
}