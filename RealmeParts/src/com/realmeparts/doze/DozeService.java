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

package com.realmeparts.doze;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class DozeService extends Service {
    private static final String TAG = "DozeService";
    private static final boolean DEBUG = false;

    private ProximitySensor mProximitySensor;
    private TiltSensor mTiltSensor;
    private AmdSensor mAmdSensor;
    private FODProxCheck mFODProxCheck;

    private BroadcastReceiver mScreenStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                onDisplayOn();
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                onDisplayOff();
            }
        }
    };

    @Override
    public void onCreate() {
        if (DEBUG) Log.d(TAG, "Creating service");
        mProximitySensor = new ProximitySensor(this);
        mTiltSensor = new TiltSensor(this);
        mAmdSensor = new AmdSensor(this);
	mFODProxCheck = new FODProxCheck(this);

        IntentFilter screenStateFilter = new IntentFilter();
        screenStateFilter.addAction(Intent.ACTION_SCREEN_ON);
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mScreenStateReceiver, screenStateFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (DEBUG) Log.d(TAG, "Starting service");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (DEBUG) Log.d(TAG, "Destroying service");
        super.onDestroy();
        this.unregisterReceiver(mScreenStateReceiver);
        mProximitySensor.disable();
        mTiltSensor.disable();
        mAmdSensor.disable();
	mFODProxCheck.disable();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void onDisplayOn() {
        if (DEBUG) Log.d(TAG, "Display on");
        if (DozeUtils.isPickUpEnabled(this)) {
            mTiltSensor.disable();
        }
        if (DozeUtils.isSmartWakeEnabled(this)) {
            mAmdSensor.disable();
        }
        if (DozeUtils.isPocketGestureEnabled(this)) {
            mProximitySensor.disable();
        }

	if (DozeUtils.isFODWakeEnabled(this)) {
            mFODProxCheck.disable();
            mFODProxCheck.tapcheck();
        }
    }

    private void onDisplayOff() {
        if (DEBUG) Log.d(TAG, "Display off");
        if (DozeUtils.isPickUpEnabled(this)) {
            mTiltSensor.enable();
        }
        if (DozeUtils.isSmartWakeEnabled(this) && DozeUtils.isPickUpEnabled(this)) {
            mAmdSensor.enable();
        }
        if (DozeUtils.isPocketGestureEnabled(this)) {
            mProximitySensor.enable();
        }
	if (DozeUtils.isFODWakeEnabled(this)) {
            mFODProxCheck.skip_prox_check();
        }

    }
}
