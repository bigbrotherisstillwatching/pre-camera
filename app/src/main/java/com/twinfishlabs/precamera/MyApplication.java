package com.twinfishlabs.precamera;

import android.app.Application;
import com.facebook.flipper.android.AndroidFlipperClient;
import com.facebook.flipper.android.utils.FlipperUtils;
import com.facebook.flipper.core.FlipperClient;
import com.facebook.flipper.plugins.inspector.DescriptorMapping;
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin;
import com.facebook.soloader.SoLoader;

public class MyApplication extends Application {

	static public MyApplication Instance;

	public MyApplication() {
		Instance = this;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Configs.init();
		PrefUtils.init();
		CamcorderManager.init();

		SoLoader.init(this, false);
		
		if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
			final FlipperClient client = AndroidFlipperClient.getInstance(this);
			client.addPlugin(new InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()));
			client.start();
		}
	}
}
