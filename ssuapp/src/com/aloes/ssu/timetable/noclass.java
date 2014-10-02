package com.aloes.ssu.timetable;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;

import com.aloes.ssu.R;

public class noclass extends Activity {

	int index = 0;
	Calendar c = Calendar.getInstance();
	int cyear = c.get(Calendar.YEAR);
	int cmonth = c.get(Calendar.MONTH);
	int cday = c.get(Calendar.DAY_OF_MONTH);
	int nomon;
	int noday;
	int id;
	SQLiteDatabase db;
	DatabaseHelper helper;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.t_noclass);
		helper = new DatabaseHelper(this, "database.db", null, 1);
		db = helper.getWritableDatabase();
		Intent i = getIntent();
		index = i.getExtras().getInt("index");
		int[] week = i.getExtras().getIntArray("week");
		String[] name = i.getStringArrayExtra("name");

		for (int j = 0; j < index; j++) {
			switch (week[j]) {
			case 1:
				name[j] = "(월) " + name[j];
				break;
			case 2:
				name[j] = "(화) " + name[j];
				break;
			case 3:
				name[j] = "(수) " + name[j];
				break;
			case 4:
				name[j] = "(목) " + name[j];
				break;
			case 5:
				name[j] = "(금) " + name[j];
				break;
			case 6:
				name[j] = "(토) " + name[j];
				break;
			}
		}

		ListView list = (ListView) findViewById(R.id.myclass);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, name);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new ListViewItemClickListener());
	}

	private void DialogDatePicker(int position) {
		Calendar c = Calendar.getInstance();
		int cyear = c.get(Calendar.YEAR);
		int cmonth = c.get(Calendar.MONTH);
		int cday = c.get(Calendar.DAY_OF_MONTH);
		id = position;

		DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
			// onDateSet method
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				nomon = monthOfYear + 1;
				String dated;
				if (nomon < 10)
					dated = "0" + nomon;
				else
					dated = nomon + "";
				noday = dayOfMonth;
				String nodaystr = "" + noday;
				if (noday < 10)
					nodaystr = "0" + noday;
				String date = dated + "/" + nodaystr;

				db.execSQL("insert into droptable (date, id) values ('" + date
						+ "'," + id + ")");
				finish();
			}
		};
		DatePickerDialog alert = new DatePickerDialog(this, mDateSetListener,
				cyear, cmonth, cday);
		alert.show();
	}

	private class ListViewItemClickListener implements
			AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			DialogDatePicker(position);
		}
	}
}
