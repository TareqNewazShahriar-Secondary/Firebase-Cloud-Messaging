package com.doesnothaveadomain.firebase_cloud_messaging.utilities;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;

import com.doesnothaveadomain.firebase_cloud_messaging.LoginActivity;

public class StickySevice extends Service
{
	private String TAG = "STK_SRV";
	
	public StickySevice()
	{
		String s = "sfd";
	}
	
	@Override
	public void onCreate()
	{
	
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		inform("on start. Flags : " + flags +", startid: " + startId);
		
		PackageManager pm = getPackageManager();
		Intent i = pm.getLaunchIntentForPackage("com.doesnothaveadomain.firebase_cloud_messaging");
		i.putExtra("run_transparent","yes");
		startActivity(i);
		return Service.START_STICKY;
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		inform("destroyed");
	}
	
	@Override
	public void onLowMemory()
	{
		super.onLowMemory();
		inform( "low memory fired.");
	}
	
	@Override
	public boolean onUnbind(Intent intent)
	{
		inform("unbind called.");
		
		return super.onUnbind(intent);
	}
	
	@Override
	public void onRebind(Intent intent)
	{
		super.onRebind(intent);
		inform("rebind happened.");
	}
	
	@Override
	public void onTaskRemoved(Intent rootIntent)
	{
		super.onTaskRemoved(rootIntent);
		inform("task removed, but why! rootIntent: " + rootIntent.getExtras().toString());
		
		PackageManager pm = getPackageManager();
		Intent i = pm.getLaunchIntentForPackage("com.doesnothaveadomain.firebase_cloud_messaging");
		i.putExtra("run_transparent","yes");
		startActivity(i);
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		
		return null;
	}
	
	private void inform(String msg)
	{
		Log.w(TAG, msg);
		new NotificationHelper(NotificationHelper.activityScope, TAG, msg).Show();
	}
}
