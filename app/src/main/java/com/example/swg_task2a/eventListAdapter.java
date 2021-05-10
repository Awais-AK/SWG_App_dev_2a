package com.example.swg_task2a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class eventListAdapter extends BaseAdapter {
    private ArrayList<Event> Events;
    Context ctx;

    public eventListAdapter(ArrayList<Event> events, Context ctx) {
        this.Events = events;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return Events.size();
    }
    @Override
    public Object getItem(int position) {
        return Events.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listview = convertView;

        if(listview==null){
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listview = inflater.inflate(R.layout.activity_notice_board,null);
        }

        TextView eventName = (TextView)listview.findViewById(R.id.eventInfo);
        TextView eventTime = (TextView)listview.findViewById(R.id.TimeEve);
        TextView eventDate = (TextView)listview.findViewById(R.id.Date);
        TextView description = (TextView)listview.findViewById(R.id.eventDes);

        final int n = Events.size();

        eventName.setText(Events.get(n-1-position).getEventName().toString());
        eventTime.setText(Events.get(n-1-position).getTime().toString());
        eventDate.setText(Events.get(n-1-position).getDate().toString());
        description.setText(Events.get(n-1-position).getDescription().toString());

        return listview;
    }

}
