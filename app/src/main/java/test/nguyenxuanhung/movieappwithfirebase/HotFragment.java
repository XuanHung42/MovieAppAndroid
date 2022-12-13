package test.nguyenxuanhung.movieappwithfirebase;

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


public class HotFragment extends Fragment {


    // connect db
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private List<DataModel> dataModels;
    private SlideAdapter slideAdapter;

    private List<FeaturedModel> featuredModels;
    private FeaturedAdapter featuredAdapter;

    private List<SeriesModel> seriesModels;
    private SeriesAdapter seriesAdapter;

    private List<TopModel> topModels;
    private TopAdapter topAdapter;

    private RecyclerView featuredRecyclerView, web_series_recycler_view, topRecyclerView;

    // constructor empty
    public HotFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        SliderView sliderView = (SliderView) view.findViewById(R.id.sliderView);

//        // slider
//        slideAdapter = new SlideAdapter(getActivity());
//        sliderView.setSliderAdapter(slideAdapter);
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
//        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
//        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
//        sliderView.setScrollTimeInSec(3);
//        sliderView.setAutoCycle(true);
//        reNewItem(sliderView);
//
////         load hot from database
////        loadFirebaseForSlide();


        DatabaseReference FRef = database.getReference("topday");
        topRecyclerView = view.findViewById(R.id.recyclerTopDayMovie);
        LinearLayoutManager layoutManagerT = new LinearLayoutManager(getActivity());

        layoutManagerT.setOrientation(RecyclerView.HORIZONTAL);

//        layoutManager.setReverseLayout(true);
//        layoutManager.setStackFromEnd(true);
        topRecyclerView.setLayoutManager(layoutManagerT);
        topModels = new ArrayList<TopModel>();
        topAdapter = new TopAdapter(topModels);
        topRecyclerView.setAdapter(topAdapter);

        FRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot contentSnapShot : snapshot.getChildren()) {
                    TopModel topModel = contentSnapShot.getValue(TopModel.class);
                    topModels.add(topModel);
                }
                topAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // top weeek
        DatabaseReference DRef = database.getReference("topweek");
        featuredRecyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManagerD = new LinearLayoutManager(getActivity());
        layoutManagerD.setOrientation(RecyclerView.HORIZONTAL);
//        layoutManager.setReverseLayout(true);
//        layoutManager.setStackFromEnd(true);
        featuredRecyclerView.setLayoutManager(layoutManagerD);
        featuredModels = new ArrayList<FeaturedModel>();
        featuredAdapter = new FeaturedAdapter(featuredModels);
        featuredRecyclerView.setAdapter(featuredAdapter);

        DRef.addValueEventListener(new ValueEventListener() {
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

        // top month
        DatabaseReference SRef = database.getReference("topmonth");
        web_series_recycler_view = (RecyclerView) view.findViewById(R.id.web_series_recycler_view);
        LinearLayoutManager layoutManagerTM = new LinearLayoutManager(getActivity());
        layoutManagerTM.setOrientation(RecyclerView.HORIZONTAL);
//        layoutManagerS.setReverseLayout(true);
//        layoutManagerS.setStackFromEnd(true);
        web_series_recycler_view.setLayoutManager(layoutManagerTM);
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


    // load show slide hot
    private void loadFirebaseForSlide() {
        myRef.child("hot").addListenerForSingleValueEvent(new ValueEventListener() {
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

    // renew item
    private void reNewItem(View view){
        dataModels = new ArrayList<>();
        DataModel dataItems = new DataModel();
        dataModels.add(dataItems);
        slideAdapter.reNewItems(dataModels);
        slideAdapter.deleteItems(0);
    }
}