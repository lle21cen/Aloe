package com.aloes.ssu.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.aloes.ssu.BackPressCloseHandler;
import com.aloes.ssu.R;
import com.aloes.ssu.campusmap.MapStartActivity;
import com.aloes.ssu.schoolinfo.SchoolinfoActivity;
import com.aloes.ssu.setting.SettingStartActivity;
import com.aloes.ssu.timetable.TimetableMain;

public class MainActivity extends Activity {

	private final int REQUEST_CODE = 1001;
	private BackPressCloseHandler backPressCloseHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_main);
		  backPressCloseHandler = new BackPressCloseHandler(this);

		final LinearLayout foodlayout = (LinearLayout) findViewById(R.layout.f_random_pick);

		Button toRandomPickActivity = (Button) findViewById(R.id.random);

		toRandomPickActivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), RandomPick.class);
				startActivityForResult(intent, REQUEST_CODE);

			}
		});

		// 식당메뉴확인
		final Button toStudentmealActivity = (Button) findViewById(R.id.stuRes);
		toStudentmealActivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), EatActivity.class);
				intent.putExtra("where", "학생식당");
				startActivityForResult(intent, 1003);

			}
		});
		toStudentmealActivity.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					toStudentmealActivity
							.setBackgroundResource(R.drawable.studentfood2);
					break;
				case MotionEvent.ACTION_UP:
					toStudentmealActivity
							.setBackgroundResource(R.drawable.studentfood);
					break;
				}
				return false;
			}
		});

		final Button teachRes = (Button) findViewById(R.id.teachRes);
		teachRes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), EatActivity.class);
				intent.putExtra("where", "교직원식당");
				startActivityForResult(intent, 1003);

			}
		});
		teachRes.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					teachRes.setBackgroundResource(R.drawable.teacherfood2);
					break;
				case MotionEvent.ACTION_UP:
					teachRes.setBackgroundResource(R.drawable.teacherfood);
					break;
				}
				return false;
			}
		});

		final Button ScienceRes = (Button) findViewById(R.id.ScienceRes);
		ScienceRes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), EatActivity.class);
				intent.putExtra("where", "정보대식당");
				startActivityForResult(intent, 1003);

			}
		});

		ScienceRes.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					ScienceRes.setBackgroundResource(R.drawable.multifood2);
					break;
				case MotionEvent.ACTION_UP:
					ScienceRes.setBackgroundResource(R.drawable.multifood);
					break;
				}
				return false;
			}
		});

		final Button snackRes = (Button) findViewById(R.id.snackRes);
		snackRes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), EatActivity.class);
				intent.putExtra("where", "스넥코너");
				startActivityForResult(intent, 1003);

			}
		});
		snackRes.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					snackRes.setBackgroundResource(R.drawable.foodsnack2);
					break;
				case MotionEvent.ACTION_UP:
					snackRes.setBackgroundResource(R.drawable.foodsnack);
					break;
				}
				return false;
			}
		});

		// 음식별종류
		final Button koreanFood = (Button) findViewById(R.id.koreanFood);
		koreanFood.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(),
						koreanFoodList.class);
				startActivityForResult(intent, 1002);

			}
		});

		koreanFood.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					koreanFood.setBackgroundResource(R.drawable.hanbun2);
					break;
				case MotionEvent.ACTION_UP:
					koreanFood.setBackgroundResource(R.drawable.hanbun);
					break;
				}
				return false;
			}
		});

		final Button flourFood = (Button) findViewById(R.id.WorldFood);

		flourFood.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(),
						WorldfoodList.class);
				startActivityForResult(intent, 1002);

			}
		});

		flourFood.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					flourFood.setBackgroundResource(R.drawable.world2);
					break;
				case MotionEvent.ACTION_UP:
					flourFood.setBackgroundResource(R.drawable.world);
					break;
				}
				return false;
			}
		});

		final Button Chicken = (Button) findViewById(R.id.Chicken);
		
		Chicken.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(),
						ChickenList.class);
				startActivityForResult(intent, 1002);

			}
		});
		Chicken.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					Chicken.setBackgroundResource(R.drawable.chicken2);
					break;
				case MotionEvent.ACTION_UP:
					Chicken.setBackgroundResource(R.drawable.chicken);
					break;
				}
				return false;
			}
		});

		final Button AllFood = (Button) findViewById(R.id.AllFood);
		
		AllFood.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					AllFood.setBackgroundResource(R.drawable.allres2);
					break;
				case MotionEvent.ACTION_UP:
					AllFood.setBackgroundResource(R.drawable.allres);
					Intent intent = new Intent(getBaseContext(),
							AllFoodList.class);
					startActivityForResult(intent, 1002);
					break;
				}
				return false;
			}
		});

		final Button JapaneseFood = (Button) findViewById(R.id.JapaneseFood);

		JapaneseFood.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					JapaneseFood.setBackgroundResource(R.drawable.jp2);
					break;
				case MotionEvent.ACTION_UP:
					JapaneseFood.setBackgroundResource(R.drawable.jp);
					Intent intent = new Intent(getBaseContext(),
							JapanesefoodList.class);
					startActivityForResult(intent, 1002);
					break;
				}
				return false;
			}
		});

		final Button ChineseFood = (Button) findViewById(R.id.ChineseFood);

		ChineseFood.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					ChineseFood.setBackgroundResource(R.drawable.cha2);
					break;
				case MotionEvent.ACTION_UP:
					ChineseFood.setBackgroundResource(R.drawable.cha);
					Intent intent = new Intent(getBaseContext(),
							ChinesefoodList.class);
					startActivityForResult(intent, 1002);
					break;
				}
				return false;
			}
		});

		final Button WesternFood = (Button) findViewById(R.id.WesternFood);

		WesternFood.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					WesternFood.setBackgroundResource(R.drawable.us2);
					break;
				case MotionEvent.ACTION_UP:
					WesternFood.setBackgroundResource(R.drawable.us);
					Intent intent = new Intent(getBaseContext(),
							WesternfoodList.class);
					startActivityForResult(intent, 1002);
					break;
				}
				return false;
			}
		});

		final Button alcohol = (Button) findViewById(R.id.alcohol);

		alcohol.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					alcohol.setBackgroundResource(R.drawable.ach2);
					break;
				case MotionEvent.ACTION_UP:
					alcohol.setBackgroundResource(R.drawable.ach);
					Intent intent = new Intent(getBaseContext(),
							BarList.class);
					startActivityForResult(intent, 1002);
					break;
				}
				return false;
			}
		});

		final Button foodbot1 = (Button) findViewById(R.id.foodbot01);

		foodbot1.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					foodbot1.setBackgroundResource(R.drawable.foodcolbot1);
					break;
				case MotionEvent.ACTION_UP:
					foodbot1.setBackgroundResource(R.drawable.foodbot1);
					break;
				}
				return false;
			}
		});
		
		foodbot1.setOnClickListener(new OnClickListener() {
 			@Override
 			public void onClick(View v) {
 				Intent intent = new Intent(getBaseContext(), TimetableMain.class);
 				startActivityForResult(intent, 1003);
 				finish();
 			}
 		});

		final Button foodbot2 = (Button) findViewById(R.id.foodbot02);

		foodbot2.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					foodbot2.setBackgroundResource(R.drawable.foodcolorbot2);
					break;
				case MotionEvent.ACTION_UP:
					foodbot2.setBackgroundResource(R.drawable.foodbot2);
					break;
				}
				return false;
			}
		});
		foodbot2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), SchoolinfoActivity.class);
				startActivityForResult(intent, 1003);	
				finish();
			}
		});
		final Button foodbot3 = (Button) findViewById(R.id.foodbot03);

		foodbot3.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					foodbot3.setBackgroundResource(R.drawable.foodcolorbot03);
					break;
				case MotionEvent.ACTION_UP:
					foodbot3.setBackgroundResource(R.drawable.foodbot3);
					break;
				}
				return false;
			}
		});

		final Button foodbot4 = (Button) findViewById(R.id.foodbot04);

		foodbot4.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					foodbot4.setBackgroundResource(R.drawable.foodcolorbot4);
					break;
				case MotionEvent.ACTION_UP:
					foodbot4.setBackgroundResource(R.drawable.foodbot4);
					break;
				}
				return false;
			}
		});
    	foodbot4.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getBaseContext(), MapStartActivity.class);
			startActivityForResult(intent, 1003);
			finish();
			
		}
	});

		final Button foodbot5 = (Button) findViewById(R.id.foodbot05);

		foodbot5.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					foodbot5.setBackgroundResource(R.drawable.foodcolorbot5);
					break;
				case MotionEvent.ACTION_UP:
					foodbot5.setBackgroundResource(R.drawable.foodbot5);
					break;
				}
				return false;
			}
		});
		
		foodbot5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(),SettingStartActivity.class);
				startActivityForResult(intent, 1003);
				finish();
				
			}
		});

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent Data) {
		super.onActivityResult(requestCode, resultCode, Data);

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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }

}
