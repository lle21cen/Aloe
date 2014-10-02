package com.aloes.ssu.timetable;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aloes.ssu.R;


public class addActivity extends Activity {

	RelativeLayout layout;

	final static int GO_MENU = 0;
	final static int GWAJE = 1;
	final static int SIHUM = 2;
	final static int HUGANG = 3;
	final static int BOGANG = 4;
	final static int BYUNGYUNG = 5;
	final static int ADD_CLASS = 6;

	private String str;
	private int dYear;
	private int dMonth;
	private int dDay;

	public String getStr() {
		return str;
	}

	public int getdYear() {
		return dYear;
	}

	public int getdMonth() {
		return dMonth;
	}

	public int getdDay() {
		return dDay;
	}

	public void setdDay(int dDay) {
		this.dDay = dDay;
	}

	TextView dday;
	TextView display;

	static final int DATE_DIALOG_ID = 0;
	String ddaystr = "D - Day";
	String gwanamestr = "";

	SQLiteDatabase database;
	String tablename = "assignTable";

//	
//	@Override
//	protected void onResume() {
//		super.onResume();
//		
//		layout = (RelativeLayout) findViewById(R.id.layout);
//		dday = (TextView) findViewById(R.id.dday);
//		display = (TextView) findViewById(R.id.display);
//		Button btn = (Button) findViewById(R.id.btn);
//
//		Createdatabase();
//
//		Cursor cursor = null;
//		try {
//			String sql = "select gwamok, dYear, dMonth, dDay from " + tablename
//					+ " where dYear > 2013";
//			cursor = database.rawQuery(sql, null);
//			
//		} catch (Exception e) {
//
//		}
//
//		if (cursor != null) {
//			int count = cursor.getCount();
//			for (int i = 0; i < count; i++) {
//				cursor.moveToNext();
//				if (getdDay(cursor.getInt(1), cursor.getInt(2),
//						cursor.getInt(3)) >= 0) {
//					ddaystr = ddaystr
//							+ "\n"
//							+ "D - "
//							+ getdDay(cursor.getInt(1), cursor.getInt(2),
//									cursor.getInt(3));
//					gwanamestr = gwanamestr + "\n" + cursor.getString(0) + "("
//							+ cursor.getInt(2) + "/" + cursor.getInt(3) + ")";
//				}
//			}
//		}
//		dday.setText(ddaystr);
//		display.setText(gwanamestr);
//		btn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				layout.setVisibility(View.INVISIBLE);
//			}
//		});
//		Button btn2 = (Button) findViewById(R.id.btn2);
//		Button btn3 = (Button) findViewById(R.id.btn3);
//		btn3.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				finish();
//			}
//		});
//		Button btn4 = (Button) findViewById(R.id.btn4);
//		btn4.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(addActivity.this, AddClass.class);
//				startActivityForResult(intent, ADD_CLASS);
//			}
//		});
//
//	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.t_activity_add);
		layout = (RelativeLayout) findViewById(R.id.layout);
		dday = (TextView) findViewById(R.id.dday);
		display = (TextView) findViewById(R.id.display);
		Button btn = (Button) findViewById(R.id.btn);

		Createdatabase();

		Cursor cursor = null;
		try {
			String sql = "select gwamok, dYear, dMonth, dDay from " + tablename
					+ " where dYear > 2013";
			cursor = database.rawQuery(sql, null);
			
		} catch (Exception e) {

		}

