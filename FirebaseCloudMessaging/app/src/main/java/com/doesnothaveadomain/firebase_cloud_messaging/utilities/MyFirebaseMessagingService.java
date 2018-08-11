package com.doesnothaveadomain.firebase_cloud_messaging.utilities;

import android.app.Service;
import android.app.job.JobService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import com.doesnothaveadomain.firebase_cloud_messaging.LoginActivity;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService
{
	private static final String TAG  = "FCM_Log";
	
	@Override
	public void onMessageReceived(RemoteMessage remoteMessage)
	{
		String  nBody = "",
				nTitle = "";
		Map<String, String> nData = null;
		
		try
		{
			if(remoteMessage.getNotification() != null)
			{
				nTitle = remoteMessage.getNotification().getTitle();
				nBody = remoteMessage.getNotification().getBody();
			}
			if(remoteMessage.getData() != null)
				nData = remoteMessage.getData();
			
			Log.d(TAG,  nTitle);
			Log.d(TAG, nBody);
			Log.d(TAG, nData.toString());
			
			new NotificationHelper(getApplicationContext(),
						nTitle,
						nBody + nData.toString())
						.Show();
			///Toast.makeText(LoginActivity.this, "Got push notification: " + nBody, Toast.LENGTH_LONG).show();
		}
		catch(NullPointerException e)
		{
			Log.e(TAG, e.getMessage());
		}
	}
	
	@Override
	public void onDeletedMessages()
	{
		super.onDeletedMessages();
		new NotificationHelper(NotificationHelper.activityScope, "nTitle","on delete message").Show();
	}
	
	@Override
	public void onNewToken(String s)
	{
		Log.d(TAG, "New token acquired" + s);
		new NotificationHelper(getApplicationContext(), "nTitle","on new token").Show();
		//FirebaseUtil.InsertDeviceToken(s);
	}
}
