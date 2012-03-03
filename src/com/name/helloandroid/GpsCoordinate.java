package com.name.helloandroid;

public class GpsCoordinate {
	public String timestamp;
	public int xCoordinate;
	public int yCoordinate;
	
	public GpsCoordinate(String date, int xLoc, int yLoc)
	{
		timestamp = date;
		xCoordinate = xLoc;
		yCoordinate = yLoc;
		
	}
	
	public String toString()
	{
		return (timestamp + ":" + xCoordinate + ";" + yCoordinate + "|");
	}
	
}
