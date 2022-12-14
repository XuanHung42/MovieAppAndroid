package groups.project.movieappwithfirebase.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import groups.project.movieappwithfirebase.DetailsActivity;
import groups.project.movieappwithfirebase.R;
import groups.project.movieappwithfirebase.model.Coming22Model;

public class Coming22Adapter extends RecyclerView.Adapter<Coming22Adapter.MyViewHolder> {
 private List<Coming22Model> coming22List;

    public Coming22Adapter(List<Coming22Model> coming22List) {
        this.coming22List = coming22List;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(coming22List.get(position).getCtitle());
        Glide.with(holder.imageView.getContext()).load(coming22List.get(position).getCthumb()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendDataToDetailsActivity = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                sendDataToDetailsActivity.putExtra("title",coming22List.get(position).getCtitle());
                sendDataToDetailsActivity.putExtra("link",coming22List.get(position).getClink());
                sendDataToDetailsActivity.putExtra("cover",coming22List.get(position).getCcover());
                sendDataToDetailsActivity.putExtra("thumb",coming22List.get(position).getCthumb());
                sendDataToDetailsActivity.putExtra("desc",coming22List.get(position).getCdes());
                sendDataToDetailsActivity.putExtra("cast", coming22List.get(position).getCcast());
                sendDataToDetailsActivity.putExtra("t_link", coming22List.get(position).getTlink());

                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation((Activity)holder.itemView.getContext(), holder.imageView,
                                "imageMain");
                holder.imageView.getContext().startActivity(sendDataToDetailsActivity,activityOptionsCompat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return coming22List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
