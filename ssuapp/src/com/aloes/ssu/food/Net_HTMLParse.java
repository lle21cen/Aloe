package com.aloes.ssu.food;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

public class Net_HTMLParse {
	private String url;
	private Context context;
	private Handler handler;
	private ProgressDialog progressDialog;
	private Source source;
	private ArrayList<HashMap<String, String>> data;
	private String day;
	String name;
	int notsunday;

	public Net_HTMLParse(Context context, Handler handler,
			ArrayList<HashMap<String, String>> data) {
		this.context = context;
		this.handler = handler;
		this.data = data;
	}

	public void open(String eyyyy, String emm, String edd, String ename,
			int today) {
		url = "http://m.ssu.ac.kr/html/themes/m/html/etc_menulist.jsp";
		// 처리하기
		System.out.print(eyyyy + "." + emm + "." + edd);
		day = eyyyy + "-" + emm + "-" + edd;
		name = ename;
		notsunday = today;
		try {
			Log.d("debug",""+notsunday);
			if (notsunday != Calendar.SUNDAY) {
				process();
			}
			else{
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void process() throws IOException {
		// 상태 Progress 띄우기 위해서 사용함!
		final Handler mHandler = new Handler();
		new Thread() {

			@Override
			public void run() {
				URL nURL;
				try {
					nURL = new URL(url);
					mHandler.post(new Runnable() {

						@Override
						public void run() {
							progressDialog = ProgressDialog.show(context, "",
									"데이터를 불러오는 중입니다....");
						}
					});

					// 모든 데이터 초기화
					data.clear();
					HashMap<String, String> hm = null;
					InputStream html = nURL.openStream();
					// 가져오는 HTML의 인코딩형식
					source = new Source(new InputStreamReader(html, "UTF-8"));
					System.out.println("10%는 됨  오늘의 요일은 " + notsunday);
					// 테이블가져오기
					if (notsunday != 1) {
						Element div = (Element) source.getAllElementsByClass(
								"weeklymenu weeklymenu-" + day).get(0);
						System.out.println(source.getAllElementsByClass(
								"weeklymenu weeklymenu-" + day).size());

						int fb = div.getAllElementsByClass("frame-b").size();
						for (int i = 0; i < fb; i++) {
							Element fbn = (Element) div.getAllElementsByClass(
									"frame-b").get(i);
							int bj = fbn.getAllElementsByClass("basic").size();
							if (bj > 0) {
								for (int j = 0; j < bj; j++) {
									String namehr = fbn
											.getAllElements("strong")
											.get(j * 2).getTextExtractor()
											.toString();
									if (namehr.equals(name)
											&& !name.equals("스넥코너")) {
										Element std = (Element) fbn
												.getAllElementsByClass("basic")
												.get(j);
										switch (i) {
										case 0: // 조식일때
											hm = new HashMap<String, String>();
											hm.put("subject", "조식");
											hm.put("title2",
													(fbn.getTextExtractor()
															.toString()
															.replace(namehr, "")
															.replace("/", "\n")));
											data.add(hm);
											break;
										case 1: // 중식일때
											hm = new HashMap<String, String>();
											System.out.println("여기까진 됨");
											hm.put("subject", "중식");
											hm.put("title2",
													(std.getTextExtractor()
															.toString()
															.replace(namehr, "")
															.replace("/", "\n")));
											data.add(hm);
											break;
										case 2: // 중식2 일때
											hm = new HashMap<String, String>();
											hm.put("subject", "중식2");
											hm.put("title2",
													(std.getTextExtractor()
															.toString()
															.replace(namehr, "")
															.replace("/", "\n")));
											data.add(hm);
											break;
										case 3:// 중식3일때
											hm = new HashMap<String, String>();
											hm.put("subject", "중식3");
											hm.put("title2",
													(std.getTextExtractor()
															.toString()
															.replace(namehr, "")
															.replace("/", "\n")));
											data.add(hm);
											break;
										case 4: // 석식일때
											hm = new HashMap<String, String>();
											hm.put("subject", "석식");
											hm.put("title2",
													(std.getTextExtractor()
															.toString()
															.replace(namehr, "")
															.replace("/", "\n")));
											data.add(hm);
											break;
										}
									} else if (name.equals("스넥코너")
											&& namehr.equals("스넥코너")) {
										Element std = (Element) fbn
												.getAllElementsByClass("basic")
												.get(j);
										hm = new HashMap<String, String>();
										hm.put("subject", "스넥코너");
										hm.put("title2", (std
												.getTextExtractor().toString()
												.replace(namehr, "").replace(
												"/", "\n")));
										data.add(hm);
									} else if (name.equals("스넥코너")
											&& namehr.equals("푸드코트")) {
										Element std = (Element) fbn
												.getAllElementsByClass("basic")
												.get(j);
										hm = new HashMap<String, String>();
										hm.put("subject", "푸드코트");
										hm.put("title2", (std
												.getTextExtractor().toString()
												.replace(namehr, "").replace(
												"/", "\n")));
										data.add(hm);
									}
								}
							}

						}
					} else // if today is sunday
					{
						hm = new HashMap<String, String>();
						hm.put("subject", name);
						hm.put("title2", "주말은 학식이 없는 날입니다.");
						data.add(hm);
					}

					mHandler.post(new Runnable() {
						public void run() {
							progressDialog.cancel();
							// 업데이트 완료를 핸들러로 보내줌
							handler.sendEmptyMessage(0);
						}
					});
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}.start();
	}
}
