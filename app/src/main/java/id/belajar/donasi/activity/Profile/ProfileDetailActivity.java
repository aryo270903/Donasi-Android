package id.belajar.donasi.activity.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

import id.belajar.donasi.MyApplication;
import id.belajar.donasi.activity.Auth.RegisterActivity;
import id.belajar.donasi.connection.Connection;
import id.belajar.donasi.databinding.ActivityProfileDetailBinding;
import id.belajar.donasi.entity.BaseResponse;
import id.belajar.donasi.entity.User;
import id.belajar.donasi.utils.Shared;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileDetailActivity extends AppCompatActivity {
    private ActivityProfileDetailBinding binding;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = MyApplication.getInstance().getUserSession();

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    binding.Inputnama.setText(user.nama);
                    binding.Inputnama.setEnabled(true);
                    binding.InputEmail.setText(user.email);
                    binding.InputEmail.setEnabled(true);
                    binding.Inputnotelp.setText(user.no_telp);
                    binding.Inputnotelp.setEnabled(true);
                    binding.Inputalamat.setText(user.alamat);
                    binding.Inputalamat.setEnabled(true);
                    binding.btnsubmit.setVisibility(View.VISIBLE);
                    binding.btnEdit.setVisibility(View.GONE);
            }
        });
        binding.backeditprofile.setOnClickListener(v -> {
            finish();
        });
        getProfileDetail();
        binding.btnsubmit.setOnClickListener(v -> validasi());
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
                        Toast.makeText(ProfileDetailActivity.this,res.message, Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(ProfileDetailActivity.this,response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                Toast.makeText(ProfileDetailActivity.this,"Gagal",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void mappingUserView(User user){
        binding.Inputnama.setText(user.nama);
        binding.Inputnama.setEnabled(false);
        binding.InputEmail.setText(user.email);
        binding.InputEmail.setEnabled(false);
        binding.Inputnotelp.setText(user.no_telp);
        binding.Inputnotelp.setEnabled(false);
        binding.Inputalamat.setText(user.alamat);
        binding.Inputalamat.setEnabled(false);
    }

    private void validasi(){
        String nama = binding.Inputnama.getText().toString();
        String email = binding.InputEmail.getText().toString();
        String notelp = binding.Inputnotelp.getText().toString();
        String alamat = binding.Inputalamat.getText().toString();

        Editprofile(nama,email,notelp,alamat);
    }

    void Editprofile(String nama,String email,String no_telp,String alamat){

        HashMap<String,String> request = new HashMap<>();
        request.put("nama",nama);
        request.put("email", email);
        request.put("no_telp",no_telp);
        request.put("alamat",alamat);

        Connection.getInstance().getServiceEndPoint().Editprofile(user.id,request).enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                if(response.code() == 200 ){
                    BaseResponse<User> res = response.body();
                    if(res.code.equals("00")) {
                        user = res.data;
                        MyApplication.getInstance().setUserSession(user);
                        finish();
                        Toast.makeText(ProfileDetailActivity.this, "save sukses", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(ProfileDetailActivity.this, "ioo", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ProfileDetailActivity.this, "oii", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                Toast.makeText(ProfileDetailActivity.this, "GENERE ERROR HUBUNGIN ADMISTRATOR", Toast.LENGTH_SHORT).show();
            }
        });
    }
}