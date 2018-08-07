package com.doesnothaveadomain.firebase_cloud_messaging;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.doesnothaveadomain.firebase_cloud_messaging.utilities.FirebaseUtil;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;

public class LoginActivity extends AppCompatActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		FirebaseUtil.openFirebaseReference(this);
		FirebaseUtil.attachListner();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_menu, menu);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.get_user_firebase_token:
				String id = FirebaseInstanceId.getInstance().getId();
				Log.d("toekn", id);
				return true;
			case R.id.signout_menu:
				AuthUI.getInstance()
						.signOut(this)
						.addOnCompleteListener(new OnCompleteListener<Void>() {
							public void onComplete(@NonNull Task<Void> task) {
								Log.d("sign-out", "Signed-out");
								Toast.makeText(LoginActivity.this, "Signed-out.", Toast.LENGTH_SHORT).show();
								FirebaseUtil.attachListner();
							}
						});
				FirebaseUtil.detachListner();
				return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		FirebaseUtil.detachListner();
	}
}
