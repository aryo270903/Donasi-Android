package id.belajar.donasi.activity.Berita;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import id.belajar.donasi.BuildConfig;
import id.belajar.donasi.R;
import id.belajar.donasi.activity.Gallery.GalleryActivity;
import id.belajar.donasi.entity.Berita;

public class BeritaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Berita> dtList = new ArrayList<>();
    private Activity activity;

    public BeritaAdapter(Activity activity,List<Berita> dtList){
        this.activity = activity;
        this.dtList = dtList;
    }
    public BeritaAdapter(Activity activity){
        this.activity = activity;
    }
    public void setData(List<Berita>data){
        this.dtList = data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_berita, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Berita dt = dtList.get(position);
        ImageView bennertv = holder.itemView.findViewById(R.id.img1);
        TextView txtTitle = holder.itemView.findViewById(R.id.txtTitle);
        CardView wrap = holder.itemView.findViewById(R.id.wrapBerita);

        wrap.setOnClickListener(v -> {
            Intent intent = new Intent(this.activity, BeritaDetailActivity.class);
            intent.putExtra("extra_berita", new Gson().toJson(dt));
            this.activity.startActivity(intent);
        });

        Glide.with(activity)
                .load(BuildConfig.BASE_URL_IMAGE+dt.foto)
                .into(bennertv);
        txtTitle.setText(dt.title);
    }

    @Override
    public int getItemCount() {
//        int limit = 5;
//        return Math.min(dtList.size(), limit);
        return dtList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
