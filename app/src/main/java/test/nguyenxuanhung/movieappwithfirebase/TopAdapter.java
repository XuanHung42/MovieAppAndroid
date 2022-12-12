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
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.security.PublicKey;
import java.util.List;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.MyViewHolder>  {


    private List<TopModel> topModelList;
    public TopAdapter(List<TopModel> topModelList){
        this.topModelList = topModelList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_hot, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,@SuppressLint("RecyclerView") int position) {
        holder.textViewTitleTop.setText(topModelList.get(position).getTtitle());
        Glide.with(holder.imageViewTop.getContext()).load(topModelList.get(position).getTthumb()).into(holder.imageViewTop);
        // click vao itemview hien thi chi tiet view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendDataToDetailsActivity = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                sendDataToDetailsActivity.putExtra("title", topModelList.get(position).getTtitle());
                sendDataToDetailsActivity.putExtra("link", topModelList.get(position).getTlink());
                sendDataToDetailsActivity.putExtra("cover", topModelList.get(position).getTcover());
                sendDataToDetailsActivity.putExtra("thumb", topModelList.get(position).getTthumb());
                sendDataToDetailsActivity.putExtra("desc", topModelList.get(position).getTdesc());
                sendDataToDetailsActivity.putExtra("cast", topModelList.get(position).getTcast());
                sendDataToDetailsActivity.putExtra("t_link", topModelList.get(position).getTtlink());

                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)holder.itemView.getContext(), holder.imageViewTop, "imageMain");
                holder.imageViewTop.getContext().startActivity(sendDataToDetailsActivity, activityOptionsCompat.toBundle());

            }
        });
    }

    @Override
    public int getItemCount() {
        return topModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewTop;
        TextView textViewTitleTop;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
             imageViewTop = itemView.findViewById(R.id.imageView);
             textViewTitleTop = itemView.findViewById(R.id.movie_title);

        }
    }
}
