package id.belajar.donasi.activity.Berita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.gson.Gson;

import java.util.List;

import id.belajar.donasi.R;
import id.belajar.donasi.activity.Profile.ProfileDetailActivity;
import id.belajar.donasi.connection.Connection;
import id.belajar.donasi.databinding.ActivityBeritaBinding;
import id.belajar.donasi.entity.BaseResponse;
import id.belajar.donasi.entity.Berita;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritaActivity extends AppCompatActivity {
    private ActivityBeritaBinding binding;
    private BeritaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBeritaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new BeritaAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
        binding.recyclerview.setAdapter(adapter);

        binding.backberita.setOnClickListener(v -> {
            finish();
        });
        getBerita();
    }

    void getBerita(){
        Connection.getInstance().getServiceEndPoint().getListBerita(500).enqueue(new Callback<BaseResponse<List<Berita>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Berita>>> call, Response<BaseResponse<List<Berita>>> response) {
                if (response.isSuccessful()){
                    BaseResponse<List<Berita>> res = response.body();
                    if (res.code.equals("00")){
                        adapter.setData(res.data);
                        adapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(BeritaActivity.this, res.message, Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(BeritaActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Berita>>> call, Throwable t) {
                Toast.makeText(BeritaActivity.this,"Gagal Masuk", Toast.LENGTH_LONG).show();
            }
        });
    }
}