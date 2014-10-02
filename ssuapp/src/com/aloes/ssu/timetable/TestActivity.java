package com.aloes.ssu.timetable;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.aloes.ssu.R;

public class TestActivity extends Activity {
	String time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.t_addactivity_test);

		try{
		Intent i = getIntent();
		time = i.getExtras().getString("time");
		String name = i.getExtras().getString("name");
		SQLiteDatabase db;
		DatabaseHelper helper;

		parse p = new parse();
		p.init(time);
		helper = new DatabaseHelper(this, "database.db", null, 1);
		db = helper.getWritableDatabase();

		Toast.makeText(getApplicationContext(), time+" "+name, Toast.LENGTH_SHORT).show();
		float starttime;
		starttime = (float) ((float) p.oneClass.startHour + (float) p.oneClass.startMinute / 60.0);
		float endtime = (float) ((float) p.oneClass.endHour + (float) p.oneClass.endMinute / 60.0);
		db.execSQL("insert into timetable (week, starttime, endtime, name, classroom, show) values ("
				+ p.oneClass.week
				+ ", "
				+ starttime
				+ ", "
				+ endtime
				+ ", '"
				+ name + "', '" + p.oneClass.classrome + "', 1)");
		Toast.makeText(getApplicationContext(), "추가되었습니다.", Toast.LENGTH_SHORT)
				.show();

		if (p.twoClass.startHour != 0) {
			float starttime2;
			starttime2 = (float) ((float) p.twoClass.startHour + (float) p.twoClass.startMinute / 60.0);
			float endtime2 = (float) ((float) p.twoClass.endHour + (float) p.twoClass.endMinute / 60.0);
			db.execSQL("insert into timetable (week, starttime, endtime, name, classroom, show) values ("
					+ p.twoClass.week
					+ ", "
					+ starttime2
					+ ", "
					+ endtime2
					+ ", '" + name + "', '" + p.twoClass.classrome + "', 1)");
		}
		finish();
		}
		catch(Exception e){
			Log.d("debug",e.getMessage());
		}
	}
}
