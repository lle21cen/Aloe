package com.aloes.ssu.timetable;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aloes.ssu.R;

public class ListItemView extends LinearLayout {

	private TextView dDay, subjectName;
	
	public ListItemView(Context context, ListItem aItem) {
		super(context);
		
	       // Layout Inflation
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.t_listitem, this, true);
        
		dDay=(TextView)findViewById(R.id.Dday);
		subjectName=(TextView)findViewById(R.id.subjectName);
		
		dDay.setText(aItem.getdDay());
		subjectName.setText(aItem.getSubjectName());
	}

	public void setText(String dDay, String subjectName){
		this.dDay.setText(dDay);
		this.subjectName.setText(subjectName);
	}
}
