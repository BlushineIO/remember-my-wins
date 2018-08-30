package io.blushine.rmw.settings;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.app.ActionBar;
import android.support.v7.preference.Preference;

import de.mrapp.android.preference.ListPreference;
import io.blushine.android.AppPreferenceFragment;
import io.blushine.android.legal.SettingsLegalFragment;
import io.blushine.android.preference.TimePreference;
import io.blushine.rmw.R;
import io.blushine.rmw.util.AppActivity;
import io.blushine.utils.EventBus;

/**
 * All the settings for Celebratorica App
 */
public class SettingsFragment extends AppPreferenceFragment {

@Override
public void onCreate(@Nullable Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	Resources resources = getResources();
	
	// Notification reminder
	final TimePreference timePreference = (TimePreference) findPreference(resources.getString(R.string.setting_reminder_time_1_key));
	timePreference.setOnPreferenceChangeListener((preference, newValue) -> {
		// TODO update notification time
		return true;
	});
	// TODO set notification time as subtitle
	
	final SwitchPreference notificationEnabledPreference = (SwitchPreference) findPreference(resources.getString(R.string.setting_reminder_key));
	notificationEnabledPreference.setOnPreferenceChangeListener((preference, newValue) -> {
		if (newValue instanceof Boolean) {
			boolean enabled = (boolean) newValue;
			
			// TODO enable/disable notification
		}
		return true;
	});
	
	final ListPreference storageLocationPreference = (ListPreference) findPreference(resources.getString(R.string.setting_store_location_key));
	storageLocationPreference.setOnPreferenceChangeListener((preference, newValue) -> {
		// TODO Show another dialog to either accept privacy policy or show ability to delete all online content
		
		if (newValue instanceof String) {
			StorageLocations newLocation = StorageLocations.Companion.toEnum((String) newValue);
			EventBus.getInstance().post(newLocation);
			return true;
		} else {
			return false;
		}
	});
	
	
	// Legal
	final Preference legalPreference = findPreference(resources.getString(R.string.legal_key));
	legalPreference.setOnPreferenceClickListener(preference -> {
		SettingsLegalFragment fragment = new SettingsLegalFragment();
		fragment.show();
		return true;
	});
}

@Override
public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
	addPreferencesFromResource(R.xml.settings);
}

@Override
public void onResume() {
	super.onResume();
	ActionBar actionBar = AppActivity.getActivity().getSupportActionBar();
	if (actionBar != null) {
		actionBar.setTitle(R.string.settings);
	}
}
}