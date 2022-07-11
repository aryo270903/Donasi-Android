package id.belajar.donasi.entity;

public class BaseResponse<T> {
    public String code;
    public String message;
    public T data;
}
