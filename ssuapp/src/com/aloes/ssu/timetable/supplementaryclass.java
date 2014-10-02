package com.aloes.ssu.timetable;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.aloes.ssu.R;


public class supplementaryclass extends Activity{

   int weekclass = 0;
   String name;
   float starttime = 0;
   float endtime = 0;
   String classroom;
   SQLiteDatabase db;
   DatabaseHelper helper;
   int colorset;
   String date;
   
   
   int index = 0;
   Calendar c = Calendar.getInstance();
    int cyear = c.get(Calendar.YEAR);
    int cmonth = c.get(Calendar.MONTH);
    int cday = c.get(Calendar.DAY_OF_MONTH);
    int nomon;
    int noday;
    int id;
   
   
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.t_supplementaryclass);
      EditText ETstartH = (EditText) findViewById(R.id.ETstartHaabb);
        EditText ETstartM = (EditText) findViewById(R.id.ETendMaabb);
      EditText ETendH = (EditText) findViewById(R.id.ETendHaabb);
        EditText ETendM = (EditText) findViewById(R.id.ETendMaabb);
      ETstartH.setClickable(false);
        ETstartM.setClickable(false);
        ETstartH.setFocusable(false);
        ETstartM.setFocusable(false);
      ETendH.setClickable(false);
        ETendM.setClickable(false);
        ETendH.setFocusable(false);
        ETendM.setFocusable(false);
        findViewById(R.id.Monaabb).setOnClickListener(week);
      findViewById(R.id.Tueaabb).setOnClickListener(week);
      findViewById(R.id.Wedaabb).setOnClickListener(week);
      findViewById(R.id.Thuaabb).setOnClickListener(week);
      findViewById(R.id.Friaabb).setOnClickListener(week);
      findViewById(R.id.Sataabb).setOnClickListener(week);
      helper = new DatabaseHelper(this, "database.db", null, 1);
        db = helper.getWritableDatabase();
   
        Button Close = (Button) findViewById(R.id.Closeaa);
      Close.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            finish();
         }
      });
        
      Button Save = (Button) findViewById(R.id.Saveaa);
      Save.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
             EditText ETname = (EditText) findViewById(R.id.ETSubjectaabb);
              EditText ETclassroom = (EditText) findViewById(R.id.ETLectureRoomaa);
            name = ETname.getText().toString();
            classroom = ETclassroom.getText().toString();
            db.execSQL("INSERT into sultable (week, starttime, endtime, name, classroom, date) values ("+weekclass+" , "+starttime+" , "+endtime+" , '"+name+"' , '"+classroom+"', '"+date+"')");
            finish();
         }
      });
      
      Button setstarttime = (Button) findViewById(R.id.setstarttimeaabb);
      setstarttime.setOnClickListener(new OnClickListener() {
         
         @Override
         public void onClick(View v) {
            // TODO Auto-generated method stub
            DialogTimePicker();
         }
      });
      
      Button setdate = (Button) findViewById(R.id.setendtimeaa);
      setdate.setOnClickListener(new OnClickListener() {
         
         @Override
         public void onClick(View arg0) {
            // TODO Auto-generated method stub
            DialogDatePicker();
         }
      });
      
      Button setendtime = (Button) findViewById(R.id.setendtimeaabb);
      setendtime.setOnClickListener(new OnClickListener() {
         
         @Override
         public void onClick(View v) {
            // TODO Auto-generated method stub
            DialogTimePicker2();
         }
      });
        
   
   }
   
   
   Button.OnClickListener week = new View.OnClickListener() {
      public void onClick(View v) {
         switch (v.getId()) {
         case R.id.Monaabb:
            Toast.makeText(supplementaryclass.this,
                     "월요일", Toast.LENGTH_SHORT)
                     .show();
            weekclass = 1;
            break;
         case R.id.Tueaabb:
            Toast.makeText(supplementaryclass.this,
                     "화요일", Toast.LENGTH_SHORT)
                     .show();
            weekclass = 2;
            break;
         case R.id.Wedaabb:
            Toast.makeText(supplementaryclass.this,
                     "수요일", Toast.LENGTH_SHORT)
                     .show();
            weekclass = 3;
            break;
         case R.id.Thuaabb:
            Toast.makeText(supplementaryclass.this,
                     "목요일", Toast.LENGTH_SHORT)
                     .show();
            weekclass = 4;
            break;
         case R.id.Friaabb:
            Toast.makeText(supplementaryclass.this,
                     "금요일", Toast.LENGTH_SHORT)
                     .show();
            weekclass = 5;
            break;
         case R.id.Sataabb:
            Toast.makeText(supplementaryclass.this,
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
               EditText ETstartH = (EditText) findViewById(R.id.ETstartHaabb);
               EditText ETstartM = (EditText) findViewById(R.id.ETstartMaabb);
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
               EditText ETendH = (EditText) findViewById(R.id.ETendHaabb);
               EditText ETendM = (EditText) findViewById(R.id.ETendMaabb);
               ETendH.setText(hourOfDay+"");
               ETendM.setText(minute+"");
               endtime = hourOfDay + (float)((float)minute/(float)60.0);
           }
       };
       TimePickerDialog alert = new TimePickerDialog(this, 
   mTimeSetListener, 8, 0, false);
       alert.show();
   }
   
   

   private void DialogDatePicker(){
       Calendar c = Calendar.getInstance();
       int cyear = c.get(Calendar.YEAR);
       int cmonth = c.get(Calendar.MONTH);
       int cday = c.get(Calendar.DAY_OF_MONTH);
        
       DatePickerDialog.OnDateSetListener mDateSetListener = 
       new DatePickerDialog.OnDateSetListener() {
       // onDateSet method
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                nomon = monthOfYear+1;
                String dated;
                if(nomon<10)
                   dated = "0"+nomon;
                else
                   dated = nomon+"";
                noday = dayOfMonth;
                String nodaystr = ""+noday;
                if(noday <10)
                   nodaystr="0"+noday;
                date = dated+"/"+nodaystr;
                
                EditText ETstartH = (EditText) findViewById(R.id.ETendHaa);
                  EditText ETstartM = (EditText) findViewById(R.id.ETendMaa);
                  ETstartH.setText(dated);
                  ETstartM.setText(nodaystr);
            
       }
        };
        DatePickerDialog alert = new DatePickerDialog(this,  mDateSetListener,  
        cyear, cmonth, cday);
        alert.show();
   }
   
   
   
}