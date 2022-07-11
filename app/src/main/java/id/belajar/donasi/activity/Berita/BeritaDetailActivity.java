package id.belajar.donasi.activity.Berita;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

import id.belajar.donasi.BuildConfig;
import id.belajar.donasi.R;
import id.belajar.donasi.activity.Donasi.Yayasan.YayasanDetailActivity;
import id.belajar.donasi.connection.Connection;
import id.belajar.donasi.databinding.ActivityBeritaBinding;
import id.belajar.donasi.databinding.ActivityBeritaDetailBinding;
import id.belajar.donasi.entity.BaseResponse;
import id.belajar.donasi.entity.Berita;
import id.belajar.donasi.entity.Yayasan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritaDetailActivity extends AppCompatActivity {
    private ActivityBeritaDetailBinding binding;
    private Berita berita;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBeritaDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backdetailberita.setOnClickListener(v -> {
            finish();
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null ){
            String beritaExtra = extras.getString("extra_berita");
            berita = new Gson().fromJson(beritaExtra, Berita.class);
            mappingBerita(berita);
        }
    }

    private void mappingBerita(Berita berita){
        Glide.with(BeritaDetailActivity.this)
                .load(BuildConfig.BASE_URL_IMAGE+berita.foto)
                .into(binding.imgberita);
        binding.title.setText(berita.title);
        binding.keterangan.setText(berita.content);
    }

}