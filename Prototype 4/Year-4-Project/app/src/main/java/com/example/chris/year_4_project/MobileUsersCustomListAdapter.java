package com.example.chris.year_4_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class MobileUsersCustomListAdapter extends BaseAdapter {

    private ArrayList mobileUserListData;
    private LayoutInflater layoutInfl;

    public MobileUsersCustomListAdapter(Context theContext, ArrayList theListData)
    {
        mobileUserListData = theListData;
        layoutInfl = LayoutInflater.from(theContext);
    }

    @Override
    public int getCount()
    {
        return mobileUserListData.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mobileUserListData.get(position);
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
            convertView = layoutInfl.inflate(R.layout.mobile_user_list_row_layout, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.mobileUserNameTxt);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameView.setText(((MobileUserItem) mobileUserListData.get(position)).getUsername());

        return convertView;
    }

    static class ViewHolder
    {
        TextView nameView;
    }
}
