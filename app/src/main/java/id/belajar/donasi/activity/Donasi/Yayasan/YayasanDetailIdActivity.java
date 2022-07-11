package id.belajar.donasi.activity.Donasi.Yayasan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.belajar.donasi.databinding.ActivityQrBinding;
import id.belajar.donasi.databinding.ActivityYayasanDetailBinding;
import id.belajar.donasi.databinding.ActivityYayasanDetailIdBinding;

public class YayasanDetailIdActivity extends AppCompatActivity {
    private ActivityYayasanDetailIdBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityYayasanDetailIdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}