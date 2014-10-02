package com.aloes.ssu.timetable;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aloes.ssu.BackPressCloseHandler;
import com.aloes.ssu.R;
import com.aloes.ssu.campusmap.MapStartActivity;
import com.aloes.ssu.schoolinfo.SchoolinfoActivity;
import com.aloes.ssu.setting.SettingStartActivity;

public class TimetableMain extends Activity {
	SQLiteDatabase db;
	SQLiteDatabase dropdb;
	String tableName = "timetable";
	DatabaseHelper helper;
	int[] week = new int[100];
	float[] starttime = new float[100];
	float[] endtime = new float[100];
	String[] name = new String[100];
	String[] classroom = new String[100];
	String[] professor = new String[100];
	int[] id = new int[100];
	int[] showup = new int[100];
	int[] color = new int[100];
	int i = 0;
	Date date = new Date();// 오늘에 날짜를 세팅 해준다.
	int year = date.getYear() + 1900;
	int mon = date.getMonth() + 1;
	int tdweek = Calendar.WEEK_OF_MONTH;
	private BackPressCloseHandler backPressCloseHandler;

	// 호신이 부분

	ListView list;

	Button upBtn;
	RelativeLayout listlayout;
	private boolean isOpen = false;
	private boolean tableCreated;
	SQLiteDatabase database;
	 SQLiteDatabase bodb;
	private final int REQUESTCODE_ADDACT = 1100;
	private final String DATABASE_NAME = "database.db";

	String tableNameas = "assignTable";
	Animation translateUp;
	Animation translateDown;

	ListAdapter adapter;

	// 호신이 부분 끝

	class Timetable extends View {

		public Timetable(Context context) {
			super(context);
		}

		private int calculateWidthFromFontSize(String testString,
				int currentSize) {
			Rect bounds = new Rect();
			Paint paint = new Paint();
			paint.setTextSize(currentSize);
			paint.getTextBounds(testString, 0, testString.length(), bounds);

			return (int) Math.ceil(bounds.width());
		}

		private int calculateHeightFromFontSize(String testString,
				int currentSize) {
			Rect bounds = new Rect();
			Paint paint = new Paint();
			paint.setTextSize(currentSize);
			paint.getTextBounds(testString, 0, testString.length(), bounds);

			return (int) Math.ceil(bounds.height());
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			int h = getHeight();
			int w = getWidth();
			System.out.println(h + "/" + w);
			Paint paint = new Paint();
			paint.setColor(Color.BLUE);
			for (int j = 0; j < i; j++) {

				Rect r1 = new Rect();
				if (showup[j] == 0)
					paint.setColor(0xffff0000);
				else {
					switch (color[j]) {
					case 0:
						paint.setColor(0xAAF08080);
						break;
					case 1:
						paint.setColor(0xAAF4A460);
						break;
					case 2:
						paint.setColor(0xAAFFDAB9);
						break;
					case 3:
						paint.setColor(0xAAADFF2F);
						break;
					case 4:
						paint.setColor(0xAA98FB98);
						break;
					case 5:
						paint.setColor(0xAA20B2AA);
						break;
					case 6:
						paint.setColor(0xAA87CEEB);
						break;
					case 7:
						paint.setColor(0xAA7B68EE);
						break;
					case 8:
						paint.setColor(0xAAC71585);
						break;
					case 9:
						paint.setColor(0xAAFFFACD);
						break;
					case 10:
						paint.setColor(0xAAFFFAFA);
						break;
					case 11:
						paint.setColor(0xAAF0FFF0);
						break;

					}
				}

				r1.set((w / 6) * week[j],
						(int) ((float) (h / 13) * (starttime[j] - (float) 8)),
						(w / 6) * week[j] + (w / 6),
						(int) ((float) (h / 13) * (endtime[j] - (float) 8)));
				canvas.drawRect(r1, paint);
				paint.setColor(Color.WHITE);
				paint.setTextSize(20);

				String str = name[j];
				int fontSize = 20;
				int lineHeight = 0;
				int yoffset = 0;
				int line2 = 0;
				int margin = 5;
				String[] lines = str.split("");

				lineHeight = (int) (calculateHeightFromFontSize(str, fontSize));
				String line = "";
				for (int i = 0; i < lines.length; ++i) {

					if (calculateWidthFromFontSize(line + lines[i], fontSize) <= r1
							.width() - margin * 2) {
						line = line + lines[i];

					} else {
						paint.setTextAlign(Align.CENTER);
						canvas.drawText(
								line,
								(w / 6) * week[j] + (w / 12),
								(((float) (h / 13) * (starttime[j] - (float) 8)) + ((float) (h / 13) * (endtime[j] - (float) 8)))
										/ 2 + yoffset, paint);
						paint.setTextAlign(Paint.Align.LEFT);
						yoffset = yoffset + lineHeight;
						line = lines[i];
						line2++;
					}
				}
				if (line2 < 2) {
					paint.setTextAlign(Align.CENTER);
					canvas.drawText(
							line,
							(w / 6) * week[j] + (w / 12),
							(((float) (h / 13) * (starttime[j] - (float) 8)) + ((float) (h / 13) * (endtime[j] - (float) 8)))
									/ 2 + yoffset, paint);
					yoffset = yoffset + lineHeight;
					paint.setTextAlign(Paint.Align.LEFT);
				}
				paint.setTextAlign(Align.CENTER);
				paint.setTextSize(15);
				canvas.drawText(
						classroom[j],
						(w / 6) * week[j] + (w / 12),
						(((float) (h / 13) * (starttime[j] - (float) 8)) + ((float) (h / 13) * (endtime[j] - (float) 8)))
								/ 2 + yoffset, paint);
				paint.setTextAlign(Paint.Align.LEFT);

			}
			// canvas.drawRect(0,0,w/6,h, paint);
			// canvas.drawRectd(0+w/6,0,2*(w/6),200, paint);

		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		backPressCloseHandler = new BackPressCloseHandler(this);

		RelativeLayout rl = (RelativeLayout) findViewById(R.id.timetable);
		rl.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);

