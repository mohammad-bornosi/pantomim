package com.e.adabazi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class GroupListadaptor extends BaseAdapter {
    Context context;
    List<Group>groupList;
    public GroupListadaptor(Context context, List<Group> groupList) {
        this.context = context;
        this.groupList= groupList;
    }
    @Override
    public int getCount() {
        return groupList.size();
    }
    @Override
    public Object getItem(int position) {
        return groupList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void update(List<Group> list) {
        this.groupList = list;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.group,parent,false);
        }
        TextView group_name=convertView.findViewById(R.id.btn_group_name);
        TextView group_point=convertView.findViewById(R.id.btn_group_point);
        group_name.setText(String.valueOf(groupList.get(position).getName()));
        group_point.setText(String.valueOf(groupList.get(position).getPoint()));
        return convertView;
    }
}
