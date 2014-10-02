package com.aloes.ssu.schoolinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import net.htmlparser.jericho.Source;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

public class Net_HTMLParse
{
   private String url;
   private Context context;
   private Handler handler;
   private ProgressDialog progressDialog;
  // private Source source;
  // private Source source;
   private ArrayList<HashMap<String, String>> data;
   final Handler mHandler = new Handler();
   public th Th;
 //  private String day;
   int page=1;
   boolean check=false;
   
   
   public Net_HTMLParse(Context context, Handler handler, ArrayList<HashMap<String, String>> data)
   {
      this.context = context;
      this.handler = handler;
      this.data = data;
   }
   
   public void open(String category)
   {
      try {
		category= URLEncoder.encode(category, "UTF-8");
	} catch (UnsupportedEncodingException e1) {
		// TODO Auto-generated catch block
		Log.d("debug","encoding");
		e1.printStackTrace();
	}
      url = "http://m.ssu.ac.kr/html/themes/m/html/notice_univ_list.jsp?curPage="+page+"&sCategory="+category+"&sKeyType=&sKeyword=";
      page++;
      //처리하기
      try
      {
    	  if(page==2){
    		  data.clear();
    	  }
    	  Log.d("debug",url);
         process();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
   
   private void process() throws IOException
   {
	   
	   Th= new th();
	   Th.start();
      //상태 Progress 띄우기 위해서 사용함!
      //final Handler mHandler = new Handler();
      /*
      class th extends Thread
      {

         @Override
         public void run()
         {
            URL nURL;
            try
            {
               nURL = new URL(url);
               mHandler.post(new Runnable(){

                  @Override
                  public void run()
                  {
                	  Log.d("debug","h0");
                     progressDialog = ProgressDialog.show(context, "", "데이터를 불러오는 중입니다....");
                     Log.d("debug","h0.5");
                  }
               });
               Log.d("debug","h1");
               //모든 데이터 초기화
          //     data.clear();
               HashMap<String, String> hm = null;
               //InputStream html = nURL.openStream();
               //가져오는 HTML의 인코딩형식
               //source = new Source(new InputStreamReader(html, "UTF-8"));

               Log.d("debug","h2");
               
               BufferedReader in = new BufferedReader(
                                       new InputStreamReader(
                                    		   nURL.openConnection().getInputStream()));
               Log.d("debug","h2.5");
               StringBuilder builder = new StringBuilder();
               String buffer;
               Log.d("debug","h3");
               while( (buffer = in.readLine()) != null)
            	   builder.append(buffer);
               Log.d("debug","h4");
               in.close();
               buffer = builder.toString();
               
               Log.d("debug","hey~");
               while(buffer.indexOf("<li class=\"first-child\">") != -1) // 존재할 경우
               {
            	   buffer =buffer.substring(buffer.indexOf("<li class=\"first-child\">") + "<li class=\"first-child\">".length());
            	   hm=new HashMap<String, String>();
            	   String urlStart = "<a href=\"";
            	   buffer =buffer.substring(buffer.indexOf(urlStart) + urlStart.length());
            	   Log.d("debug", "http://m.ssu.ac.kr" + buffer.substring(0, buffer.indexOf("\"")));
            	   hm.put("url","http://m.ssu.ac.kr" + buffer.substring(0, buffer.indexOf("\"")));

            	   String titleStart = "class=\"subject\">";
            	   buffer =buffer.substring(buffer.indexOf(titleStart) + titleStart.length());
            	   Log.d("debug", buffer.substring(0, buffer.indexOf("</a>")));
            	   hm.put("subject",buffer.substring(0, buffer.indexOf("</a>")));
            	   
            	   String dateStart = "<span class=\"date\">";
            	   buffer =buffer.substring(buffer.indexOf(dateStart) + dateStart.length());
            	   Log.d("debug", buffer.substring(0, buffer.indexOf("</span>")));
            	   hm.put("date",buffer.substring(0, buffer.indexOf("</span>")));
            	   
            	   data.add(hm);
            	   Log.d("debug", "=============================================");
               }
               check=true;
               mHandler.post(new Runnable()
               {
                  public void run()
                  {
                	 
                     progressDialog.cancel();
                     //업데이트 완료를 핸들러로 보내줌
                     handler.sendEmptyMessage(0);
                  }
               });
            }catch (MalformedURLException e)
            {
               e.printStackTrace();
            }
            catch (IOException e)
            {
            	Log.d("debug","IO");
               e.printStackTrace();
            }
            
            
         }
         
      }*/
   }
   
   class th extends Thread
   {

      @Override
      public void run()
      {
         URL nURL;
         try
         {
            nURL = new URL(url);
            mHandler.post(new Runnable(){

               @Override
               public void run()
               {
             	  Log.d("debug","h0");
                  progressDialog = ProgressDialog.show(context, "", "데이터를 불러오는 중입니다....");
                  Log.d("debug","h0.5");
               }
            });
            Log.d("debug","h1");
            //모든 데이터 초기화
       //     data.clear();
            HashMap<String, String> hm = null;
            //InputStream html = nURL.openStream();
            //가져오는 HTML의 인코딩형식
            //source = new Source(new InputStreamReader(html, "UTF-8"));

            Log.d("debug","h2");
            
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                 		   nURL.openConnection().getInputStream()));
            Log.d("debug","h2.5");
            StringBuilder builder = new StringBuilder();
            String buffer;
            Log.d("debug","h3");
            while( (buffer = in.readLine()) != null)
         	   builder.append(buffer);
            Log.d("debug","h4");
            in.close();
            buffer = builder.toString();
            
            Log.d("debug","hey~");
            while(buffer.indexOf("<li class=\"first-child\">") != -1) // 존재할 경우
            {
         	   buffer =buffer.substring(buffer.indexOf("<li class=\"first-child\">") + "<li class=\"first-child\">".length());
         	   hm=new HashMap<String, String>();
         	   String urlStart = "<a href=\"";
         	   buffer =buffer.substring(buffer.indexOf(urlStart) + urlStart.length());
         	   Log.d("debug", "http://m.ssu.ac.kr" + buffer.substring(0, buffer.indexOf("\"")));
         	   hm.put("url","http://m.ssu.ac.kr" + buffer.substring(0, buffer.indexOf("\"")));

         	   String titleStart = "class=\"subject\">";
         	   buffer =buffer.substring(buffer.indexOf(titleStart) + titleStart.length());
         	   Log.d("debug", buffer.substring(0, buffer.indexOf("</a>")));
         	   hm.put("subject",buffer.substring(0, buffer.indexOf("</a>")));
         	   
         	   String dateStart = "<span class=\"date\">";
         	   buffer =buffer.substring(buffer.indexOf(dateStart) + dateStart.length());
         	   Log.d("debug", buffer.substring(0, buffer.indexOf("</span>")));
         	   hm.put("date",buffer.substring(0, buffer.indexOf("</span>")));
         	   
         	   data.add(hm);
         	   Log.d("debug", "=============================================");
            }
            check=true;
            mHandler.post(new Runnable()
            {
               public void run()
               {
             	 
                  progressDialog.cancel();
                  //업데이트 완료를 핸들러로 보내줌
                  handler.sendEmptyMessage(0);
               }
            });
         }catch (MalformedURLException e)
         {
            e.printStackTrace();
         }
         catch (IOException e)
         {
         	Log.d("debug","IO");
            e.printStackTrace();
         }
         
         
      }
      
   }
   
   
   
   
}