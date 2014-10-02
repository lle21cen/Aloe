package com.aloes.ssu.timetable;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.aloes.ssu.Firstck;
import com.aloes.ssu.R;

public class MainActivity extends Activity {

	SQLiteDatabase db;
	DatabaseHelper helper;

	ArrayList<String> subjectNames = new ArrayList<String>();
	ArrayList<Integer> subjectCodes = new ArrayList<Integer>();

	String[] collegeItems = { "�����к�", "�ι�����", "�ڿ����д���", "��������", "��ȸ���д���",
			"��������а�", "��������", "IT����", "�濵����" }, facultyItems, majorItems;

	EditText subjectName;
	Spinner collegeSpinner, facultySpinner, majorSpinner;
	Button searchName;
	RadioGroup aGroup;
	RadioButton majorRadioBtn;
	int selectedType;

	SubjectListAdapter mAdapter;
	ListView subjectList;

	Drawable subjectType;

	Cursor cursor;

	private String nameForAnotherAct, timeForAnotherAct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.t_addactivity_main);

		Firstck fs = new Firstck(this);

		helper = new DatabaseHelper(this, "classtable.db", null, 1);
		db = helper.getWritableDatabase();

		aGroup = (RadioGroup) findViewById(R.id.radioGroup);
		collegeSpinner = (Spinner) findViewById(R.id.college);
		facultySpinner = (Spinner) findViewById(R.id.faculty);
		majorSpinner = (Spinner) findViewById(R.id.major);

		majorRadioBtn = (RadioButton) findViewById(R.id.subject_major);

		aGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				Resources res = getResources();
				switch (checkedId) {
				case R.id.subject_major:
					selectedType = 1;
					subjectType = res.getDrawable(R.drawable.zungong);
					break;
				case R.id.subject_mandatory:
					selectedType = 2;
					subjectType = res.getDrawable(R.drawable.ghopil);
					break;
				case R.id.subject_choice:
					selectedType = 3;
					subjectType = res.getDrawable(R.drawable.ghosun);
					break;
				}
				setListByData(selectedType);
			}
		});
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, collegeItems);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		collegeSpinner.setAdapter(adapter);

		subjectName = (EditText) findViewById(R.id.inputSubjectName);

		/*
		 * ���� ����Ʈ ����
		 */
		subjectList = (ListView) findViewById(R.id.subjectList);
		mAdapter = new SubjectListAdapter(this);

		collegeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				selectAndInputFacultys();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		facultySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mAdapter.clearItem();
				setListByData(selectedType);
				setAndInputMajors();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		majorSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					mAdapter.clearItem();
					setListByData(selectedType);
				} catch (Exception ex) {
					Log.d("�� ���ǳ� ���� ����", ex.getMessage());
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		searchName = (Button) findViewById(R.id.searchName);

		searchName.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (subjectNames.size() > 0) {
					mAdapter.clearItem();
					String name = subjectName.getText().toString();
					long code = doParseLong(name);
					cursor.moveToFirst();

					for (int i = 0; i < cursor.getCount(); i++) {
						if (subjectNames.get(i).contains(name)
								|| code == cursor.getLong(5)) {

							mAdapter.addItem(new subjectListItem(subjectType,
									cursor.getString(0), cursor.getString(1),
									cursor.getString(2), cursor.getString(3),
									cursor.getString(4), cursor.getString(6)));
						}
						cursor.moveToNext();
					}
					subjectList.setAdapter(mAdapter);
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(subjectName.getWindowToken(), 0);
				}
			}
		});

		subjectList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getBaseContext(), TestActivity.class);
				try {
					intent.putExtra("name", mAdapter.getItemName(position));
					Log.d("debug", mAdapter.getItemTime(position));
					if (mAdapter.getItemTime(position).length() > 0) {
						intent.putExtra("time", mAdapter.getItemTime(position));
					}

					startActivityForResult(intent, 2000);
					finish();
				} catch (Exception ex) {
					intent.putExtra("time", "");
					Toast.makeText(getApplicationContext(), "Ÿ�Ӱ� ����",
							Toast.LENGTH_SHORT).show();
					Log.d("����", ex.getMessage());
				}
			}
		});
		/*
		 * EditText Ű�е� ���� ���ʹ�� �˻� Ű
		 */
		subjectName.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(subjectName.getWindowToken(), 0);

					return true;
				}
				return false;
			}
		});
		searchName.setOnTouchListener(new OnTouchListener() {
			@SuppressLint("ClickableViewAccessibility")
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					searchName.setBackgroundResource(R.drawable.search2);
					break;
				case MotionEvent.ACTION_UP:
					searchName.setBackgroundResource(R.drawable.search);
					break;
				}
				return false;
			}
		});
	}

	public long doParseLong(String str) {
		try {
			long answer = 0;
			answer = Long.parseLong(str);
			return answer;
		} catch (Exception ex) {
			Log.d("����", ex.getMessage());
			return 0;
		}
	}

	/*
	 * type - ���� ���� Ÿ�� 1�� code - �����ڵ� 3�� name - ���Ǹ� 4�� mate - �й� 5�� professor -
	 * ������ 6�� grade - ���� / �ð� 8�� time - ���ǽð� 11�� who - ������� 12��
	 */
	public void setListByData(int selectedType) {
		// ���� : �̹���, �����, ������, �й�, ����/�ð�, �������
		try {
			mAdapter.clearItem();

			String sql = null;
			if (selectedType == 1) {
				// ����Ʈ�� �������� �߰�
				String selectedTableName = majorSpinner.getSelectedItem()
						.toString();

				sql = "select name, professor, mate, grade, who, code, time from "
						+ selectedTableName;

			} else if (selectedType == 2) {
				sql = "select name, professor, mate, grade, who, code, time from ����";

			} else if (selectedType == 3) {
				sql = "select name, professor, mate, grade, who, code, time from ����";

			}
			if (sql != null) {
				cursor = db.rawQuery(sql, null);
				addItemToList(cursor);
			}
			subjectList.setAdapter(mAdapter);
		} catch (Exception ex) {
			Log.d("����Ʈ��������", ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void addItemToList(Cursor cursor) {
		cursor.moveToFirst();

		for (int i = 0; i < cursor.getCount(); i++) {

			// ���� : �̹���, �����, ������+�й�, ����/�ð�, �������
			mAdapter.addItem(new subjectListItem(subjectType, cursor
					.getString(0), cursor.getString(1), cursor.getString(2),
					cursor.getString(3), cursor.getString(4), cursor
							.getString(6)));

			subjectNames.add(cursor.getString(0));
			subjectCodes.add(cursor.getInt(5));
			cursor.moveToNext();
		}

	}

	public void setAndInputMajors() {
		majorItems = new String[] { facultySpinner.getSelectedItem().toString() };

		if (facultySpinner.getSelectedItem().toString().equals("�����к�")) {
			majorItems = new String[] { "�����к�", "�����������", "����������", "�ǳ���������" };
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, majorItems);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		majorSpinner.setAdapter(adapter);
	}

	public void selectAndInputFacultys() {
		if (collegeSpinner.getSelectedItemPosition() == 0) {
			facultyItems = new String[] { "�����к�" };
		} else if (collegeSpinner.getSelectedItemPosition() == 1) {
			facultyItems = new String[] { "�⵶���а�", "������а�", "������а�",
					"������а�", "�Ҿ�ҹ��а�", "�߾��߹��а�", "�Ͼ��Ϻ��а�", "ö�а�", "���а�",
					"��������а�", "����â���а�", "��Ȱü���а�" };
		} else if (collegeSpinner.getSelectedItemPosition() == 2) {
			facultyItems = new String[] { "���а�", "�����а�", "ȭ�а�", "������躸������а�",
					"�ǻ���ý����к�" };
		} else if (collegeSpinner.getSelectedItemPosition() == 3) {
			facultyItems = new String[] { "���а�", "���������а�" };
		} else if (collegeSpinner.getSelectedItemPosition() == 4) {
			facultyItems = new String[] { "��ȸ�����к�", "�����к�", "��ġ�ܱ��а�", "������ȸ�а�",
					"���ȫ���а�" };
		} else if (collegeSpinner.getSelectedItemPosition() == 5) {
			facultyItems = new String[] { "�����а�", "�۷ι�����а�" };
		} else if (collegeSpinner.getSelectedItemPosition() == 6) {
			facultyItems = new String[] { "ȭ�а��а�", "����ż������̹����а�", "������к�",
					"�����а�", "��������ý��۰��а�", "�����к�" };
		} else if (collegeSpinner.getSelectedItemPosition() == 7) {
			facultyItems = new String[] { "��ǻ���к�", "����������ڰ��к�", "�۷ι��̵���к�",
					"����Ʈ�����к�" };
		} else if (collegeSpinner.getSelectedItemPosition() == 8) {
			facultyItems = new String[] { "�濵�к�", "��ó�濵�а�", "��ó�߼ұ���а�", "ȸ���а�",
					"���丮�ڸ��濵�а�", "���Ű濵�а�" };
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, facultyItems);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		facultySpinner.setAdapter(adapter);
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

	public String getNameForAnotherAct() {
		return nameForAnotherAct;
	}

	public void setNameForAnotherAct(String nameForAnotherAct) {
		this.nameForAnotherAct = nameForAnotherAct;
	}

	public String getTimeForAnotherAct() {
		return timeForAnotherAct;
	}

	public void setTimeForAnotherAct(String timeForAnotherAct) {
		this.timeForAnotherAct = timeForAnotherAct;
	}

}
