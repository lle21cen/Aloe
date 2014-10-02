package com.aloes.ssu.timetable;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ListAdapter extends BaseAdapter {

	ArrayList<ListItem> items = new ArrayList<ListItem>();
	Context context;
	
	public ListAdapter(Context context){
		this.context=context;
	}
	
	public void addItem(String dDay, String subjectName){
		items.add(new ListItem(dDay, subjectName));
	}
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void clearItem(){
		items.clear();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ListItemView alist;
		 if (convertView == null) {
			 alist = new ListItemView(context, items.get(position));
	        } else {
	        	alist = (ListItemView) convertView;
	    
	        	alist.setText(items.get(position).getdDay(), items.get(position).getSubjectName());
	        }
		
		return alist;
	}

}
