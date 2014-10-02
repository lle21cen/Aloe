package com.aloes.ssu.timetable;

public class ListItem {

	private String dDay;
	private String subjectName;
	
	public ListItem(String dDay, String subjectName){
		this.dDay=dDay;
		this.subjectName=subjectName;
	}
	
	public String getdDay(){
		return dDay;
	}
	
	public String getSubjectName(){
		return subjectName;
	}
}
