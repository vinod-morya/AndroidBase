package com.company.product.Support;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import com.airbnb.lottie.LottieAnimationView;
import com.company.product.R;

/**
 * Created by burakustun on 03/02/2018.
 */

public class LottieDialogFragment extends DialogFragment {
	
	String jsonFileName;
	boolean isLoopEnabled;
	LottieProgressDialog progressDialog;
	
	/***
	 *
	 * @param jsonFileName json file name in src/main/assets example: "gears.json"
	 * @return
	 */
	public LottieDialogFragment newInstance(String jsonFileName) {
		LottieDialogFragment fragment = new LottieDialogFragment();
		
		Bundle args = new Bundle();
		args.putString("jsonFileName", jsonFileName);
		args.putBoolean("isLoopEnabled", true);
		fragment.setArguments(args);
		
		return fragment;
	}
	
	/***
	 *
	 * @param jsonFileName json file name in src/main/assets example: "gears.json"
	 * @param isLoopEnabled if true animation will replay itself
	 * @return
	 */
	public LottieDialogFragment newInstance(String jsonFileName, boolean isLoopEnabled) {
		
		LottieDialogFragment fragment = new LottieDialogFragment();
		
		Bundle args = new Bundle();
		args.putString("jsonFileName", jsonFileName);
		args.putBoolean("isLoopEnabled", isLoopEnabled);
		fragment.setArguments(args);
		
		return fragment;
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		jsonFileName = getArguments().getString("jsonFileName");
		isLoopEnabled = getArguments().getBoolean("isLoopEnabled");
		
		if (jsonFileName == null) {
			jsonFileName = "spinner_loading.json";
		}
		progressDialog = new LottieProgressDialog(getActivity());
		progressDialog.setJsonFileName(jsonFileName);
		progressDialog.setLoop(isLoopEnabled);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return progressDialog;
	}
	
	@Override
	public void onDismiss(DialogInterface dialog) {
		super.onDismiss(dialog);
	}
	
	private class LottieProgressDialog extends AlertDialog {
		private LottieAnimationView lavProgress;
		private String jsonFileName;
		private boolean loop = true;
		
		private LottieProgressDialog(Context context) {
			super(context);
		}
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.dialog_lottie);
			
			Window currentWindow = this.getWindow();
			
			if (currentWindow != null) {
				currentWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			}
			
			lavProgress = findViewById(R.id.lavProgress);
			
			lavProgress.setAnimation(jsonFileName);
			lavProgress.loop(loop);
			lavProgress.playAnimation();
		}
		
		@Override
		public void dismiss() {
			if (lavProgress != null) {
				lavProgress.cancelAnimation();
			}
			super.dismiss();
		}
		
		private String getJsonFileName() {
			return jsonFileName;
		}
		
		private void setJsonFileName(String jsonFileName) {
			this.jsonFileName = jsonFileName;
		}
		
		private boolean isLoop() {
			return loop;
		}
		
		private void setLoop(boolean loop) {
			this.loop = loop;
		}
	}
	
}