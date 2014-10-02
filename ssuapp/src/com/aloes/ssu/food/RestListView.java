package com.aloes.ssu.food;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aloes.ssu.R;

public class RestListView extends LinearLayout{

	Context context;
	private ImageView iconImage;
	private TextView RestName, RestTel;
	private RestItems item;
	
	public RestListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.context = context;
	}

	public RestListView(Context context, RestItems aItem) {
		super(context);
		this.context = context;
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.f_restaurant_item, this, true);
		
		item = aItem;
		iconImage = (ImageView) findViewById(R.id.image);
		RestName = (TextView) findViewById(R.id.restName);
		RestTel = (TextView) findViewById(R.id.Tel);
		
		iconImage.setImageDrawable(aItem.getImage());
		RestName.setText(aItem.getName());
		RestTel.setText(aItem.getTel());
	}

	public RestItems getItem(){
		return item;
	}
	public void setIcon(Drawable icon){
		iconImage.setImageDrawable(icon);
	}
	
	public void setName(String name){
		RestName.setText(name);
	}
	
	public void setTel(String tel){
		RestTel.setText(tel);
	}
}
