package id.belajar.donasi.activity.Berita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import id.belajar.donasi.R;
import id.belajar.donasi.databinding.ActivityBeritaBinding;
import id.belajar.donasi.databinding.ActivityBeritaDetailBinding;

public class BeritaDetailActivity extends AppCompatActivity {
    private ActivityBeritaDetailBinding binding;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBeritaDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

}