		TextView todayweektv = (TextView) findViewById(R.id.dateAndtime);
		todayweektv.setText(mon + "월 " + tdweek + "주");

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"MM/dd");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String todaydate = formatter.format(c.getTime());

		helper = new DatabaseHelper(this, "database.db", null, 1);
		db = helper.getWritableDatabase();
		dropdb = helper.getWritableDatabase();
		bodb = helper.getWritableDatabase();

		// String[] startday = todaydate.split("/");
		String[] searchday = new String[5];
		searchday[0] = formatter.format(c.getTime());
		c.add(Calendar.DATE, 1);
		searchday[1] = formatter.format(c.getTime());
		c.add(Calendar.DATE, 1);
		searchday[2] = formatter.format(c.getTime());
		c.add(Calendar.DATE, 1);
		searchday[3] = formatter.format(c.getTime());
		c.add(Calendar.DATE, 1);
		searchday[4] = formatter.format(c.getTime());

		Cursor dropresult = dropdb.rawQuery(
				"SELECT date, id FROM droptable WHERE date ='" + searchday[0]
						+ "' or date='" + searchday[1] + "'or date='"
						+ searchday[2] + "'or date='" + searchday[3]
						+ "'or date='" + searchday[4] + "'", null);
		Cursor result = db
				.rawQuery(
						"SELECT week, starttime, endtime, name, classroom, professor, color, show FROM timetable",
						null);
		 Cursor boresult = bodb.rawQuery("SELECT week, starttime, endtime, name, classroom, professor FROM sultable where date ='"+searchday[0]+"' or date='"+searchday[1]+"'or date='"+searchday[2]+"'or date='"+searchday[3]+"'or date='"+searchday[4]+"'",null);

		result.moveToFirst();

		while (!result.isAfterLast()) {
			week[i] = result.getInt(0);
			starttime[i] = result.getFloat(1);
			endtime[i] = result.getFloat(2);
			name[i] = result.getString(3);
			classroom[i] = result.getString(4);
			professor[i] = result.getString(5);
			showup[i] = result.getInt(7);
			color[i] = result.getInt(6);
			i++;
			result.moveToNext();
		}
		result.close();

