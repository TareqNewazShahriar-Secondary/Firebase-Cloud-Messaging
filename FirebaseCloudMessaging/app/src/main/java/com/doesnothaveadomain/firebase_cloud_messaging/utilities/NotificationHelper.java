package com.doesnothaveadomain.firebase_cloud_messaging.utilities;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import com.doesnothaveadomain.firebase_cloud_messaging.R;

public class NotificationHelper
{
	public static Activity activityScope = null;
	
	String tittle = "";
	String body = "";
	Activity activity = null;
	
	public NotificationHelper(Activity activity, String title, String body)
	{
		this.tittle = title;
		this.body = body;
		this.activity = activity;
	}
	
	public void Show()
	{
		NotificationManager notificationManager =
				(NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
		
		String channelId = "_default_id_";
		CharSequence channelName = "Default";
		
		Notification.Builder notificationBuilderObj =
				new Notification.Builder(activity.getApplicationContext(), channelId)
					.setContentTitle(tittle)
					.setContentText(body)
					.setSmallIcon(R.drawable.fui_ic_twitter_bird_white_24dp)
					.setAutoCancel(true);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
		{
			NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
			notificationChannel.enableLights(true);
			notificationChannel.setLightColor(Color.BLUE);
			notificationChannel.enableVibration(true);
			notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
			
			notificationManager.createNotificationChannel(notificationChannel);
			//notificationBuilderObj.setChannelId(channelId);
		}
		
		Notification notificationObj = notificationBuilderObj.build();
		notificationObj.flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(0, notificationObj);
	}
}
