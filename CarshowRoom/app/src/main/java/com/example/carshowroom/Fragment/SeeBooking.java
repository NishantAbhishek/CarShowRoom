package com.example.carshowroom.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carshowroom.Adapter.CarBoughtAdapter;
import com.example.carshowroom.Models.CarBought;
import com.example.carshowroom.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SeeBooking extends Fragment {
    private List<CarBought> carBoughts;
    private CarBoughtAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_see_booking, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        carBoughts = new ArrayList<>();
        addingBoughtCars();
        adapter = new CarBoughtAdapter(carBoughts,getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void addingBoughtCars(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Buyers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    CarBought carBuyer = snapshot.getValue(CarBought.class);
                    carBoughts.add(carBuyer);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }
}
