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
		// ó���ϱ�
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
		// ���� Progress ���� ���ؼ� �����!
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
									"�����͸� �ҷ����� ���Դϴ�....");
						}
					});

					// ��� ������ �ʱ�ȭ
					data.clear();
					HashMap<String, String> hm = null;
					InputStream html = nURL.openStream();
					// �������� HTML�� ���ڵ�����
					source = new Source(new InputStreamReader(html, "UTF-8"));
					System.out.println("10%�� ��  ������ ������ " + notsunday);
					// ���̺�������
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
											&& !name.equals("�����ڳ�")) {
										Element std = (Element) fbn
												.getAllElementsByClass("basic")
												.get(j);
										switch (i) {
										case 0: // �����϶�
											hm = new HashMap<String, String>();
											hm.put("subject", "����");
											hm.put("title2",
													(fbn.getTextExtractor()
															.toString()
															.replace(namehr, "")
															.replace("/", "\n")));
											data.add(hm);
											break;
										case 1: // �߽��϶�
											hm = new HashMap<String, String>();
											System.out.println("������� ��");
											hm.put("subject", "�߽�");
											hm.put("title2",
													(std.getTextExtractor()
															.toString()
															.replace(namehr, "")
															.replace("/", "\n")));
											data.add(hm);
											break;
										case 2: // �߽�2 �϶�
											hm = new HashMap<String, String>();
											hm.put("subject", "�߽�2");
											hm.put("title2",
													(std.getTextExtractor()
															.toString()
															.replace(namehr, "")
															.replace("/", "\n")));
											data.add(hm);
											break;
										case 3:// �߽�3�϶�
											hm = new HashMap<String, String>();
											hm.put("subject", "�߽�3");
											hm.put("title2",
													(std.getTextExtractor()
															.toString()
															.replace(namehr, "")
															.replace("/", "\n")));
											data.add(hm);
											break;
										case 4: // �����϶�
											hm = new HashMap<String, String>();
											hm.put("subject", "����");
											hm.put("title2",
													(std.getTextExtractor()
															.toString()
															.replace(namehr, "")
															.replace("/", "\n")));
											data.add(hm);
											break;
										}
									} else if (name.equals("�����ڳ�")
											&& namehr.equals("�����ڳ�")) {
										Element std = (Element) fbn
												.getAllElementsByClass("basic")
												.get(j);
										hm = new HashMap<String, String>();
										hm.put("subject", "�����ڳ�");
										hm.put("title2", (std
												.getTextExtractor().toString()
												.replace(namehr, "").replace(
												"/", "\n")));
										data.add(hm);
									} else if (name.equals("�����ڳ�")
											&& namehr.equals("Ǫ����Ʈ")) {
										Element std = (Element) fbn
												.getAllElementsByClass("basic")
												.get(j);
										hm = new HashMap<String, String>();
										hm.put("subject", "Ǫ����Ʈ");
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
						hm.put("title2", "�ָ��� �н��� ���� ���Դϴ�.");
						data.add(hm);
					}

					mHandler.post(new Runnable() {
						public void run() {
							progressDialog.cancel();
							// ������Ʈ �ϷḦ �ڵ鷯�� ������
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
