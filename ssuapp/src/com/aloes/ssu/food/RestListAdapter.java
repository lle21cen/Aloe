package com.aloes.ssu.food;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class RestListAdapter extends BaseAdapter {

	private List<RestItems> items = new ArrayList<RestItems>();
	Context context;
	
	public RestListAdapter(Context context){
		this.context = context;
	}
	
	public void addItem(RestItems aItem){
		items.add(aItem);
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RestListView restaurant;
		if(convertView == null){
			restaurant = new RestListView(context, items.get(position));
		}else{
			restaurant = (RestListView) convertView;
			
			restaurant.setIcon(items.get(position).getImage());
			restaurant.setName(items.get(position).getName());
			restaurant.setTel(items.get(position).getTel());
		}
		return restaurant;
	}
}
