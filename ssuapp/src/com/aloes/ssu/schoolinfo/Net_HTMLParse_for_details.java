package com.aloes.ssu.schoolinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

public class Net_HTMLParse_for_details {
	private String url;
	private Context context;
	private Handler handler;
	private ProgressDialog progressDialog;
	// private Source source;
	// private Source source;
	private ArrayList<HashMap<String, String>> data;
	final Handler mHandler = new Handler();
	public th Th;

	// public Thread thread=new Thread();
	// private String day;

	public Net_HTMLParse_for_details(Context context, Handler handler,
			ArrayList<HashMap<String, String>> data) {
		this.context = context;
		this.handler = handler;
		this.data = data;
	}

	public void open(String Url) {

		url = Url;
		// 처리하기
		try {

			Log.d("debug", url);
			process();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void process() throws IOException {
		Th = new th();
		Th.start();
		/*
		 * //상태 Progress 띄우기 위해서 사용함! final Handler mHandler = new Handler();
		 * //new Thread() new Thread() {
		 * 
		 * @Override public void run() { URL nURL; try { nURL = new URL(url);
		 * mHandler.post(new Runnable(){
		 * 
		 * @Override public void run() { Log.d("debug","h0"); progressDialog =
		 * ProgressDialog.show(context, "", "데이터를 불러오는 중입니다....");
		 * Log.d("debug","h0.5"); } }); // Log.d("debug","h1"); //모든 데이터 초기화
		 * data.clear(); HashMap<String, String> hm = null; //InputStream html =
		 * nURL.openStream(); //가져오는 HTML의 인코딩형식 //source = new Source(new
		 * InputStreamReader(html, "UTF-8"));
		 * 
		 * // Log.d("debug","h2");
		 * 
		 * BufferedReader in = new BufferedReader( new InputStreamReader(
		 * nURL.openConnection().getInputStream())); // Log.d("debug","h2.5");
		 * StringBuilder builder = new StringBuilder(); String buffer; //
		 * Log.d("debug","h3"); while( (buffer = in.readLine()) != null)
		 * builder.append(buffer); // Log.d("debug","h4"); in.close(); buffer =
		 * builder.toString();
		 * 
		 * buffer =buffer.substring(buffer.indexOf("<div class=\"bbs-view\">") +
		 * "<div class=\"bbs-view\">".length()); hm=new HashMap<String,
		 * String>(); String titleStart = "<div class=\"subject\">"; buffer
		 * =buffer.substring(buffer.indexOf(titleStart) + titleStart.length());
		 * Log.d("debug",buffer.substring(0, buffer.indexOf("<span")));
		 * hm.put("subject",buffer.substring(0, buffer.indexOf("<span")));
		 * 
		 * String dateStart = "class=\"date\">"; buffer
		 * =buffer.substring(buffer.indexOf(dateStart) + dateStart.length());
		 * Log.d("debug", buffer.substring(0, buffer.indexOf("</span>")));
		 * hm.put("date",buffer.substring(0, buffer.indexOf("</span>")));
		 * 
		 * int filesize=0; // Log.d("debug","hey~");
		 * while(buffer.indexOf("<img alt=\"첨부파일\"") != -1) // 존재할 경우 {
		 * 
		 * String fileStart = "<a herf=\""; buffer
		 * =buffer.substring(buffer.indexOf(fileStart) + fileStart.length());
		 * Log.d("debug", "http://m.ssu.ac.kr"+buffer.substring(0,
		 * buffer.indexOf("\"")));
		 * hm.put("file"+filesize,"http://m.ssu.ac.kr"+buffer.substring(0,
		 * buffer.indexOf("\"")));
		 * 
		 * String fileNameStart="\">"; buffer
		 * =buffer.substring(buffer.indexOf(fileNameStart) +
		 * fileNameStart.length()); Log.d("debug", buffer.substring(0,
		 * buffer.indexOf("</a>")));
		 * hm.put("filename"+filesize,buffer.substring(0,
		 * buffer.indexOf("</a>"))); filesize++;
		 * 
		 * } hm.put("filesize", String.valueOf(filesize));
		 * 
		 * String
		 * contents=buffer.substring(buffer.indexOf("class=\"contents\">")
		 * ,buffer.indexOf("<div class=\"section-button\">")); //
		 * contents.replace("</div>", ""); Log.d("debug",contents);
		 * hm.put("contents",contents);
		 * 
		 * data.add(hm); Log.d("debug",
		 * "============================================="); mHandler.post(new
		 * Runnable() { public void run() { progressDialog.cancel(); //업데이트 완료를
		 * 핸들러로 보내줌 handler.sendEmptyMessage(0); } }); }catch
		 * (MalformedURLException e) { e.printStackTrace(); } catch (IOException
		 * e) { Log.d("debug","IO"); e.printStackTrace(); }
		 * 
		 * 
		 * }
		 * 
		 * }.start();
		 */
	}

	public class th extends Thread {

		@Override
		public void run() {
			URL nURL;
			try {
				nURL = new URL(url);
				mHandler.post(new Runnable() {

					@Override
					public void run() {
						// Log.d("debug","h0");
						progressDialog = ProgressDialog.show(context, "",
								"데이터를 불러오는 중입니다....");
						// Log.d("debug","h0.5");
					}

					// progressDialog = ProgressDialog.show(context, "",
					// "데이터를 불러오는 중입니다....");

				});
				// Log.d("debug","h1");
				// 모든 데이터 초기화
				data.clear();
				HashMap<String, String> hm = null;
				// InputStream html = nURL.openStream();
				// 가져오는 HTML의 인코딩형식
				// source = new Source(new InputStreamReader(html, "UTF-8"));

				// Log.d("debug","h2");

				BufferedReader in = new BufferedReader(new InputStreamReader(
						nURL.openConnection().getInputStream()));
				// Log.d("debug","h2.5");
				StringBuilder builder = new StringBuilder();
				String buffer;
				// Log.d("debug","h3");
				while ((buffer = in.readLine()) != null)
					builder.append(buffer);
				// Log.d("debug","h4");
				in.close();
				buffer = builder.toString();

				buffer = buffer.substring(buffer
						.indexOf("<div class=\"bbs-view\">")
						+ "<div class=\"bbs-view\">".length());
				hm = new HashMap<String, String>();
				String titleStart = "<div class=\"subject\">";
				buffer = buffer.substring(buffer.indexOf(titleStart)
						+ titleStart.length());
				Log.d("debug", buffer.substring(0, buffer.indexOf("<span")));
				hm.put("subject", buffer.substring(0, buffer.indexOf("<span")));

				String dateStart = "class=\"date\">";
				buffer = buffer.substring(buffer.indexOf(dateStart)
						+ dateStart.length());
				Log.d("debug", buffer.substring(0, buffer.indexOf("</span>")));
				hm.put("date", buffer.substring(0, buffer.indexOf("</span>")));

				int filesize = 0;
				// Log.d("debug","hey~");
				String fileSt = "<img src=\"../img/common/ico_file.gif\"";
				while (buffer.indexOf("<img src=\"../img/common/ico_file.gif\"") != -1) // 존재할
																					// 경우
				{
					 buffer = buffer.substring(buffer.indexOf(fileSt)+fileSt.length());

					String fileStart = "<a href=\"";
					//Log.d("debug",buffer);
					//Log.d("debug",String.valueOf(buffer.indexOf(fileStart)));
					buffer = buffer.substring(buffer.indexOf(fileStart)+ fileStart.length());
					//Log.d("debug",buffer);
					Log.d("debug","http://m.ssu.ac.kr"+ buffer.substring(0, buffer.indexOf("\"")));
					hm.put("file" + filesize,"http://m.ssu.ac.kr"+ buffer.substring(0, buffer.indexOf("\"")));

					String fileNameStart = "\">";
					buffer = buffer.substring(buffer.indexOf(fileNameStart)
							+ fileNameStart.length());
					Log.d("debug", buffer.substring(0, buffer.indexOf("</a>")));
					hm.put("filename" + filesize,
							buffer.substring(0, buffer.indexOf("</a>")));
					filesize++;

				}
				hm.put("filesize", String.valueOf(filesize));

				String contents = buffer.substring(
						buffer.indexOf("class=\"contents\">") + 17,
						buffer.indexOf("<div class=\"section-button\">"));
				// contents.replace("class=\"contents\">", "");
				// contents.replace("</div>", "");
				// Log.d("debug",contents);
				hm.put("contents", contents);

				data.add(hm);
				Log.d("debug", "=============================================");
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
				Log.d("debug", "IO");
				e.printStackTrace();
			}

		}

	}

}