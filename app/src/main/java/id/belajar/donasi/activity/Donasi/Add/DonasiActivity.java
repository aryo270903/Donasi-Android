package id.belajar.donasi.activity.Donasi.Add;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

import id.belajar.donasi.connection.Connection;
import id.belajar.donasi.databinding.ActivityDonasiBinding;
import id.belajar.donasi.entity.BaseResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonasiActivity extends AppCompatActivity {
    private ActivityDonasiBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDonasiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnsubmit.setOnClickListener(v -> validasi());
    }

    private void validasi(){
        String iddonatur = binding.InputIdDonatur.getText().toString();
        String jenis_donasi = binding.InputIdDonatur.getText().toString();
        String jumlah = binding.InputIdDonatur.getText().toString();
        String pengiriman = binding.InputIdDonatur.getText().toString();
        String provinsi = binding.InputIdDonatur.getText().toString();
        String kota = binding.InputIdDonatur.getText().toString();
        String kecamatan = binding.InputIdDonatur.getText().toString();
        String kelurahan = binding.InputIdDonatur.getText().toString();
        String longitude = binding.InputIdDonatur.getText().toString();
        String latitude = binding.InputIdDonatur.getText().toString();
        String status = binding.InputIdDonatur.getText().toString();

        submitDonasi(iddonatur,
                jenis_donasi,jumlah,pengiriman,provinsi,
                kota,kecamatan,kelurahan,longitude,latitude,status);
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
                        Toast.makeText(DonasiActivity.this, "save sukses", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DonasiActivity.this, res.message, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(DonasiActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                Toast.makeText(DonasiActivity.this, "GENERE ERROR HUBUNGIN ADMISTRATOR", Toast.LENGTH_SHORT).show();
            }
        });

    }
}