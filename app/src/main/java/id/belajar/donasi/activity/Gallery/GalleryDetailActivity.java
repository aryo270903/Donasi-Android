package id.belajar.donasi.activity.Gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.belajar.donasi.databinding.ActivityGalleryBinding;
import id.belajar.donasi.databinding.ActivityGalleryDetailBinding;

public class GalleryDetailActivity extends AppCompatActivity {
    private ActivityGalleryDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityGalleryDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}