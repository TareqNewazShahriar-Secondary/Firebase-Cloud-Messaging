package com.doesnothaveadomain.firebase_cloud_messaging;

import android.app.Application;
import android.content.Intent;
import com.doesnothaveadomain.firebase_cloud_messaging.utilities.StickySevice;

public class ThisApplication extends Application
{
	@Override
	public void onCreate() {
		super.onCreate();
		
		startService(new Intent(this, StickySevice.class));
	}
}