		if (dropresult.getCount() != 0) {
			dropresult.moveToFirst();
			int k = 0;
			while (!dropresult.isAfterLast()) {
				id[k] = dropresult.getInt(1);
				showup[(id[k])] = 0;
				k++;
				dropresult.moveToNext();
			}
		}
		  if(boresult.getCount() > 0)
          {
             boresult.moveToFirst();
             int k = 0;
             while (!boresult.isAfterLast()){
                week[i] = boresult.getInt(0);
               starttime[i] = boresult.getFloat(1);
               endtime[i] = boresult.getFloat(2);
               name[i] = boresult.getString(3);
               classroom[i] = boresult.getString(4);
               professor[i] = boresult.getString(5);
               showup[i] = 1;
               color[i] = 1;
               i++;
               boresult.moveToNext();
             }
          
          }
       boresult.close();
		Timetable mp = new Timetable(this);
		rl.addView(mp);

		// 호신이 부분

		adapter = new ListAdapter(this);

		adapter.addItem("    ", "   ");
		list = (ListView) findViewById(R.id.assignmentsList);

		list.setAdapter(adapter);

		listlayout = (RelativeLayout) findViewById(R.id.listlayout);

		translateUp = AnimationUtils.loadAnimation(this, R.anim.translate_up);
		translateDown = AnimationUtils.loadAnimation(this,
				R.anim.translate_down);

		SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();

