package io.blushine.celebratorica.settings;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import io.blushine.android.util.Time;
import io.blushine.celebratorica.R;
import io.blushine.celebratorica.util.AppActivity;

/**
 * Gateway for settings in the preference file
 */
public class SettingsPrefsGateway {
private static final String REMINDER_TIME_1_KEY = AppActivity.getActivity().getResources().getString(R.string.setting_reminder_time_1_key);
private final SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(AppActivity.getActivity());

/**
 * Get the reminder (notification) time
 * @return reminder (notification) time
 */
Time getReminderTime() {
	int seconds = mPreferences.getInt(REMINDER_TIME_1_KEY, 0);
	return new Time(seconds);
}
}
