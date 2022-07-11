package id.belajar.donasi.entity;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    public String code;
    public String message;

    @SerializedName("data")
    public User data;
}
