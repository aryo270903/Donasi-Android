package id.belajar.donasi.activity.Donasi.Yayasan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.HashMap;

import id.belajar.donasi.BuildConfig;
import id.belajar.donasi.MainActivity;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityYayasanDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
        String iddonatur = binding.InputIdDonatur.getText().toString();
        String jenisbarang = binding.InputJenisBarang.getSelectedItem().toString();
        String jumlah = binding.InputJumlah.getText().toString();
        String pengiriman = binding.InputPengiriman.getSelectedItem().toString();
        String provinsi = binding.InputProvinsi.getText().toString();
        String kota = binding.InputKota.getText().toString();
        String kecamatan = binding.InputKecamatan.getText().toString();
        String kelurahan = binding.InputKelurahan.getText().toString();
        String longitude = binding.Inputlatitude.getText().toString();
        String latitude = binding.Inputlongitude.getText().toString();
        String status = binding.InputStatus.getSelectedItem().toString();

        submitDonasi(iddonatur,jenisbarang,jumlah,pengiriman,provinsi,kota,kecamatan,kelurahan,longitude,latitude,status);
    }

    void submitDonasi(String iddonatur, String jenis_donasi, String jumlah, String pengiriman, String provinsi, String kota , String kecamatan,
                      String kelurahan, String longitude, String latitude, String status){

        HashMap<String,String> request = new HashMap<>();
        request.put("id_donatur",iddonatur);
        request.put("jenis_donasi",jenis_donasi);
        request.put("jumlah",jumlah);
        request.put("pengiriman",pengiriman);
        request.put("provinsi",provinsi);
        request.put("kota",kota);
        request.put("kecamatan",kecamatan);
        request.put("kelurahan",kelurahan);
        request.put("longitude",longitude);
        request.put("latitude",latitude);
        request.put("status",status);

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