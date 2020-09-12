package com.example.carshowroom.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carshowroom.Adapter.CarsAdapter;
import com.example.carshowroom.Models.Cars;
import com.example.carshowroom.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment
{
    private RecyclerView recyclerView;
    private CarsAdapter carsAdapter;
    private List<Cars> carlist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        carlist = new ArrayList<>();
        addingCars();
        carsAdapter = new CarsAdapter(getContext(),carlist);
        carsAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(carsAdapter);
        return view;
    }

    private void addingCars(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("CarsPosted");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Cars cars = snapshot.getValue(Cars.class);
                    carlist.add(cars);
                    carsAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        carsAdapter.notifyDataSetChanged();

    }
}
