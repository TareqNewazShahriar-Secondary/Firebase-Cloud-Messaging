package com.doesnothaveadomain.firebase_cloud_messaging.Models;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.io.Serializable;

public class KeyValuePair implements Serializable
{
	private String key;
	private String value;
	
	public KeyValuePair(String key, String value)
	{
		setKey(key);
		setValue(value);
	}
	
	public String getKey()
	{
		return key;
	}
	
	public void setKey(String key)
	{
		this.key = key;
	}
	
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
}
