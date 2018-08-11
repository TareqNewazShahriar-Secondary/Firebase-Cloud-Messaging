package com.doesnothaveadomain.firebase_cloud_messaging.utilities;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.doesnothaveadomain.firebase_cloud_messaging.FirebaseActivity;
import com.doesnothaveadomain.firebase_cloud_messaging.R;

public class NotificationHelper
{
	public static Activity activityScope = null;
	Context appContext = null;
	
	String channelName = "";
	String channelId = "";
	String tittle = "";
	String body = "";
	
	public NotificationHelper(Context appContext, String title, String body)
	{
		this(appContext, title,  body, "Default");
	}
	
	public NotificationHelper(Context appContext, String title, String body, String channelName)
	{
		this.channelName = channelName;
		this.channelId = "__" + channelName + "__";
		this.tittle = title;
		this.body = body;
		this.appContext = appContext;
	}
	
	public void Show()
	{
		NotificationManager notificationManager =
				(NotificationManager) appContext.getSystemService(Context.NOTIFICATION_SERVICE);
		
		Notification.Builder notificationBuilderObj =
				new Notification.Builder(appContext, channelId)
					.setContentTitle(tittle)
					.setContentText(body)
					.setSmallIcon(R.drawable.fui_ic_twitter_bird_white_24dp)
					.setAutoCancel(true);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
		{
			NotificationChannel channelObj = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
			channelObj.enableLights(true);
			channelObj.setLightColor(Color.YELLOW);
			channelObj.enableVibration(false);
			//channelObj.setVibrationPattern(new long[]{ 100, 200, 300, 400, 500, 400, 300, 200, 400 });
			
			notificationManager.createNotificationChannel(channelObj);
			//notificationBuilderObj.setChannelId(channelId);
		}
		
		Notification notificationObj = notificationBuilderObj.build();
		notificationObj.flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(0, notificationObj);
	}
}
