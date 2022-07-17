package id.belajar.donasi.activity.Gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import id.belajar.donasi.R;
import id.belajar.donasi.activity.Berita.BeritaActivity;
import id.belajar.donasi.activity.Berita.BeritaAdapter;
import id.belajar.donasi.connection.Connection;
import id.belajar.donasi.databinding.ActivityGalleryBinding;
import id.belajar.donasi.entity.BaseResponse;
import id.belajar.donasi.entity.Berita;
import id.belajar.donasi.entity.Gallery;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryActivity extends AppCompatActivity {
    private ActivityGalleryBinding binding;
    private GalleryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityGalleryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        adapter = new GalleryAdapter(this);
        RecyclerView.LayoutManager  layoutManager = new GridLayoutManager(this,2);
        binding.recyclerview.setLayoutManager(layoutManager);
        binding.recyclerview.setAdapter(adapter);

        binding.backgallery.setOnClickListener(v -> {
            finish();
        });
        getGallery();
    }

    void getGallery(){
        Connection.getInstance().getServiceEndPoint().getListGallery(500).enqueue(new Callback<BaseResponse<List<Gallery>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Gallery>>> call, Response<BaseResponse<List<Gallery>>> response) {
                if (response.isSuccessful()){
                    BaseResponse<List<Gallery>> res = response.body();
                    if (res.code.equals("00")){
                        adapter.setData(res.data);
                        adapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(GalleryActivity.this, res.message, Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(GalleryActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Gallery>>> call, Throwable t) {
                Toast.makeText(GalleryActivity.this,"Gagal Masuk", Toast.LENGTH_LONG).show();
            }
        });
    }
}