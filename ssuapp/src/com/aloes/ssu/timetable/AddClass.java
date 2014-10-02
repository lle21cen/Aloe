package com.aloes.ssu.timetable;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.aloes.ssu.R;



public class AddClass extends Activity {
	
	int weekclass = 0;
	String name;
	float starttime = 0;
	float endtime = 0;
	String classroom;
	SQLiteDatabase db;
	DatabaseHelper helper;
	int colorset;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.t_add_class);
		EditText ETstartH = (EditText) findViewById(R.id.ETstartH);
        EditText ETstartM = (EditText) findViewById(R.id.ETendM);
		EditText ETendH = (EditText) findViewById(R.id.ETendH);
        EditText ETendM = (EditText) findViewById(R.id.ETendM);
		ETstartH.setClickable(false);
        ETstartM.setClickable(false);
        ETstartH.setFocusable(false);
        ETstartM.setFocusable(false);
		ETendH.setClickable(false);
        ETendM.setClickable(false);
        ETendH.setFocusable(false);
        ETendM.setFocusable(false);
        findViewById(R.id.Mon).setOnClickListener(week);
		findViewById(R.id.Tue).setOnClickListener(week);
		findViewById(R.id.Wed).setOnClickListener(week);
		findViewById(R.id.Thu).setOnClickListener(week);
		findViewById(R.id.Fri).setOnClickListener(week);
		findViewById(R.id.Sat).setOnClickListener(week);
		findViewById(R.id.Col1).setOnClickListener(colorselect);
		findViewById(R.id.Col2).setOnClickListener(colorselect);
		findViewById(R.id.Col3).setOnClickListener(colorselect);
		findViewById(R.id.Col4).setOnClickListener(colorselect);
		findViewById(R.id.Col5).setOnClickListener(colorselect);
		findViewById(R.id.Col6).setOnClickListener(colorselect);
		findViewById(R.id.Col7).setOnClickListener(colorselect);
		findViewById(R.id.Col8).setOnClickListener(colorselect);
		findViewById(R.id.Col9).setOnClickListener(colorselect);
		findViewById(R.id.Col10).setOnClickListener(colorselect);
		findViewById(R.id.Col11).setOnClickListener(colorselect);
		findViewById(R.id.Col12).setOnClickListener(colorselect);
		helper = new DatabaseHelper(this, "database.db", null, 1);
        db = helper.getWritableDatabase();
        
      
		Button Close = (Button) findViewById(R.id.Close);
		Close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		Button Save = (Button) findViewById(R.id.Save);
		Save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		    	EditText ETname = (EditText) findViewById(R.id.ETSubject);
		        EditText ETclassroom = (EditText) findViewById(R.id.ETLectureRoom);
				name = ETname.getText().toString();
				classroom = ETclassroom.getText().toString();
				db.execSQL("INSERT into timetable (week, starttime, endtime, name, classroom, color, show) values ("+weekclass+" , "+starttime+" , "+endtime+" , '"+name+"' , '"+classroom+"' , "+colorset+" , 1)");
				finish();
			}
		});
		

		Button setstarttime = (Button) findViewById(R.id.setstarttime);
		setstarttime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DialogTimePicker();
			}
		});
		
		Button setendtime = (Button) findViewById(R.id.setendtime);
		setendtime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DialogTimePicker2();
			}
		});

	}
	Button.OnClickListener colorselect = new View.OnClickListener() {
		
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.Col1:
				colorset = 0;
				break;
			case R.id.Col2:
				colorset = 1;
				break;
			case R.id.Col3:
				colorset = 2;
				break;
			case R.id.Col4:
				colorset = 3;
				break;
			case R.id.Col5:
				colorset = 4;
				break;
			case R.id.Col6:
				colorset = 5;
				break;
			case R.id.Col7:
				colorset = 6;
				break;
			case R.id.Col8:
				colorset = 7;
				break;
			case R.id.Col9:
				colorset = 8;
				break;
			case R.id.Col10:
				colorset = 9;
				break;
			case R.id.Col11:
				colorset = 10;
				break;
			case R.id.Col12:
				colorset = 11;
				break;
			}
		}
	};
	
	Button.OnClickListener week = new View.OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.Mon:
				Toast.makeText(AddClass.this,
			            "월요일", Toast.LENGTH_SHORT)
			            .show();
				weekclass = 1;
				break;
			case R.id.Tue:
				Toast.makeText(AddClass.this,
			            "화요일", Toast.LENGTH_SHORT)
			            .show();
				weekclass = 2;
				break;
			case R.id.Wed:
				Toast.makeText(AddClass.this,
			            "수요일", Toast.LENGTH_SHORT)
			            .show();
				weekclass = 3;
				break;
			case R.id.Thu:
				Toast.makeText(AddClass.this,
			            "목요일", Toast.LENGTH_SHORT)
			            .show();
				weekclass = 4;
				break;
			case R.id.Fri:
				Toast.makeText(AddClass.this,
			            "금요일", Toast.LENGTH_SHORT)
			            .show();
				weekclass = 5;
				break;
			case R.id.Sat:
				Toast.makeText(AddClass.this,
			            "토요일", Toast.LENGTH_SHORT)
			            .show();
				weekclass = 6;
				break;
			}
		}
	};
	
	
	
	private void DialogTimePicker(){
	    TimePickerDialog.OnTimeSetListener mTimeSetListener = 
	    new TimePickerDialog.OnTimeSetListener() {
	        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	            EditText ETstartH = (EditText) findViewById(R.id.ETstartH);
	            EditText ETstartM = (EditText) findViewById(R.id.ETstartM);
	            ETstartH.setText(hourOfDay+"");
	            ETstartM.setText(minute+"");
	            starttime = hourOfDay+(float)((float)minute/(float)60.0);

	        }
	    };
	    TimePickerDialog alert = new TimePickerDialog(this, 
	mTimeSetListener, 8, 0, false);
	    alert.show();
	}

	private void DialogTimePicker2(){
	    TimePickerDialog.OnTimeSetListener mTimeSetListener = 
	    new TimePickerDialog.OnTimeSetListener() {
	        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	            EditText ETendH = (EditText) findViewById(R.id.ETendH);
	            EditText ETendM = (EditText) findViewById(R.id.ETendM);
	            ETendH.setText(hourOfDay+"");
	            ETendM.setText(minute+"");
	            endtime = hourOfDay + (float)((float)minute/(float)60.0);
	        }
	    };
	    TimePickerDialog alert = new TimePickerDialog(this, 
	mTimeSetListener, 8, 0, false);
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
	
	
}
