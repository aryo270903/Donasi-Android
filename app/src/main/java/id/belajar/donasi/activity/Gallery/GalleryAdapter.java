package id.belajar.donasi.activity.Gallery;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import id.belajar.donasi.BuildConfig;
import id.belajar.donasi.R;
import id.belajar.donasi.activity.Berita.BeritaDetailActivity;
import id.belajar.donasi.entity.Gallery;

public class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Gallery> dtList = new ArrayList<>();
    private Activity activity;

    public GalleryAdapter(Activity activity,List<Gallery> dtList){
        this.activity = activity;
        this.dtList = dtList;
    }
    public GalleryAdapter(Activity activity){
        this.activity = activity;
    }
    public void setData(List<Gallery>data){
        this.dtList = data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_gallery_pager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Gallery dt = dtList.get(position);

        ImageView bennertv = holder.itemView.findViewById(R.id.bennertv);
//        TextView keterangantv = holder.itemView.findViewById(R.id.Keterangantv);
        RelativeLayout wrap = holder.itemView.findViewById(R.id.wrapGallery);

        wrap.setOnClickListener(v -> {
            Intent intent = new Intent(this.activity, GalleryDetailActivity.class);
            intent.putExtra("extra_gallery", new Gson().toJson(dt));
            this.activity.startActivity(intent);
        });

        Glide.with(activity)
                .load(BuildConfig.BASE_URL_IMAGE+dt.image)
                .into(bennertv);
//        keterangantv.setText(dt.keterangan);
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
