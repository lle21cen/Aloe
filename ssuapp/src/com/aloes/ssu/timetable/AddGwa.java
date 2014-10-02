package com.aloes.ssu.timetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.aloes.ssu.R;
// 과제 추가 기능

public class AddGwa extends Activity implements OnItemSelectedListener {

	final static int GWAJE = 1;
	
	String[] items = { "물리", "화학", "컴그", "플밍" };
	Spinner spinner;
	EditText year_et;
	EditText mon_et;
	EditText day_et;
	String name_gwa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.t_add_gwa);
		Button close = (Button) findViewById(R.id.close);
		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(RESULT_CANCELED);
				finish();
			}
		});
		spinner = (Spinner) findViewById(R.id.select_gwa);
		spinner.setOnItemSelectedListener(this);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		year_et = (EditText) findViewById(R.id.year_et);
		mon_et = (EditText) findViewById(R.id.mon_et);
		day_et = (EditText) findViewById(R.id.day_et);


		Button save = (Button) findViewById(R.id.save);
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AddGwa.this, addActivity.class);
				intent.putExtra("name", name_gwa);
				intent.putExtra("year", year_et.getText().toString());
				intent.putExtra("month", mon_et.getText().toString());
				intent.putExtra("day", day_et.getText().toString());
				setResult(GWAJE, intent);
				finish();
			}
		});
	}

	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
			name_gwa = items[position];
	}

	public void onNothingSelected(AdapterView<?> parent) {

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
