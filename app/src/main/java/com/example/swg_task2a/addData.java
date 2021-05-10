package com.example.swg_task2a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addData extends AppCompatActivity {

    EditText eventName;
    EditText eventDescription;
    EditText eventDate;
    EditText eventTime;
    Button btnPostEvent;

    DatabaseReference databaseEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        databaseEvents = FirebaseDatabase.getInstance().getReference("events");

        eventName = (EditText) findViewById(R.id.eventName);
        eventDescription = (EditText) findViewById(R.id.eventDescription);
        eventDate = (EditText) findViewById(R.id.eventDate);
        eventTime = (EditText) findViewById(R.id.eventTime);
        btnPostEvent = (Button) findViewById(R.id.btnPostEvent);

        btnPostEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postEvent();
            }
        });

    }
    private void postEvent(){
        String event = eventName.getText().toString().trim();
        String description = eventDescription.getText().toString().trim();
        String date = eventDate.getText().toString().trim();
        String time = eventTime.getText().toString().trim();

        if(!TextUtils.isEmpty(event)){
            String id = databaseEvents.push().getKey();
            Event newEvent = new Event(id, event, description,date, time);
            databaseEvents.child(id).setValue(newEvent);
            Toast.makeText(this, "Event Added", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Enter the event name", Toast.LENGTH_LONG).show();
        }
    }

}