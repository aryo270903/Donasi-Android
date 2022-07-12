package id.belajar.donasi.activity.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.belajar.donasi.databinding.ActivityRegisterBinding;
import id.belajar.donasi.databinding.ActivityYayasanDetailBinding;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}