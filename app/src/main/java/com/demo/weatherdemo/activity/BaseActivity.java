package com.demo.weatherdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.demo.weatherdemo.R;

public abstract class BaseActivity extends AppCompatActivity {
	@Override
	public void onCreate (@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
		super.onCreate(savedInstanceState, persistentState);
		setFullscreen(false,false);
	}

	abstract void initView();
	abstract void initData();
	abstract int getLayout();

	public void setFullscreen(boolean isShowStatusBar, boolean isShowNavigationBar) {
		int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

		if (!isShowStatusBar) {
			uiOptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
		}
		if (!isShowNavigationBar) {
			uiOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
		}
		getWindow().getDecorView().setSystemUiVisibility(uiOptions);

		//专门设置一下状态栏导航栏背景颜色为透明，凸显效果。
		setNavigationStatusColor(Color.TRANSPARENT);
	}

	public void setNavigationStatusColor(int color) {
		//VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		getWindow().setNavigationBarColor(color);
		getWindow().setStatusBarColor(color);
	}

	@Override
	public boolean onKeyDown (int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent home = new Intent(Intent.ACTION_MAIN);
			home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			home.addCategory(Intent.CATEGORY_HOME);
			startActivity(home);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
