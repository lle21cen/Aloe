package com.aloes.ssu.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aloes.ssu.R;

public class subjectListItemView extends RelativeLayout {

	Context context;

	ImageView aicon;
	TextView subjectName, profName, grade, who;

	public subjectListItemView(Context context, subjectListItem aItem) {
		super(context);
		this.context = context;

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.t_addsubject_list_item, this, true);

		aicon = (ImageView) findViewById(R.id.icon);
		aicon.setImageDrawable(aItem.getIcon());

		subjectName = (TextView) findViewById(R.id.subjectName);
		subjectName.setText(aItem.getSubjectName());

		profName = (TextView) findViewById(R.id.professorName);
		profName.setText(aItem.getProfName());
		
		grade = (TextView) findViewById(R.id.grade);
		grade.setText(aItem.getGrade());

		who= (TextView) findViewById(R.id.who);
		who.setText(aItem.getWho());

	}
	public void setGrade(String grade) {
		this.grade.setText(grade);
	}

	public void setSubjectName(String name) {
		subjectName.setText(name);
	}

	public void setProfName(String name, String mate) {
		if(mate == null)
			profName.setText(name);
		else
			profName.setText(name+mate);
	}
	public void setWho(String str) {
		who.setText(str);
	}


}
