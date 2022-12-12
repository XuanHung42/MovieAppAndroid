package test.nguyenxuanhung.movieappwithfirebase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.List;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.MyViewHolder> {
    private List<FeaturedModel> dataModels;

    public FeaturedAdapter(List<FeaturedModel> dataModels) {
        this.dataModels = dataModels;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(dataModels.get(position).getFtitle());
        Glide.with(holder.itemView.getContext()).load(dataModels.get(position).getFthumb()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // khi click vao movie item se gui du lieu toi details activity qua Intent
                Intent sendDataToDetailsActivity = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                sendDataToDetailsActivity.putExtra("title",dataModels.get(position).getFtitle());
                sendDataToDetailsActivity.putExtra("link",dataModels.get(position).getFlink());
                sendDataToDetailsActivity.putExtra("cover",dataModels.get(position).getFcover());
                sendDataToDetailsActivity.putExtra("thumb",dataModels.get(position).getFthumb());
                sendDataToDetailsActivity.putExtra("desc",dataModels.get(position).getFdes());
                sendDataToDetailsActivity.putExtra("cast",dataModels.get(position).getFcast());
                sendDataToDetailsActivity.putExtra("t_link",dataModels.get(position).getTlink());

                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity)holder.itemView.getContext(), holder.imageView,
                                "imageMain");

                holder.imageView.getContext().startActivity(sendDataToDetailsActivity,activityOptionsCompat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.movie_title);
        }
    }
}