		translateUp.setAnimationListener(animListener);
		translateDown.setAnimationListener(animListener);

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 0) {
					if (isOpen) {
						listlayout.startAnimation(translateDown);
					} else {
						listlayout.setVisibility(View.VISIBLE);
						listlayout.startAnimation(translateUp);
					}

				} else {

				}
			}
		});

		queryData();

		upBtn = (Button) findViewById(R.id.upBtn);
		upBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				upBtn.setVisibility(View.INVISIBLE);
				listlayout.setVisibility(View.VISIBLE);
				listlayout.startAnimation(translateUp);
			}
		});
		// 과목 추가
		final Button addAcitivity = (Button) findViewById(R.id.addclass);
		addAcitivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogSelectaddclass();
			}
		});

		addAcitivity.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					addAcitivity.setBackgroundResource(R.drawable.subplus2);
					break;
				case MotionEvent.ACTION_UP:
					addAcitivity.setBackgroundResource(R.drawable.subplus);
					break;
				}
				return false;
			}
		});
		final Button remindcal = (Button) findViewById(R.id.remindcal);
		remindcal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogSelectOption();

			}
		});

		remindcal.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					remindcal.setBackgroundResource(R.drawable.subchange2);
					break;
				case MotionEvent.ACTION_UP:
					remindcal.setBackgroundResource(R.drawable.subchange);
					break;
				}
				return false;
			}
		});
		// 호신이 부분 끝
			
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.t_activity_main);

		// 아래 메뉴버튼 클릭
		final Button foot1 = (Button) findViewById(R.id.timetablebot1);
		foot1.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					foot1.setBackgroundResource(R.drawable.timecolbot1);// 왜 사진을
																		// 못읽을까;;;;;;
					break;
				case MotionEvent.ACTION_UP:
					foot1.setBackgroundResource(R.drawable.timebot1);
					break;
				}
				return false;
			}
		});

		final Button foot2 = (Button) findViewById(R.id.timetablebot2);
		foot2.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					foot2.setBackgroundResource(R.drawable.timecolorbot2);
					break;
				case MotionEvent.ACTION_UP:
					foot2.setBackgroundResource(R.drawable.timebot2);
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
		final Button foot3 = (Button) findViewById(R.id.timetablebot3);
		foot3.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					foot3.setBackgroundResource(R.drawable.timecolorbot3);
					break;
				case MotionEvent.ACTION_UP:
					foot3.setBackgroundResource(R.drawable.timebot3);
					break;
				}
				return false;
			}
		});
		foot3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(),
						com.aloes.ssu.food.MainActivity.class);
				startActivityForResult(intent, 1003);
				finish();
			}
		});
		final Button foot4 = (Button) findViewById(R.id.timetablebot4);
		foot4.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					foot4.setBackgroundResource(R.drawable.timecolorbot4);
					break;
				case MotionEvent.ACTION_UP:
					foot4.setBackgroundResource(R.drawable.timebot4);
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

		final Button foot5 = (Button) findViewById(R.id.timetablebot5);
		foot5.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					foot5.setBackgroundResource(R.drawable.timecolorbot5);
					break;
				case MotionEvent.ACTION_UP:
					foot5.setBackgroundResource(R.drawable.timebot5);
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

	private void DialogSelectOption() {
		final String items[] = { "과제 / 시험", "휴강", "보강", "닫기" };
		AlertDialog.Builder ab = new AlertDialog.Builder(TimetableMain.this);
		ab.setTitle("일정수정");
		ab.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				switch (whichButton) {
				case 0:

					Context mContext = getApplicationContext();
					LayoutInflater inflater = (LayoutInflater) mContext
							.getSystemService(LAYOUT_INFLATER_SERVICE);
					final View layout = inflater.inflate(
							R.layout.t_addhomework,
							(ViewGroup) findViewById(R.id.layout_root));

					AlertDialog.Builder aDialog = new AlertDialog.Builder(
							TimetableMain.this);// 여기서buttontest는 패키지이름
					aDialog.setTitle("D-day 설정");
					aDialog.setView(layout);

					aDialog.setPositiveButton("저장",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {

									EditText Text1;
									Text1 = (EditText) layout
											.findViewById(R.id.ETSubject1);
									final String TEXT = Text1.getText()
											.toString();

									DialogDatePicker(TEXT);
								}
							});
					aDialog.setNegativeButton("취소",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							});
					AlertDialog ad = aDialog.create();
					ad.show();

					break;
				case 1:
					Intent intentnoclass = new Intent(TimetableMain.this,
							noclass.class);
					intentnoclass.putExtra("week", week);
					intentnoclass.putExtra("name", name);
					intentnoclass.putExtra("index", i);
					startActivityForResult(intentnoclass, 1000);
					break;
				case 2:
					Intent intentsuppclass = new Intent(TimetableMain.this,
							supplementaryclass.class);
					startActivityForResult(intentsuppclass, 1000);
					break;
				case 3:
					break;
				default:
					break;
				}
			}

		});
		ab.show();
	}

	private void DialogSelectaddclass() {
		final String items[] = { "과목 코드, 과목 검색으로 추가", "직접입력" };
		AlertDialog.Builder ab = new AlertDialog.Builder(TimetableMain.this);
		ab.setTitle("과목추가");
		ab.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				switch (whichButton) {
				case 0:
					Intent intent2 = new Intent(TimetableMain.this,
							MainActivity.class);
					startActivityForResult(intent2, REQUESTCODE_ADDACT);
					break;
				case 1:
					Intent intent = new Intent(TimetableMain.this,
							AddClass.class);
					startActivityForResult(intent, REQUESTCODE_ADDACT);
					break;
				default:
					break;
				}
			}

		});
		ab.show();

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUESTCODE_ADDACT) {
			queryData();
		}
	}

	public void queryData() {

		addActivity aA = new addActivity();

		try {
			helper = new DatabaseHelper(this, DATABASE_NAME, null, 1);
			database = helper.getWritableDatabase();

			String sql = "select * from " + tableNameas;

			Cursor cursor = database.rawQuery(sql, null);

			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();

				adapter.addItem(
						"D -"
								+ aA.getdDay(cursor.getInt(0),
										cursor.getInt(1), cursor.getInt(2)),
						cursor.getString(3));

			}
		} catch (Exception ex) {
			Log.d("QueryData", ex.getMessage());
		}
	}

	private void DialogDatePicker(final String ddayname) {
		Calendar c = Calendar.getInstance();
		int cyear = c.get(Calendar.YEAR);
		int cmonth = c.get(Calendar.MONTH);
		int cday = c.get(Calendar.DAY_OF_MONTH);

		DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
			// onDateSet method
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				db.execSQL("insert into assignTable (year, month, day, name) values ("
						+ year
						+ ","
						+ monthOfYear
						+ ","
						+ dayOfMonth
						+ ", '"
						+ ddayname + "')");

				queryData();
				list.setAdapter(adapter);
			}
		};
		DatePickerDialog alert = new DatePickerDialog(this, mDateSetListener,
				cyear, cmonth, cday);
		alert.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class SlidingPageAnimationListener implements AnimationListener {
		@Override
		public void onAnimationEnd(Animation animation) {
			if (isOpen) {
				listlayout.setVisibility(View.INVISIBLE);
				upBtn.setVisibility(View.VISIBLE);
				isOpen = false;
			} else {
				isOpen = true;
			}
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

		@Override
		public void onAnimationStart(Animation animation) {

		}
	}

	@Override
	public void onBackPressed() {
		// super.onBackPressed();
		backPressCloseHandler.onBackPressed();
	}

}
