package id.belajar.donasi.activity.Gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import id.belajar.donasi.BuildConfig;
import id.belajar.donasi.activity.Berita.BeritaDetailActivity;
import id.belajar.donasi.databinding.ActivityGalleryBinding;
import id.belajar.donasi.databinding.ActivityGalleryDetailBinding;
import id.belajar.donasi.entity.Berita;
import id.belajar.donasi.entity.Gallery;

public class GalleryDetailActivity extends AppCompatActivity {
    private ActivityGalleryDetailBinding binding;
    private Gallery gallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityGalleryDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backdetailgallery.setOnClickListener(v -> {
            finish();
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null ){
            String galleryExtra = extras.getString("extra_gallery");
            gallery = new Gson().fromJson(galleryExtra, Gallery.class);
            mappingGallery(gallery);
        }
    }

    private void mappingGallery(Gallery gallery){
        Glide.with(GalleryDetailActivity.this)
                .load(BuildConfig.BASE_URL_IMAGE+gallery.image)
                .into(binding.imgGallery);
        binding.keterangan.setText(gallery.keterangan);
    }
}