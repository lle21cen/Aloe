package com.aloes.ssu;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.util.Log;

import com.aloes.ssu.schoolinfo.SchoolinfoActivity;
import com.google.android.gcm.GCMBaseIntentService;


public class GCMIntentService extends GCMBaseIntentService {

static String re_message=null;




	 private static void generateNotification(Context context, String message) {

		 int icon = R.drawable.ic_launcher;

		 long when = System.currentTimeMillis();




	  NotificationManager notificationManager = (NotificationManager) context

	    .getSystemService(Context.NOTIFICATION_SERVICE);

	  Notification notification = new Notification(icon, message, when);




	String title = "앱이름";

	Intent notificationIntent = new Intent(context, SchoolinfoActivity.class);

	re_message=message;




	  notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

	  PendingIntent intent = PendingIntent.getActivity(context, 0,notificationIntent, 0);




	  notification.setLatestEventInfo(context, title, message, intent);

	  notification.flags |= Notification.FLAG_AUTO_CANCEL;

	  notificationManager.notify(0, notification);

	 }




	 @Override

	 protected void onError(Context arg0, String arg1) {




	 }

	  private void getPreferences(Context context, String msg){
	        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
	        String isnull = pref.getString("GCM setting", "");
	        if(isnull.equals("Von"))
	        {
	        	Vibrator vibrator = (Vibrator)getSystemService(context.VIBRATOR_SERVICE);
	            vibrator.vibrate(1000);
	      	  	generateNotification(context, msg);

	        }
	        else if(isnull.equals("Voff"))
	        {
	      	  	generateNotification(context, msg);

	        }
	        else if(isnull.equals("off"))
	        {
	        }
	    }	


	 @Override

	 protected void onMessage(Context context, Intent intent) {

	  String msg = intent.getStringExtra("msg");

	  Log.e("getmessage", "getmessage:" + msg);
	  
	  
	  
	  
	  
	  getPreferences(context, msg);

	 }




	 @Override

	 protected void onRegistered(Context context, String reg_id) {

	  Log.e("키를 등록합니다.(GCM INTENTSERVICE)", reg_id);

	 }




	 @Override

	 protected void onUnregistered(Context arg0, String arg1) {

	  Log.e("키를 제거합니다.(GCM INTENTSERVICE)", "제거되었습니다.");

	 }

	}
