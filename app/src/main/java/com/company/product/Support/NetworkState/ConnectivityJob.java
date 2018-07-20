package com.company.product.Support.NetworkState;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.orhanobut.logger.Logger;

public class ConnectivityJob extends JobService {
	
	private ConnectivityManager connectivityManager;
	private ConnectivityManager.NetworkCallback networkCallback;
	private BroadcastReceiver connectivityChange;
	
	@Override
	public boolean onStartJob(JobParameters job) {
		connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().build(), networkCallback = new ConnectivityManager.NetworkCallback() {
				// -Snip-
			});
		} else {
			registerReceiver(connectivityChange = new BroadcastReceiver() {
				@Override
				public void onReceive(Context context, Intent intent) {
					handleConnectivityChange(!intent.hasExtra("noConnectivity"), intent.getIntExtra("networkType", -1));
				}
			}, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
		}
		
		NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
		if (activeNetwork == null) {
			Logger.e("vinod - No active network.");
		} else {
			// Some logic..
			Logger.e("vinod - Found active network.");
		}
//		Logger.e("vinod - Done with onStartJob");
		return true;
	}
	
	
	@Override
	public boolean onStopJob(JobParameters job) {
		if (networkCallback != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) connectivityManager.unregisterNetworkCallback(networkCallback);
		else if (connectivityChange != null) unregisterReceiver(connectivityChange);
		return true;
	}
	
	private void handleConnectivityChange(boolean connected, int type) {
		// Calls handleConnectivityChange(boolean connected, ConnectionType connectionType)
	}
	
	private void handleConnectivityChange(NetworkInfo networkInfo) {
		// Calls handleConnectivityChange(boolean connected, int type)
	}
	
	private void handleConnectivityChange(boolean connected, ConnectionType connectionType) {
		// Logic based on the new connection
	}
	
	private enum ConnectionType {
		MOBILE, WIFI, VPN, OTHER;
	}
}