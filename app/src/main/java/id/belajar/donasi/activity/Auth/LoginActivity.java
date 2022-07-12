package id.belajar.donasi.activity.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import id.belajar.donasi.MainActivity;
import id.belajar.donasi.MyApplication;
import id.belajar.donasi.activity.Profile.ProfileDetailActivity;
import id.belajar.donasi.connection.Connection;
import id.belajar.donasi.databinding.ActivityLoginBinding;
import id.belajar.donasi.entity.LoginRequest;
import id.belajar.donasi.entity.LoginResponse;
import id.belajar.donasi.utils.Shared;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(v -> validasi());

        binding.btnregister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
    void validasi (){
        String email = binding.editEmail.getText().toString();
        String password = binding.editPassword .getText().toString();
        if (email.equals("")  || password.equals("")){
            Toast.makeText(this,"Tidak Boleh Kosong",Toast.LENGTH_LONG).show();
        }else {
            login(email,password);
        }
    }

    public void login(String email, String password){
        LoginRequest request = new LoginRequest();
        request.email = email;
        request.password = password;

        Connection.getInstance().getServiceEndPoint().login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (loginResponse.code.equals("00")){
                    goToLogin(loginResponse);
                }else{
                    Toast.makeText(LoginActivity.this, loginResponse.message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Hubungui Admin",Toast.LENGTH_LONG).show();
            }
        });
    }

    void goToLogin(LoginResponse loginResponse){
        MyApplication.getInstance().setUserSession(loginResponse.data);
//        Shared.setValue("access_token",loginResponse.data.access_token);
//        Connection.getInstance().resetToken();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this,"Anda Telah Login",Toast.LENGTH_LONG).show();
        finish();
    }
}