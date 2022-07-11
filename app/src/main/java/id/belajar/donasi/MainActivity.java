package id.belajar.donasi;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.List;

import id.belajar.donasi.activity.Auth.LoginActivity;
import id.belajar.donasi.activity.Berita.BeritaActivity;
import id.belajar.donasi.activity.Berita.BeritaAdapter;
import id.belajar.donasi.activity.Donasi.Yayasan.YayasanActivity;
import id.belajar.donasi.activity.Donasi.Yayasan.YayasanDetailActivity;
import id.belajar.donasi.activity.Gallery.GalleryActivity;
import id.belajar.donasi.activity.Gallery.GalleryAdapter;
import id.belajar.donasi.activity.Profile.ProfileDetailActivity;
import id.belajar.donasi.activity.camera.CameraActivity;
import id.belajar.donasi.activity.camera.CaptureAct;
import id.belajar.donasi.activity.camera.QrActivity;
import id.belajar.donasi.connection.Connection;
import id.belajar.donasi.databinding.ActivityMainBinding;
import id.belajar.donasi.entity.BaseResponse;
import id.belajar.donasi.entity.Berita;
import id.belajar.donasi.entity.Gallery;
import id.belajar.donasi.entity.LoginRequest;
import id.belajar.donasi.entity.LoginResponse;
import id.belajar.donasi.entity.User;
import id.belajar.donasi.entity.Yayasan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private BeritaAdapter adapter;
    private GalleryAdapter adapter1;
    private User user;
    private Yayasan yayasan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = MyApplication.getInstance().getUserSession();


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (MyApplication.getInstance().userSession == null){
            binding.textnama.setVisibility(View.GONE);
            binding.btnEdit.setVisibility(View.GONE);
            binding.btnLogin.setVisibility(View.VISIBLE);
            binding.btnkeluar.setVisibility(View.GONE);
        }else {
            getProfileDetail();
            binding.btnLogin.setVisibility(View.GONE);
        }

        binding.btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        });

        adapter = new BeritaAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
        binding.recyclerview.setAdapter(adapter);

        adapter1 = new GalleryAdapter(this);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerview1.setLayoutManager(layoutManager2);
        binding.recyclerview1.setAdapter(adapter1);

        getGallery();
        getBerita();

        binding.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileDetailActivity.class);
            startActivity(intent);
        });

        binding.btndonasi.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, YayasanActivity.class);
            startActivity(intent);
        });

        binding.textberita2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BeritaActivity.class);
            startActivity(intent);
        });

        binding.textberita5.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
            startActivity(intent);
        });

        binding.btnscan.setOnClickListener(v -> {
            scanCode();
        });

        binding.btnkeluar.setOnClickListener(v -> {
            SharedPreferences pref = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this,"Anda Telah Loqout",Toast.LENGTH_LONG).show();
            finish();
        });

    }

    private void getProfileDetail() {
        Connection.getInstance().getServiceEndPoint().getProfileDetail(user.id).enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                if (response.isSuccessful()){
                    BaseResponse<User> res = response.body();
                    if (res.code.equals("00")){
                        mappingUserView(res.data);
                    }else {
                        Toast.makeText(MainActivity.this,res.message, Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this,response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Gagal",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void mappingUserView(User user){
        binding.textnama.setText(user.full_name);

    }

    void getBerita(){
        Connection.getInstance().getServiceEndPoint().getListBerita().enqueue(new Callback<BaseResponse<List<Berita>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Berita>>> call, Response<BaseResponse<List<Berita>>> response) {
                if (response.isSuccessful()){
                    BaseResponse<List<Berita>> res = response.body();
                    if (res.code.equals("00")){
                        adapter.setData(res.data);
                        adapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(MainActivity.this, res.message, Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Berita>>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Gagal Masuk", Toast.LENGTH_LONG).show();
            }
        });
    }

    void getGallery(){
        Connection.getInstance().getServiceEndPoint().getListGallery().enqueue(new Callback<BaseResponse<List<Gallery>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Gallery>>> call, Response<BaseResponse<List<Gallery>>> response) {
                if (response.isSuccessful()){
                    BaseResponse<List<Gallery>> res = response.body();
                    if (res.code.equals("00")){
                        adapter1.setData(res.data);
                        adapter1.notifyDataSetChanged();
                    }else {
                        Toast.makeText(MainActivity.this, res.message, Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Gallery>>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Gagal Masuk", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void scanCode()
    {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result->
    {
        if(result.getContents() !=null)
        {
            try {
                String id = result.getContents().split("=")[1];
                getDetailYayasan(id);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this,"Invalid Qr", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Invalid Qr Data", Toast.LENGTH_LONG).show();
        }
    });


    private void getDetailYayasan(String idYayasan){
        Connection.getInstance().getServiceEndPoint().getYayasanDetail(idYayasan).enqueue(new Callback<BaseResponse<Yayasan>>() {
            @Override
            public void onResponse(Call<BaseResponse<Yayasan>> call, Response<BaseResponse<Yayasan>> response) {
                if(response.isSuccessful()){
                    BaseResponse<Yayasan> res  = response.body();
                    if(res.code.equals("00")){
                        Intent intent = new Intent(MainActivity.this, YayasanDetailActivity.class);
                        intent.putExtra("extra_yayasan",new Gson().toJson(res.data));
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, res.message, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this,  response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Yayasan>> call, Throwable t) {

            }
        });
    }


}