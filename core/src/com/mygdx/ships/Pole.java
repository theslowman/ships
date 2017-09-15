package com.mygdx.ships;

public class Pole {
	public static final String T = "trafiony";
	public static final String ST = "statek";
	public static final String P = "pudlo";
	public static final String B = "pole bia³e";
	public static final int SIZE = 10;

	private
	String status;

	public
	Pole(){
		setStatus(B);
	}

	public String getStatus(){
		return status.toString();

	}

	void setStatus(String status){
		this.status = status;
	}

}

