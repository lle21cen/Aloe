package com.aloes.ssu.schoolinfo;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

public class InfoButton extends Button {
	Context mContext;
	
	private boolean selected;
	
	
	
	public InfoButton(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		mContext = context;
		
	
		selected = false;
	}
	public InfoButton(Context context, AttributeSet attrs) {
		// TODO Auto-generated constructor stub
		super(context, attrs);
		mContext = context;
		
	
		selected = false;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void wantPushUp()
	{
		//setBackgroundColor(selected_color);
	}
	
	public void nonwantPushUp()
	{
		//setBackgroundColor(default_color);
	}
	
	
	
	

}
