package com.aloes.ssu.food;

import android.graphics.drawable.Drawable;

public class RestItems {

	private Drawable image;
	private String name, tel;
	
	public RestItems(String name, String tel){
		this.name = name;
		this.tel = tel;
	}
	
	public void setImage(Drawable image){
		this.image = image;
	}
	
	public Drawable getImage(){
		return this.image;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getTel(){
		return this.tel;
	}
}
