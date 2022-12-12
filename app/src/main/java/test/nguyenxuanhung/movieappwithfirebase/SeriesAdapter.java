package test.nguyenxuanhung.movieappwithfirebase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.MyViewHolder>
{
    private List<SeriesModel> seriesModelList;

    public SeriesAdapter(List<SeriesModel> seriesModelList) {
        this.seriesModelList = seriesModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);


        return new  MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(seriesModelList.get(position).getStitle());
        Glide.with(holder.imageView.getContext()).load(seriesModelList.get(position).getSthumb()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // khi click vao movie item se gui du lieu toi details activity qua Intent
                Intent sendDataToDetailsActivity = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                sendDataToDetailsActivity.putExtra("title",seriesModelList.get(position).getStitle());
                sendDataToDetailsActivity.putExtra("link",seriesModelList.get(position).getSlink());
                sendDataToDetailsActivity.putExtra("cover",seriesModelList.get(position).getScover());
                sendDataToDetailsActivity.putExtra("thumb",seriesModelList.get(position).getSthumb());
                sendDataToDetailsActivity.putExtra("desc",seriesModelList.get(position).getSdesc());
                sendDataToDetailsActivity.putExtra("cast",seriesModelList.get(position).getScast());
                sendDataToDetailsActivity.putExtra("t_link",seriesModelList.get(position).getTlink());

                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity)holder.itemView.getContext(), holder.imageView,
                                "imageMain");

                holder.imageView.getContext().startActivity(sendDataToDetailsActivity,activityOptionsCompat.toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return seriesModelList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
