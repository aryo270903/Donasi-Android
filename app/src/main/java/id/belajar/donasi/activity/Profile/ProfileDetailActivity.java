package id.belajar.donasi.activity.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import id.belajar.donasi.MyApplication;
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

        getProfileDetail();
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
//        binding.txtFirstName.setText(user.first_name);
//        binding.txtLastName.setText(user.last_name);
//        binding.txtPhone.setText(user.phone);
        binding.Inputnama.setText(user.nama);
        binding.Inputnama.setEnabled(false);
        binding.InputEmail.setText(user.email);
        binding.InputEmail.setEnabled(false);
        binding.Inputnotelp.setText(user.no_telp);
        binding.Inputnotelp.setEnabled(false);
        binding.Inputalamat.setText(user.alamat);
        binding.Inputalamat.setEnabled(false);
    }
}