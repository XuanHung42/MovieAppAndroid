package groups.project.movieappwithfirebase.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import groups.project.movieappwithfirebase.R;
import groups.project.movieappwithfirebase.adapter.FeaturedAdapter;
import groups.project.movieappwithfirebase.adapter.SeriesAdapter;
import groups.project.movieappwithfirebase.adapter.SlideAdapter;
import groups.project.movieappwithfirebase.model.DataModel;
import groups.project.movieappwithfirebase.model.FeaturedModel;
import groups.project.movieappwithfirebase.model.SeriesModel;

public class HomeFragment extends Fragment {

FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference myRef = database.getReference();
    private List<DataModel> dataModels;
    private List<FeaturedModel> featuredModels;
    private List<SeriesModel> seriesModels;
    private SlideAdapter slideAdapter;
    private RecyclerView featuredRecyclerView, web_series_recycler_view;
    private FeaturedAdapter featuredAdapter;
    private SeriesAdapter seriesAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        SliderView sliderView = (SliderView) view.findViewById(R.id.sliderView);
        slideAdapter = new SlideAdapter(getActivity());
        sliderView.setSliderAdapter(slideAdapter);
        sliderView.setSliderAdapter(slideAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        reNewItems(sliderView);

        // load banner slider
        loadFirebaseForSlide();

        DatabaseReference FRef = database.getReference("featured");
        featuredRecyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        featuredRecyclerView.setLayoutManager(layoutManager);
        featuredModels = new ArrayList<>();
        featuredAdapter = new FeaturedAdapter(featuredModels);
        featuredRecyclerView.setAdapter(featuredAdapter);

        FRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot contentSnapShot : snapshot.getChildren()) {
                    FeaturedModel dataModel = contentSnapShot.getValue(FeaturedModel.class);
                    featuredModels.add(dataModel);
                }
                featuredAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference SRef = database.getReference("series");
        web_series_recycler_view = (RecyclerView) view.findViewById(R.id.web_series_recycler_view);
        LinearLayoutManager layoutManagerS = new LinearLayoutManager(getActivity());
        layoutManagerS.setOrientation(RecyclerView.HORIZONTAL);
        layoutManagerS.setReverseLayout(true);
        layoutManagerS.setStackFromEnd(true);
        web_series_recycler_view.setLayoutManager(layoutManagerS);

        seriesModels = new ArrayList<>();
        seriesAdapter = new SeriesAdapter(seriesModels);
        web_series_recycler_view.setAdapter(seriesAdapter);
        SRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot contentSnapShot : snapshot.getChildren()) {
                    SeriesModel newSeriesModel = contentSnapShot.getValue(SeriesModel.class);
                    seriesModels.add(newSeriesModel);
                }
                seriesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
return  view;

    }
    private void loadFirebaseForSlide() {

        myRef.child("Trailer").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot contentSlider : dataSnapshot.getChildren()) {
                    DataModel sliderItem = contentSlider.getValue(DataModel.class);
                    dataModels.add(sliderItem);
                }
                slideAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }

//    private void loadFeaturedData() {
//
//
//        DatabaseReference FRef = database.getReference("featured");
//        featuredRecyclerView = findViewById(R.id.recyclerView);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
//
//        layoutManager.setReverseLayout(true);
//        layoutManager.setStackFromEnd(true);
//        featuredRecyclerView.setLayoutManager(layoutManager);
//        featuredModels = new ArrayList<>();
//        featuredAdapter = new FeaturedAdapter(featuredModels);
//        featuredRecyclerView.setAdapter(featuredAdapter);
//
//        FRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot contentSnapShot : snapshot.getChildren()) {
//                    FeaturedModel dataModel = contentSnapShot.getValue(FeaturedModel.class);
//                    featuredModels.add(dataModel);
//                }
//                featuredAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        loadSeriesData();
//        loadFeaturedData();
//
//    }
//
//    private void loadSeriesData() {
//        DatabaseReference SRef = database.getReference("series");
//        web_series_recycler_view = (RecyclerView) findViewById(R.id.web_series_recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
//
//        layoutManager.setReverseLayout(true);
//        layoutManager.setStackFromEnd(true);
//        web_series_recycler_view.setLayoutManager(layoutManager);
//
//        seriesModels = new ArrayList<>();
//        seriesAdapter = new SeriesAdapter(seriesModels);
//        web_series_recycler_view.setAdapter(seriesAdapter);
//        SRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot contentSnapShot : snapshot.getChildren()) {
//                    SeriesModel newSeriesModel = contentSnapShot.getValue(SeriesModel.class);
//                    seriesModels.add(newSeriesModel);
//                }
//                seriesAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    private void reNewItems(View view) {
        dataModels = new ArrayList<>();
        DataModel dataItems = new DataModel();
        dataModels.add(dataItems);
        slideAdapter.reNewItems(dataModels);
        slideAdapter.deleteItems(0);

    }
}