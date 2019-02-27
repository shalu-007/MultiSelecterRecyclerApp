package com.example.multiselecterrecyclerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView vRecyclerView=findViewById(R.id.mRecycler);
        ArrayList<SingleItem> vSingleItems=new ArrayList<>();
        for(int i=0;i<50;i++){
            vSingleItems.add(new SingleItem("Item number :"+(i+1),"This is item number"+(i+1)));
        }
        TextAdapter vTextAdapter=new TextAdapter(vSingleItems);
        vRecyclerView.setAdapter(vTextAdapter);
        vRecyclerView.setItemAnimator(new DefaultItemAnimator());
        vRecyclerView.setLayoutManager( new LinearLayoutManager(this));
    }
}
