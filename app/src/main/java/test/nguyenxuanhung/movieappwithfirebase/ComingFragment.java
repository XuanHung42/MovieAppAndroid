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

public class ComingFragment extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private List<DataModel> dataModels;


    private SlideAdapter slideAdapter;
    private RecyclerView coming22RecyclerView, coming23RecyclerView;


    private  List<Coming22Model> coming22Models;
    private  Coming22Adapter coming22Adapter;
    private  Coming23Adapter coming23Adapter;
    private List<Coming23Model> coming23Models;

    public ComingFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coming, container, false);

        SliderView sliderView = (SliderView) view.findViewById(R.id.sliderView);
        slideAdapter = new SlideAdapter(getActivity());
        sliderView.setSliderAdapter(slideAdapter);
        sliderView.setSliderAdapter(slideAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setScrollTimeInSec(5);
        reNewItems(sliderView);
        loadFirebaseForSlide();

        DatabaseReference FRef = database.getReference("Coming2022");
        coming22RecyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        coming22RecyclerView.setLayoutManager(layoutManager);
        coming22Models = new ArrayList<>();
        coming22Adapter = new Coming22Adapter(coming22Models);
        coming22RecyclerView.setAdapter(coming22Adapter);

        FRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot contentSnapShot : snapshot.getChildren()) {
                    Coming22Model dataModel = contentSnapShot.getValue(Coming22Model.class);
                    coming22Models.add(dataModel);
                }
                coming22Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference SRef = database.getReference("Coming2023");
        coming23RecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView23);
        LinearLayoutManager layoutManagerS = new LinearLayoutManager(getActivity());
        layoutManagerS.setOrientation(RecyclerView.HORIZONTAL);
        layoutManagerS.setReverseLayout(true);
        layoutManagerS.setStackFromEnd(true);
        coming23RecyclerView.setLayoutManager(layoutManagerS);

        coming23Models = new ArrayList<>();
        coming23Adapter = new Coming23Adapter(coming23Models);
        coming23RecyclerView.setAdapter(coming23Adapter);
        SRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot contentSnapShot : snapshot.getChildren()) {
                    Coming23Model coming23Model = contentSnapShot.getValue(Coming23Model.class);
                    coming23Models.add(coming23Model);
                }
                coming23Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    private void reNewItems(View view) {
        dataModels = new ArrayList<>();
        DataModel dataItems = new DataModel();
        dataModels.add(dataItems);
        slideAdapter.reNewItems(dataModels);
        slideAdapter.deleteItems(0);

    }

    private void loadFirebaseForSlide() {

            myRef.child("Comming").addListenerForSingleValueEvent(new ValueEventListener() {
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
}