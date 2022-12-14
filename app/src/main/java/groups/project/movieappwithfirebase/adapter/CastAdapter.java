package groups.project.movieappwithfirebase.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import groups.project.movieappwithfirebase.R;
import groups.project.movieappwithfirebase.model.CastModel;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.MyViewHodler> {
    private List<CastModel> castModels;

    public CastAdapter(List<CastModel> castModels) {
        this.castModels = castModels;
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_item, parent, false);

        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        holder.cast_name.setText(castModels.get(position).getCname());
        Glide.with(holder.itemView).load(castModels.get(position).getCurl()).into(holder.cast_image);
    }

    @Override
    public int getItemCount() {
        return castModels.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {
        ImageView cast_image;
        TextView cast_name;
        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            cast_image = itemView.findViewById(R.id.img_cast);
            cast_name = itemView.findViewById(R.id.cast_name);
        }
    }
}
