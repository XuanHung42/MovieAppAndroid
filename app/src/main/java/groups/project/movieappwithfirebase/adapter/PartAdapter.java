package groups.project.movieappwithfirebase.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import groups.project.movieappwithfirebase.activity.PlayerActivity;
import groups.project.movieappwithfirebase.R;
import groups.project.movieappwithfirebase.model.PartModel;

public class PartAdapter extends RecyclerView.Adapter<PartAdapter.MyViewHolder> {
    private List<PartModel> models;

    public PartAdapter(List<PartModel> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.part_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.part_name.setText(models.get(position).getPart());
        Glide.with(holder.itemView).load(models.get(position).getUrl()).into(holder.part_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PlayerActivity.class);
                intent.putExtra("title", models.get(position).getPart());
                intent.putExtra("vid", models.get(position).getVidUrl());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView part_image;
        TextView part_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            part_image = itemView.findViewById(R.id.part_image);
            part_name = itemView.findViewById(R.id.part_name);
        }
    }
}
