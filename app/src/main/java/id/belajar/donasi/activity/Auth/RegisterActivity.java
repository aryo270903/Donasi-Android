package id.belajar.donasi.activity.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;

import id.belajar.donasi.activity.Donasi.Yayasan.YayasanDetailActivity;
import id.belajar.donasi.connection.Connection;
import id.belajar.donasi.databinding.ActivityRegisterBinding;
import id.belajar.donasi.databinding.ActivityYayasanDetailBinding;
import id.belajar.donasi.entity.BaseResponse;
import id.belajar.donasi.entity.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(v -> validasi());
    }

    private void validasi(){
        String nama = binding.InputNamaLengkap.getText().toString();
        String email = binding.InputEmail.getText().toString();
        String notelp = binding.InputNoTlp.getText().toString();
        String alamat = binding.InputAlamat.getText().toString();
        String password = binding.InputPassword.getText().toString();

        register(nama,email,notelp,alamat,password);
    }


    void register(String nama,String email,String no_telp,String alamat,String password){

        HashMap<String,String> request = new HashMap<>();
        request.put("nama",nama);
        request.put("email", email);
        request.put("no_telp",no_telp);
        request.put("alamat",alamat);
        request.put("password",password);

        Connection.getInstance().getServiceEndPoint().register(request).enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {

                if(response.code() == 200 ){
                    BaseResponse<User> res = response.body();
                    if(res.code.equals("00")) {
                        finish();
                        Toast.makeText(RegisterActivity.this, "save sukses", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(RegisterActivity.this, res.message, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(RegisterActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "GENERE ERROR HUBUNGIN ADMISTRATOR", Toast.LENGTH_SHORT).show();
            }
        });
    }
}