package id.belajar.donasi.activity.Donasi.Yayasan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.HashMap;

import id.belajar.donasi.BuildConfig;
import id.belajar.donasi.MainActivity;
import id.belajar.donasi.MyApplication;
import id.belajar.donasi.activity.Berita.BeritaDetailActivity;
import id.belajar.donasi.connection.Connection;
import id.belajar.donasi.databinding.ActivityYayasanBinding;
import id.belajar.donasi.databinding.ActivityYayasanDetailBinding;
import id.belajar.donasi.entity.BaseResponse;
import id.belajar.donasi.entity.Berita;
import id.belajar.donasi.entity.User;
import id.belajar.donasi.entity.Yayasan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YayasanDetailActivity extends AppCompatActivity {
    private ActivityYayasanDetailBinding binding;
    private Yayasan yayasan;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityYayasanDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = MyApplication.getInstance().getUserSession();

        if (MyApplication.getInstance().userSession == null){
            binding.InputNama.setVisibility(View.VISIBLE);
            binding.inputEmail.setVisibility(View.VISIBLE);
            binding.Inputnotlp.setVisibility(View.VISIBLE);
        }else {
            binding.InputNama.setVisibility(View.GONE);
            binding.inputEmail.setVisibility(View.GONE);
            binding.Inputnotlp.setVisibility(View.GONE);
        }

        binding.backdetailyayasan.setOnClickListener(v -> {
            finish();
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null ){
            String yayasanExtra = extras.getString("extra_yayasan");
            yayasan = new Gson().fromJson(yayasanExtra, Yayasan.class);
            mappingYayasan(yayasan);
        }

        binding.btnsubmit.setOnClickListener(v -> validasi());
    }
    private void mappingYayasan(Yayasan yayasan){
//        Glide.with(BeritaDetailActivity.this)
//                .load(BuildConfig.BASE_URL_IMAGE+berita.foto)
//                .into(binding.imgberita);
        binding.namalengkap.setText(yayasan.nama_lengkap);
        binding.email.setText(yayasan.email);
        binding.notlp.setText(yayasan.no_tlp);
        binding.keterangan.setText(yayasan.keterangan);
    }

    private void validasi(){
        String jenisbarang = binding.InputJenisBarang.getSelectedItem().toString();
        String jumlah = binding.InputJumlah.getText().toString();
        String pengiriman = binding.InputPengiriman.getSelectedItem().toString();
        String provinsi = binding.InputProvinsi.getText().toString();
        String kota = binding.InputKota.getText().toString();
        String kecamatan = binding.InputKecamatan.getText().toString();
        String kelurahan = binding.InputKelurahan.getText().toString();
        String fulladdress = binding.Inputalamat.getText().toString();

        submitDonasi(jenisbarang,jumlah,pengiriman,provinsi,kota,kecamatan,kelurahan,fulladdress);
    }

    void submitDonasi(String jenis_donasi, String jumlah, String pengiriman, String provinsi, String kota , String kecamatan,
                      String kelurahan, String fulladdress){

        HashMap<String,String> request = new HashMap<>();
        request.put("jenis_donasi",jenis_donasi);
        request.put("jumlah",jumlah);
        request.put("pengiriman",pengiriman);
        request.put("provinsi",provinsi);
        request.put("kota",kota);
        request.put("kecamatan",kecamatan);
        request.put("kelurahan",kelurahan);
        request.put("fulladdress",fulladdress);

        Connection.getInstance().getServiceEndPoint().submitDonasi(request).enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {

                if(response.code() == 200 ){
                    BaseResponse<String> res = response.body();
                    if(res.code.equals("00"))
                        Toast.makeText(YayasanDetailActivity.this, "save sukses", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(YayasanDetailActivity.this, res.message, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(YayasanDetailActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                Toast.makeText(YayasanDetailActivity.this, "GENERE ERROR HUBUNGIN ADMISTRATOR", Toast.LENGTH_SHORT).show();
            }
        });
    }
}