package com.aloes.ssu.timetable;

import android.graphics.drawable.Drawable;

public class subjectListItem {

	private Drawable icon;
	private String subjectName, profName, grade, who, mate, time;

	public subjectListItem(Drawable icon, String subjectName, String profName,
			String mate, String grade, String who, String time) {
		this.icon = icon;
		this.subjectName = subjectName;
		this.profName = profName;
		this.mate = mate;
		this.grade = grade;
		this.who = who;
		this.time = time;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public String getMate() {
		return mate;
	}

	public void setMate(String mate) {
		this.mate = mate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
