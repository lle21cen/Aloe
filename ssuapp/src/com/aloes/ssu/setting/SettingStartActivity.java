package com.aloes.ssu.setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aloes.ssu.Firstck;
import com.aloes.ssu.R;
import com.aloes.ssu.campusmap.MapStartActivity;
import com.aloes.ssu.food.MainActivity;
import com.aloes.ssu.schoolinfo.SchoolinfoActivity;
import com.aloes.ssu.timetable.DatabaseHelper;
import com.aloes.ssu.timetable.TimetableMain;

public class SettingStartActivity extends Activity {

	Button infoSetBtn;
	TextView infoNowSet;

	SharedPreferences pref;
	SharedPreferences.Editor editor;

	Button deleteTimeTable;

	DatabaseHelper helper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_activity);

		Firstck fr = new Firstck(this);
		helper = new DatabaseHelper(this, "database.db", null, 1);
		db = helper.getWritableDatabase();

		infoSetBtn = (Button) findViewById(R.id.infoSetBtn);
		infoNowSet = (TextView) findViewById(R.id.infoNowSet);

		pref = getSharedPreferences("pref", MODE_PRIVATE);
		editor = pref.edit();

		if(pref.getString("GCM setting", "현재 설정").equals("Von"))
		{
			
			infoNowSet.setText("진동알람");
		}
		else if(pref.getString("GCM setting", "현재 설정").equals("Voff")){
			infoNowSet.setText("무음알람");
		}
		else if(pref.getString("GCM setting", "현재 설정").equals("off")){
			infoNowSet.setText("알람끄기");
		}


		infoSetBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				GCMSettingDialog();

			}
		});

		deleteTimeTable = (Button) findViewById(R.id.timetableSetBtn);
		deleteTimeTable.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				deleteSettingDialog();
			}
		});

		Button deleteTask = (Button) findViewById(R.id.timeBreakSetBtn);
		deleteTask.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				deleteTaskDialog();
			}
		});
		// 아래 메뉴버튼 클릭
		final Button foot1 = (Button) findViewById(R.id.settingbot1);
		foot1.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					foot1.setBackgroundResource(R.drawable.settingcolbot1);// 왜
																			// 사진을
																			// 못읽을까;;;;;;
					break;
				case MotionEvent.ACTION_UP:
					foot1.setBackgroundResource(R.drawable.settingbot1);
					break;
				}
				return false;
			}
		});

		final Button foot2 = (Button) findViewById(R.id.settingbot2);
		foot2.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					foot2.setBackgroundResource(R.drawable.settingcolbot2);
					break;
				case MotionEvent.ACTION_UP:
					foot2.setBackgroundResource(R.drawable.settingbot2);
					break;
				}
				return false;
			}
		});

		foot1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(),
						TimetableMain.class);
				startActivityForResult(intent, 1003);
				finish();
			}
		});

		foot2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(),
						SchoolinfoActivity.class);
				startActivityForResult(intent, 1003);
				finish();
			}
		});

		final Button foot3 = (Button) findViewById(R.id.settingbot3);
		foot3.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					foot3.setBackgroundResource(R.drawable.settingcolbot3);
					break;
				case MotionEvent.ACTION_UP:
					foot3.setBackgroundResource(R.drawable.settingbot3);
					break;
				}
				return false;
			}
		});
		foot3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), MainActivity.class);
				startActivityForResult(intent, 1003);
				finish();
			}
		});
		final Button foot4 = (Button) findViewById(R.id.settingbot4);
		foot4.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					foot4.setBackgroundResource(R.drawable.settingcolbot4);
					break;
				case MotionEvent.ACTION_UP:
					foot4.setBackgroundResource(R.drawable.settingbot4);
					break;
				}
				return false;
			}
		});
		foot4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(),
						MapStartActivity.class);
				startActivityForResult(intent, 1003);
				finish();

			}
		});
		final Button foot5 = (Button) findViewById(R.id.settingbot5);
		foot5.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					foot5.setBackgroundResource(R.drawable.settingcolbot5);
					break;
				case MotionEvent.ACTION_UP:
					foot5.setBackgroundResource(R.drawable.settingbot5);
					break;
				}
				return false;
			}
		});

		foot5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(),
						SettingStartActivity.class);
				startActivityForResult(intent, 1003);
				finish();

			}
		});

	}

	private void GCMSettingDialog() {
		final String items[] = { "진동알림", "무음알림", "알람끄기" };
		AlertDialog.Builder ab = new AlertDialog.Builder(
				SettingStartActivity.this);
		ab.setTitle("학사정보알람설정");
		ab.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

				switch (whichButton) {
				case 0:

					editor.putString("GCM setting", "Von");
					editor.commit();
					infoNowSet.setText("진동알람");
					break;
				case 1:
					editor.putString("GCM setting", "Voff");
					editor.commit();
					infoNowSet.setText("무음알람");
					break;
				case 2:
					editor.putString("GCM setting", "off");
					editor.commit();
					infoNowSet.setText("알람끄기");
				default:
					break;
				}
			}

		});
		ab.show();

	}

	private void deleteSettingDialog() {
		final String items[] = { "아니오", "예" };
		AlertDialog.Builder ab = new AlertDialog.Builder(
				SettingStartActivity.this);
		ab.setTitle("정말 삭제 하시겠습니까?");
		ab.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

				switch (whichButton) {
				case 0:
					break;
				case 1:
					String deleteSql = "DELETE FROM timetable";
					String deleteDropTable = "DELETE FROM droptable";
					db.execSQL(deleteSql);
					db.execSQL(deleteDropTable);
					Toast.makeText(getApplicationContext(), "삭제되었습니다",
							Toast.LENGTH_LONG).show();
					break;
				default:
					break;
				}
			}

		});
		ab.show();
	}

	private void deleteTaskDialog() {
		final String items[] = { "아니오", "예" };
		AlertDialog.Builder ab = new AlertDialog.Builder(
				SettingStartActivity.this);
		ab.setTitle("정말 삭제 하시겠습니까?");
		ab.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

				switch (whichButton) {
				case 0:
					break;
				case 1:
					String deleteSql = "DELETE FROM assignTable";
					db.execSQL(deleteSql);
					Toast.makeText(getApplicationContext(), "삭제되었습니다",
							Toast.LENGTH_LONG).show();
					break;
				default:
					break;
				}
			}

		});
		ab.show();
	}
}
