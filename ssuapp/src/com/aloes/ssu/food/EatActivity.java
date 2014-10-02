/*
 *  Net_HTMLParse.open 으로 파싱 
 *  EatActivity 클래스에서 날짜를 받아서 open 호출.
 *  숭실대학교 컴퓨터학부 14학번 강경완
 * 
 */

package com.aloes.ssu.food;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.aloes.ssu.R;

public class EatActivity extends Activity {

	private ArrayList<HashMap<String, String>> data;
	private Button bt_open;
	private ListView list;
	private SimpleAdapter sa;
	private Net_HTMLParse hp;
	private TextView dateAndtimeLabel;
	private Button bt_date1;
	private Button bt_date2;

	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
	SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
	SimpleDateFormat CurDayFormat = new SimpleDateFormat("dd");
	Date current1 = new Date();
	String strCurYear = CurYearFormat.format(current1);
	String strCurMonth = CurMonthFormat.format(current1);
	String strCurDay = CurDayFormat.format(current1);
	int eyyyy = Integer.parseInt(strCurYear);
	int emm = Integer.parseInt(strCurMonth);
	int edd = Integer.parseInt(strCurDay);
	int today = calendar.get(calendar.DAY_OF_WEEK);
	int not3day = 0;

	String name;

	private final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			listUpdate();
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_eat);

		Intent intent = getIntent();
		name = intent.getExtras().getString("where");

		dateAndtimeLabel = (TextView) findViewById(R.id.dateAndtime);
		dateAndtimeLabel.setText(strCurYear + "." + strCurMonth + "."
				+ strCurDay);

		bt_date1 = (Button) findViewById(R.id.dateBtn1);
		bt_date2 = (Button) findViewById(R.id.dateBtn2);

		bt_date1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (not3day == 0 || edd <= 1) {
					error1();
				} else {
					edd--;
					not3day--;
					today--;

					dateUpdate();
				}
			}

		});
		bt_date2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (emm == 1 || emm == 3 || emm == 5 || emm == 7 || emm == 8
						|| emm == 10 || emm == 12) {
					if (edd >= 31) {
						error3();
					} else {
						if (not3day > 3) {
							error2();
						} else {
							edd++;
							not3day++;
							today++;
							dateUpdate();
						}
					}
				} else if (emm == 2) {
					if (edd >= 28) {
						error3();
					} else {
						if (not3day > 3) {
							error2();
						} else {
							edd++;
							not3day++;
							today++;
							dateUpdate();
						}
					}
				} else {
					if (edd >= 30) {
						error3();
					} else {
						if (not3day > 3) {
							error2();
						} else {
							edd++;
							not3day++;
							today++;
							dateUpdate();
						}
					}
				}
			}

		});

		data = new ArrayList<HashMap<String, String>>();

		list = (ListView) findViewById(R.id.listView1);

		hp = new Net_HTMLParse(EatActivity.this, handler, data);

		sa = new SimpleAdapter(EatActivity.this, data, R.layout.f_list_row,
				new String[] { "subject", "title2" }, new int[] {
						R.id.tv_title, R.id.tv_subject });
		if (emm < 10) {
			if (edd < 10) {

				hp.open(eyyyy + "", "0" + emm + "", "0" + edd, name, today);
			} else {

				hp.open(eyyyy + "", "0" + emm, edd + "", name, today);
			}

		} else {

			if (edd < 10) {

				hp.open(eyyyy + "", "" + emm + "", "0" + edd, name, today);
			} else {

				hp.open(eyyyy + "", emm + "", edd + "", name, today);
			}

		}
		list.setAdapter(sa);

	}

	// 업데이트하기
	private void listUpdate() {
		sa.notifyDataSetChanged();
	}

	private void dateUpdate() {
		if (emm < 10) {
			if (edd < 10) {
				dateAndtimeLabel.setText(eyyyy + ".0" + emm + ".0" + edd);
			} else {
				dateAndtimeLabel.setText(eyyyy + ".0" + emm + "." + edd);
			}

		} else {
			if (edd < 10) {
				dateAndtimeLabel.setText(eyyyy + "." + emm + ".0" + edd);
			} else {
				dateAndtimeLabel.setText(eyyyy + "." + emm + "." + edd);
			}
		}

		if (emm < 10) {
			if (edd < 10) {

				hp.open(eyyyy + "", "0" + emm + "", "0" + edd, name, today);
			} else {

				hp.open(eyyyy + "", "0" + emm, edd + "", name, today);
			}

		} else {

			if (edd < 10) {

				hp.open(eyyyy + "", "" + emm + "", "0" + edd, name, today);
			} else {

				hp.open(eyyyy + "", emm + "", edd + "", name, today);
			}
		}

		list.setAdapter(sa);
	}

	private void error1() {
		Toast.makeText(this, "지나간 날짜는 조회할 수 없습니다.", Toast.LENGTH_LONG).show();
	}

	private void error2() {
		Toast.makeText(this, "5일 이후 식단은 조회할 수 없습니다.", Toast.LENGTH_LONG).show();
	}

	private void error3() {
		Toast.makeText(this, "다음 달 식단은 조회할 수 없습니다.", Toast.LENGTH_LONG).show();
	}

}