package com.example.chris.year_4_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Chris on 26/01/2015.
 */
public class ReservationsCustomListAdapter extends BaseAdapter
{
    private ArrayList reservationsListData;
    private LayoutInflater layoutInfl;

    public ReservationsCustomListAdapter(Context theContext, ArrayList theListData)
    {
        reservationsListData = theListData;
        layoutInfl = LayoutInflater.from(theContext);
    }

    @Override
    public int getCount()
    {
        return reservationsListData.size();
    }

    @Override
    public Object getItem(int position)
    {
        return reservationsListData.get(position);
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
            convertView = layoutInfl.inflate(R.layout.reservations_list_row_layout, null);
            holder = new ViewHolder();
            holder.eventReservationView = (TextView) convertView.findViewById(R.id.eventReservation);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.eventReservationView.setText(((ReservationItem) reservationsListData.get(position)).getEventReservation());

        return convertView;
    }

    static class ViewHolder
    {
        TextView eventReservationView;
    }
}