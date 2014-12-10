package com.example.chris.year_4_project;

/**
 * Created by Chris on 04/11/2014.
 */
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter
{
    private ArrayList eventListData;
    private LayoutInflater layoutInfl;

    public CustomListAdapter(Context theContext, ArrayList theListData)
    {
        eventListData = theListData;
        layoutInfl = LayoutInflater.from(theContext);
    }

    @Override
    public int getCount()
    {
        return eventListData.size();
    }

    @Override
    public Object getItem(int position)
    {
        return eventListData.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if(convertView == null)
        {
            convertView = layoutInfl.inflate(R.layout.event_list_row_layout, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.eventName);
            holder.venueView = (TextView) convertView.findViewById(R.id.eventVenue);
            holder.dateView = (TextView) convertView.findViewById(R.id.eventDate);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameView.setText(((EventItem) eventListData.get(position)).getEventName());
        holder.venueView.setText( "At " + ((EventItem) eventListData.get(position)).getEventVenue());
        holder.dateView.setText(((EventItem) eventListData.get(position)).getEventDate());

        return convertView;
    }

    static class ViewHolder
    {
        TextView nameView;
        TextView venueView;
        TextView dateView;

    }
}
