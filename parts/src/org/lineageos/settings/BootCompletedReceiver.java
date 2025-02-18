/*
 * Copyright (C) 2015 The CyanogenMod Project
 *               2017-2018 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.provider.Settings;

import org.lineageos.settings.soundcontrol.SoundControlSettings;
import org.lineageos.settings.soundcontrol.SoundControlFileUtils;
import org.lineageos.settings.dirac.DiracUtils;

public class BootCompletedReceiver extends BroadcastReceiver {
    private static final boolean DEBUG = false;
    private static final String TAG = "XiaomiParts";

    @Override
    public void onReceive(final Context context, Intent intent) {
        int gain = Settings.Secure.getInt(
            context.getContentResolver(),
            SoundControlSettings.PREF_VOLUME_GAIN, 0
        );
        SoundControlFileUtils.setValue(
            SoundControlSettings.VOLUME_GAIN_PATH,
            gain + " " + gain
        );

        SoundControlFileUtils.setValue(
            SoundControlSettings.MICROPHONE_GAIN_PATH,
            Settings.Secure.getInt(
                context.getContentResolver(),
                SoundControlSettings.PREF_MICROPHONE_GAIN,
                0
            )
        );

        new DiracUtils(context).onBootCompleted();
    }
}
