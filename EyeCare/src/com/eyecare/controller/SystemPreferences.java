package com.eyecare.controller;

import java.util.prefs.Preferences;

public class SystemPreferences {
	
	private Preferences pref = Preferences.userRoot().node(this.getClass().getName());
	
	public void setBooleanSystemPreferences(String key, Boolean value){
		pref.putBoolean(key, value);
	}
	
	public void setIntSystemPreferences(String key, int value){
		pref.putInt(key, value);
	}
	
	public int getIntSytemPreferences(String key, int value){
		return pref.getInt(key, value);
	}
	
	public Boolean getBooleanSystemPreferences(String key, Boolean value){
		return pref.getBoolean(key, value);
	}
}
