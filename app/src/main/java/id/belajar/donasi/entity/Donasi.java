package id.belajar.donasi.entity;

import com.google.gson.annotations.SerializedName;

public class Donasi {
    public String id;
    @SerializedName("name")
    public String nama;
    @SerializedName("email")
    public String Email;
    public String no_tlp;
    public String jenis_pembayaran;
    public String id_donatur;
    public String jenis_donasi;
    public String jumlah;
    public String pengiriman;
    public String provinsi;
    public String kota;
    public String kecamatan;
    public String kelurahan;
    public String full_address;
    public String status;
}
