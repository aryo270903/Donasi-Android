package id.belajar.donasi.entity;

import com.google.gson.annotations.SerializedName;

public class Donasi {
    public String id;
    public String name;
    public String email;
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
    @SerializedName("full_address")
    public String fulladdress;
    public String status;
}
