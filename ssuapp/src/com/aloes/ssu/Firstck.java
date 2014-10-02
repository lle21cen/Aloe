/*******************************
 *                             *
 *         2014. 08. 19        *
 *  db파일이 존재하는지 확인하는 클래스     *
 *                             *
 ******************************/


package com.aloes.ssu;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Firstck extends SQLiteOpenHelper {


static String NAME = "database.db";
static CursorFactory FACTORY = null;
static String PACKEGE = "com.aloes.ssu";
static String DB = "database.db";

static int VERSION  = 1;
public Firstck(Context context) {
	super(context, NAME, FACTORY, VERSION);
	// TODO Auto-generated constructor stub
	try {
		boolean bResult = isCheckDB(context);  // DB가 있는지?
		if(!bResult){   // DB가 없으면 복사
			copyDB(context);
		}else{
		}
	} catch (Exception e) {

	}
}

// DB가 있나 체크하기
public boolean isCheckDB(Context mContext){
	String filePath = "/data/data/" + PACKEGE + "/databases/" + DB;
	File file = new File(filePath);

	if (file.exists()) {
		return true;

	}
	return false;
}

// DB를 복사하기

// assets의 /db/xxxx.db 파일을 설치된 프로그램의 내부 DB공간으로 복사하기

public void copyDB(Context mContext){
	AssetManager manager = mContext.getAssets();

	String folderPath = "/data/data/" + PACKEGE + "/databases";

	String filePath = "/data/data/" + PACKEGE + "/databases/" +DB;

	File folder = new File(folderPath);

	File file = new File(filePath);

 

	FileOutputStream fos = null;

	BufferedOutputStream bos = null;

	try {

		InputStream is = manager.open(DB);

		BufferedInputStream bis = new BufferedInputStream(is);

 

		if (folder.exists()) {

		}else{

			folder.mkdirs();

		}

		if (file.exists()) {

			file.delete();

			file.createNewFile();

		}

 

		fos = new FileOutputStream(file);

		bos = new BufferedOutputStream(fos);

		int read = -1;

		byte[] buffer = new byte[1024];

		while ((read = bis.read(buffer, 0, 1024)) != -1) {

			bos.write(buffer, 0, read);

		}

 

		bos.flush();

 

		bos.close();

		fos.close();

		bis.close();

		is.close();

 

	} catch (IOException e) {

		Log.e("ErrorMessage : ", e.getMessage());

	} 

}

/** Called when the activity is first created. */

@Override

public void onCreate(SQLiteDatabase db) {
}

 

@Override

public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	// TODO Auto-generated method stub

	String QUERY = "DROP TABLE IF EXISTS word";

	db.execSQL(QUERY);

	onCreate(db);

 

 

}

}
