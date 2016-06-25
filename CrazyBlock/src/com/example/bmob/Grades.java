package com.example.bmob;

import cn.bmob.v3.BmobObject;

public class Grades extends BmobObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3835122648330111428L;
	private String username;
	private float grades;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public float getGrades() {
		return grades;
	}
	public void setGrades(float grades) {
		this.grades = grades;
	}
	
}
