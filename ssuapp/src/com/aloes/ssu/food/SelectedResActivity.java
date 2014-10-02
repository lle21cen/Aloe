package com.aloes.ssu.food;

import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.aloes.ssu.R;

public class SelectedResActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.f_restaurant_info);

		try {
			Intent i = getIntent();
			String name = i.getStringExtra("name");
			String menu = i.getStringExtra("menu");
			String price = i.getStringExtra("price");

			menu = menu.replace("/", "\n");
			price = price.replace("/", "\n");
			TextView Restname = (TextView) findViewById(R.id.restaurantName);

			Restname.setText(name);

			TextView info = (TextView) findViewById(R.id.info);

			StringTokenizer st = new StringTokenizer(menu, "\n");
			StringTokenizer priceToken = new StringTokenizer(price, "\n");
			while (st.hasMoreTokens()) { // 토근이 있는동안 while문이 실행됨
				menu = st.nextToken(); // 토근을 temp 변수에 저장
				price = priceToken.nextToken();
				info.append(menu + " " + price + "원\n\n");
			}
		} catch (Exception ex) {
			Log.d("없는 요소", ex.getMessage());
		}
	}
}
