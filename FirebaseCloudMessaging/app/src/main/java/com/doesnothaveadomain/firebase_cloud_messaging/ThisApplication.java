package com.doesnothaveadomain.firebase_cloud_messaging;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.doesnothaveadomain.firebase_cloud_messaging.utilities.MyFirebaseMessagingService;
import com.doesnothaveadomain.firebase_cloud_messaging.utilities.NotificationHelper;
import com.doesnothaveadomain.firebase_cloud_messaging.utilities.StickySevice;

public class ThisApplication extends Application implements Application.ActivityLifecycleCallbacks
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		startService(new Intent(this, StickySevice.class));
		registerActivityLifecycleCallbacks(this);
	}
	
	@Override
	public void onActivityDestroyed(Activity activity)
	{
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		
		// 1st try
		Intent restartService = new Intent(getApplicationContext(), MyFirebaseMessagingService.class);
		PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(),
				1,
				restartService,
				PendingIntent.FLAG_ONE_SHOT);
		//alarmManager.set(AlarmManager.ELAPSED_REALTIME, 500, pendingIntent);
		
		// 2nd try
		Intent i = getPackageManager().getLaunchIntentForPackage("com.doesnothaveadomain.firebase_cloud_messaging");
		i.putExtra("run_transparent","yes");
		PendingIntent p = PendingIntent.getActivity(getApplicationContext(), 0, i, 0);
		//alarmManager.set(AlarmManager.ELAPSED_REALTIME, 500, p);
		
		new NotificationHelper(getApplicationContext(),
				"onActivityDestroyed",
				"onActivityDestroyed",
				"onActivityDestroyed")
				.Show();
	}
	
	@Override
	public void onActivityCreated(Activity activity, Bundle bundle)
	{
	
	}
	
	@Override
	public void onActivityStarted(Activity activity)
	{
	
	}
	
	@Override
	public void onActivityResumed(Activity activity)
	{
	
	}
	
	@Override
	public void onActivityPaused(Activity activity)
	{
	
	}
	
	@Override
	public void onActivityStopped(Activity activity)
	{
		String s = "";
	}
	
	@Override
	public void onActivitySaveInstanceState(Activity activity, Bundle bundle)
	{
	
	}
}