		if (cursor != null) {
			int count = cursor.getCount();
			for (int i = 0; i < count; i++) {
				cursor.moveToNext();
				if (getdDay(cursor.getInt(1), cursor.getInt(2),
						cursor.getInt(3)) >= 0) {
					ddaystr = ddaystr
							+ "\n"
							+ "D - "
							+ getdDay(cursor.getInt(1), cursor.getInt(2),
									cursor.getInt(3));
					gwanamestr = gwanamestr + "\n" + cursor.getString(0) + "("
							+ cursor.getInt(2) + "/" + cursor.getInt(3) + ")";
				}
			}
		}
		dday.setText(ddaystr);
		display.setText(gwanamestr);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				layout.setVisibility(View.INVISIBLE);
			}
		});
		Button btn2 = (Button) findViewById(R.id.btn2);
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
	//			Intent intent = new Intent(addActivity.this, SelectScreen.class);
	//			startActivityForResult(intent, GO_MENU);
			}
		});
		Button btn3 = (Button) findViewById(R.id.btn3);
		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		Button btn4 = (Button) findViewById(R.id.btn4);
		btn4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(addActivity.this, AddClass.class);
				startActivityForResult(intent, ADD_CLASS);
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == 1) {
			str = String.format("%s", data.getStringExtra("name"));
			dYear = 1; // ? ì™?™å ?™ì˜™ ? ì™?™å ?™ì˜™? ì™??? ì™?™å ?™ì˜™
			dMonth = 1;
			dDay = 1;
			dYear = Integer.parseInt(data.getStringExtra("year"));
			dMonth = Integer.parseInt(data.getStringExtra("month"));
			dDay = Integer.parseInt(data.getStringExtra("day"));

			database.beginTransaction();
			try {
				String sql = "insert into " + tablename
						+ " (gwamok, dYear, dMonth, dDay) values('" + str
						+ "', " + dYear + ", " + dMonth + ", " + dDay + ")";
				database.execSQL(sql);
				database.setTransactionSuccessful();
			} catch (Exception e) {

			} finally {
				database.endTransaction();
			}
			int a = getdDay(dYear, dMonth, dDay);
			if (a >= 0)
				dday.append("\nD - " + a);
			else
				dday.append("\nD + " + -a);
			display.append("\n" + str + "(" + dMonth + "/" + dDay + ")");
		}
	}

	public void Createdatabase() {
		String name = "database.db";
		database = openOrCreateDatabase(name, MODE_WORLD_WRITEABLE, null);

		try {
			String sql = "create table "
					+ tablename
					+ " (gwamok text, dYear integer, dMonth integer, dDay integer)";
			database.execSQL(sql);
		} catch (Exception e) {

		}
	}

	public int getdDay(int y, int m, int da) {
		long d;
		long t;
		long r;
		int dYear = y; // ? ì™?™å ?™ì˜™ ? ì™?™å ?™ì˜™? ì™??? ì™?™å ?™ì˜™
		int dMonth = m;
		int dDay = da;
		int resultNumber = 0;
		Calendar calendar = Calendar.getInstance(); // ? ì™?™å ?™ì˜™ ? ì™?™ì§œ ? ì?ë¤„ì˜™? ì™??
		Calendar dCalendar = Calendar.getInstance();
		dCalendar.set(dYear, dMonth, dDay);
		t = calendar.getTimeInMillis(); // ? ì™?™å ?™ì˜™ ? ì™?™ì§œ? ì™??? ì‹»ëªŒì˜™?? ?™ì˜™? ì™?™å ?™ì˜™ ? ìŒ•?ì˜™
		d = dCalendar.getTimeInMillis(); // ? ì™?™å ?±ë†‚?™ì§œ? ì™??? ì‹»ëªŒì˜™?? ?™ì˜™? ì™?™å ?™ì˜™ ? ìŒ•?ì˜™
		r = (d - t) / (24 * 60 * 60 * 1000); // ? ì™?™å ?™ì˜™ ? ì™?™ì§œ? ì™?™å ?™ì˜™ ? ì™?™å ?™ì˜™ ? ì™?™ì§œ? ì™??? ì™??? ì™?™å ?™ì˜™
												// '? ì™??? ì™?™å ?™ì˜™? ì™??? ìŒ•?ì˜™
		resultNumber = (int) r;
		return resultNumber;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}