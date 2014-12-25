package com.eyecare.controller;



public class Time {

	private int milisecondShortBreak = 0;
	private int milisecondLongBreak =0;
	private int milisecondLongBreakDuration=0;
	private int milisecondNotifyLongBreak=0;
	
	public int minuteToMilisecond(int minute)
	{
		int milisecond = 0;
		milisecond = minute*60000;
		return milisecond;
	}
	
	public int secondToMilisecond(int second){
		int milisecond =0;
		milisecond = second*1000;
		return milisecond;
	}
	
	public int milisecondToMinute(int milisecond){
		int minute = 0;
		minute = milisecond/60000;
		return minute;
	}
	
	public void setLongBreakTime(int comboBoxLongBreakContentIndex){
		switch(comboBoxLongBreakContentIndex){
			case 0:
				this.milisecondLongBreak = minuteToMilisecond(30);
				break;
			case 1:
				this.milisecondLongBreak = minuteToMilisecond(40);
				break;
			case 2:
				this.milisecondLongBreak = minuteToMilisecond(50);
				break;
			case 3:
				this.milisecondLongBreak = minuteToMilisecond(60);
				break;
			case 4:
				this.milisecondLongBreak = minuteToMilisecond(90);
				break;
			case 5:
				this.milisecondLongBreak = secondToMilisecond(5);
				break;
			case 6:
				this.milisecondLongBreak = secondToMilisecond(10);
		}
	}
	
	public void setLongBreakDuration(int comboBoxTimeOffContentIndex){
		
		switch(comboBoxTimeOffContentIndex){
		case 0:
			this.milisecondLongBreakDuration = minuteToMilisecond(2);
			break;
		case 1:
			this.milisecondLongBreakDuration = minuteToMilisecond(3);
			break;
		case 2:
			this.milisecondLongBreakDuration = minuteToMilisecond(4);
			break;
		case 3:
			this.milisecondLongBreakDuration = minuteToMilisecond(5);
			break;
		case 4:
			this.milisecondLongBreakDuration = minuteToMilisecond(7);
			break;
		case 5:
			this.milisecondLongBreakDuration = secondToMilisecond(5);
			break;
		}	
	}
	
	public void setLongBreakNotifyTime(int comboBoxNotifyContentIndex){

		switch(comboBoxNotifyContentIndex){
		case 0:
			this.milisecondNotifyLongBreak = secondToMilisecond(30);
			break;
		case 1:
			this.milisecondNotifyLongBreak = minuteToMilisecond(1);
			break;
		case 2:
			this.milisecondNotifyLongBreak = minuteToMilisecond(2);
			break;
		case 3:
			this.milisecondNotifyLongBreak = minuteToMilisecond(3);
			break;
		case 4:
			this.milisecondNotifyLongBreak = minuteToMilisecond(5);
			break;
		case 5:
			this.milisecondLongBreakDuration = secondToMilisecond(5);
			break;
		}
	}
	
	public void setShortBreakTime(int comboBoxShortBreakContentIndex){
		
		switch(comboBoxShortBreakContentIndex){
		case 0:
			this.milisecondShortBreak = minuteToMilisecond(3);
			break;
		case 1:
			this.milisecondShortBreak = minuteToMilisecond(5);
			break;
		case 2:
			this.milisecondShortBreak = minuteToMilisecond(8);
			break;
		case 3:
			this.milisecondShortBreak = minuteToMilisecond(10);
			break;
		case 4:
			this.milisecondShortBreak = minuteToMilisecond(15);
			break;
		case 5:
			this.milisecondShortBreak = secondToMilisecond(5);
			break;
		case 6:
			this.milisecondShortBreak = secondToMilisecond(10);
			break;
		}
	}
	
	public int getLongBreakTime(){
		return this.milisecondLongBreak;
	}
	
	public int getLongBreakDuration(){
		return this.milisecondLongBreakDuration;
	}
	
	public int getLongBreakNotifyTime(){
		return this.milisecondNotifyLongBreak;
	}
	
	public int getShortBreakTime(){
		return this.milisecondShortBreak;
	}
	
	
	
}
