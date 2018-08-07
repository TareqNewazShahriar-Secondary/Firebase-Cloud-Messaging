package com.doesnothaveadomain.firebase_cloud_messaging.utilities;

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
		Map<String, String> nData;
		
		try
		{
			nTitle = remoteMessage.getNotification().getTitle();
			nBody = remoteMessage.getNotification().getBody();
			nData = remoteMessage.getData();
			
			Log.d(TAG,  nTitle);
			Log.d(TAG, nBody);
			Log.d(TAG, nData.toString());
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
	}
	
	@Override
	public void onNewToken(String s)
	{
		super.onNewToken(s);
		Log.d("NEW_TOKEN", s);
		Log.d("fire token", FirebaseInstanceId.getInstance().getToken());
	}
}
