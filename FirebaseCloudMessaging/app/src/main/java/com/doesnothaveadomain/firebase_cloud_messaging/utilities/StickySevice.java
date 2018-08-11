package com.doesnothaveadomain.firebase_cloud_messaging.utilities;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.IBinder;
import android.util.Log;

import com.doesnothaveadomain.firebase_cloud_messaging.FirebaseActivity;
import com.doesnothaveadomain.firebase_cloud_messaging.LoginActivity;

import java.nio.channels.SelectionKey;

public class StickySevice extends Service
{
	private String TAG = "STK_SRV";
	
	@Override
	public void onCreate()
	{
	
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		inform("on start. Flags : " + flags +", startid: " + startId, "onStartCommand");
		
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
		inform("destroyed", "onDestroy");
	}
	
	@Override
	public void onLowMemory()
	{
		super.onLowMemory();
		inform( "low memory fired.", "onLowMemory");
	}
	
	@Override
	public boolean onUnbind(Intent intent)
	{
		inform("unbind called.", "onUnbind");
		
		return super.onUnbind(intent);
	}
	
	@Override
	public void onRebind(Intent intent)
	{
		super.onRebind(intent);
		inform("rebind happened.", "onRebind");
	}
	
	@Override
	public void onTaskRemoved(Intent rootIntent)
	{
		super.onTaskRemoved(rootIntent);
		inform("task removed, but why! rootIntent: " + (rootIntent != null ? rootIntent.toString() : ""), "onTaskRemoved");
		Context context = getApplicationContext();
		PackageManager pm = getPackageManager();
		Intent i = pm.getLaunchIntentForPackage("com.doesnothaveadomain.firebase_cloud_messaging");
		i.putExtra("run_transparent","yes");
		
		//startActivity(i);
		//startService(new Intent(getApplication(), MyFirebaseMessagingService.class));
		//startService(new Intent(this, StickySevice.class));
	}
	
	@Override
	public IBinder onBind(Intent arg0)
	{
		inform("on bind", "OnBind");
		return null;
	}
	
	private void inform(String msg, String channelName)
	{
		Log.w(TAG, msg);
		new NotificationHelper(getApplicationContext(), msg, msg, channelName).Show();
	}
}
