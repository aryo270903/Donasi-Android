package id.belajar.donasi.activity.Donasi.Yayasan;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import id.belajar.donasi.MainActivity;
import id.belajar.donasi.R;
import id.belajar.donasi.entity.Yayasan;

public class YayasanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Yayasan> dtList = new ArrayList<>();
    private Activity activity;
    private YayasanActivity yayasanActivity;

    public YayasanAdapter(Activity activity,List<Yayasan> dtList){
        this.activity = activity;
        this.dtList = dtList;
    }
    public YayasanAdapter(Activity activity){
        this.activity = activity;
    }
    public void setData(List<Yayasan>data){
        this.dtList = data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_yayasan, parent, false);
        return new id.belajar.donasi.activity.Donasi.Yayasan.YayasanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Yayasan dt = dtList.get(position);

        TextView txtNamaLengkap = holder.itemView.findViewById(R.id.txtNamaLengkap);
        TextView txtEmail = holder.itemView.findViewById(R.id.txtEmail);
        TextView notlp = holder.itemView.findViewById(R.id.notlp);
        RelativeLayout wrapperYayasan = holder.itemView.findViewById(R.id.wrapperYayasan);

        wrapperYayasan.setOnClickListener(v -> {
          Intent intent = new Intent(activity, YayasanDetailActivity.class);
          intent.putExtra("extra_yayasan",new Gson().toJson(dt));
          activity.startActivity(intent);
        });

        notlp.setText(dt.no_tlp);
        txtNamaLengkap.setText(dt.nama_lengkap);
        txtEmail.setText(dt.email);
    }

    @Override
    public int getItemCount() {
        return dtList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
