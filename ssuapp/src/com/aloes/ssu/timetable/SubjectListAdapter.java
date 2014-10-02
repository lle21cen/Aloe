package com.aloes.ssu.timetable;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SubjectListAdapter extends BaseAdapter {

	ArrayList<subjectListItem> items = new ArrayList<subjectListItem>();
	Context context;

	public SubjectListAdapter(Context mContext) {
		context = mContext;
	}

	public void addItem(subjectListItem aItem) {
		items.add(aItem);
	}

	public void clearItem(){
		items.clear();
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

	public String getItemName(int position){
		return items.get(position).getSubjectName();
	}
	
	public String getItemTime(int position){
		return items.get(position).getTime();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		subjectListItemView aListViewWithItem;
		if (convertView == null) {
			aListViewWithItem = new subjectListItemView(context,
					items.get(position));
		} else {
			aListViewWithItem = (subjectListItemView) convertView;
			
			aListViewWithItem.setSubjectName(items.get(position).getSubjectName());
			aListViewWithItem.setProfName(items.get(position).getProfName(), items.get(position).getMate());
			aListViewWithItem.setGrade(items.get(position).getGrade());
			aListViewWithItem.setWho(items.get(position).getWho());
			
		}

		return aListViewWithItem;
	}
}
