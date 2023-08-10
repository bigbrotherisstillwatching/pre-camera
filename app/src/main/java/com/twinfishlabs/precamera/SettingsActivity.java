package com.twinfishlabs.precamera;

import android.app.ActionBar;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.widget.Toast;
import com.facebook.flipper.android.AndroidFlipperClient;
import com.facebook.flipper.android.utils.FlipperUtils;
import com.facebook.flipper.core.FlipperClient;
import com.facebook.flipper.plugins.inspector.DescriptorMapping;
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin;
import com.facebook.soloader.SoLoader;

public class SettingsActivity extends PreferenceActivity implements OnPreferenceChangeListener {
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setTitle(getText(R.string.settings));
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
//			actionBar.setDisplayShowHomeEnabled(false);
			actionBar.setTitle(R.string.settings);
		}

		addPreferencesFromResource(R.xml.setting_preference);

//		Preference abcPositionPref = findPreference(PreferenceUtils.PREF_KEY_ABC_POSITION);
//		abcPositionPref.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
//			public boolean onPreferenceChange(Preference pref, Object newValue) {
//				int newAbcPosition = Integer.parseInt(newValue.toString());
//				PreferenceUtils.setAbcPosition(newAbcPosition);
//				return true;
//			}
//		});

		PreferenceScreen screen = getPreferenceScreen();
		for (int i = 0; i < screen.getPreferenceCount(); i++) {
			screen.getPreference(i).setOnPreferenceChangeListener(this);
		}

		SoLoader.init(this, false);
		
		if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
			final FlipperClient client = AndroidFlipperClient.getInstance(this);
			client.addPlugin(new InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()));
			client.start();
		}
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		PrefUtils.notifyChanged();
		return true;
	}
}
