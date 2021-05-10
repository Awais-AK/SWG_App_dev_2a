package com.example.swg_task2a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoticeBoard extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);
        event = new Event();
        listView = (ListView) findViewById(R.id.listView);
        databaseReference = FirebaseDatabase.getInstance().getReference("events");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.event_card, R.id.eventInfo, list);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    event = ds.getValue(Event.class);
                    list.add(event.getEventName().toString()+" ");
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
//
//listView = (ListView) findViewById(R.id.listView);
//
//final ArrayList<Event> list = new ArrayList<>();
//final eventListAdapter adapter = new eventListAdapter(list, this);
//        listView.setAdapter(adapter);
//
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("events");
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot snapshot) {
//        list.clear();
//        for(DataSnapshot ds: snapshot.getChildren()){
//        Event newEvent = new Event(ds.child("eventId").getValue(String.class),
//        ds.child("eventName").getValue(String.class),
//        ds.child("description").getValue(String.class),
//        ds.child("date").getValue(String.class),
//        ds.child("time").getValue(String.class));
//        list.add(newEvent);
//        }
//        adapter.notifyDataSetChanged();
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError error) {
//
//        }
//        });