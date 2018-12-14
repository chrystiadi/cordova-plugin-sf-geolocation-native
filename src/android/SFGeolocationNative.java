package com.smartfren.cordova.sfgeolocationnative;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class SFGeolocationNative extends CordovaPlugin implements LocationListener {
	private Context context;
	private LocationManager mLocManager;
	private Location mLoc;
	private boolean isGPSenabled;
	private boolean isGPSActive;
    private boolean isNetworkEnabled;
    private boolean isNetworkActive;

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

		try {
			if (action.equals("getCurrentLocation")) {
				mLocManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	            mLoc = getGPSLocation();
	            if (mLoc == null) {
	                mLoc = getNetworkLocation();
	            }
	            
	            double longitude = mLoc.getLongitude();
	            double latitude = mLoc.getLatitude();
	            double accuracy = mLoc.getAccuracy();
	            String provider = mLoc.getProvider();
	            
	            JSONObject jsonObject = new JSONObject();
	            jsonObject.put("latitude", latitude);
	            jsonObject.put("longitude", longitude);
	            jsonObject.put("accuracy", accuracy);
	            jsonObject.put("provider", provider);
	            callbackContext.success(jsonObject);
			}
			return false;
		} catch (Exception e) {
			callbackContext.success("N/A");package com.smartfren.cordova.sfgeolocationnative;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class SFGeolocationNative extends CordovaPlugin implements LocationListener {
	private LocationManager mLocManager;
	private Location mLoc;
	private boolean isGPSenabled;
	private boolean isGPSActive;
    private boolean isNetworkEnabled;
    private boolean isNetworkActive;

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

		try {
			if (action.equals("getCurrentLocation")) {
				mLocManager = (LocationManager) this.cordova.getActivity().getSystemService(Context.LOCATION_SERVICE);
	            mLoc = getGPSLocation();
	            if (mLoc == null) {
	                mLoc = getNetworkLocation();
	            }
	            
	            double longitude = mLoc.getLongitude();
	            double latitude = mLoc.getLatitude();
	            double accuracy = mLoc.getAccuracy();
	            String provider = mLoc.getProvider();
	            
	            JSONObject jsonObject = new JSONObject();
	            jsonObject.put("latitude", latitude);
	            jsonObject.put("longitude", longitude);
	            jsonObject.put("accuracy", accuracy);
	            jsonObject.put("provider", provider);
	            callbackContext.success(jsonObject);
			}
			return false;
		} catch (Exception e) {
			callbackContext.success("N/A");
			return true;
		}
	}

	@Override
	public void onLocationChanged(Location location) {
        mLoc = location;
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}
	
	private Location getGPSLocation() {
        isGPSenabled = mLocManager.isProviderEnabled("gps");
        Location loc = null;
        if (isGPSenabled) {
            if (isGPSActive) {
                mLocManager.requestLocationUpdates("gps", 1000L, 0.0F,
                        this);
                LocationManager locationmanager = mLocManager;

                if (locationmanager != null) {
                    loc = mLocManager.getLastKnownLocation("gps");
                }
            }
        }
        return loc;
    }

    private Location getNetworkLocation() {
        isNetworkEnabled = mLocManager.isProviderEnabled("network");
        Location loc = null;
        if (isNetworkEnabled) {
            if (isNetworkActive) {
                mLocManager.requestLocationUpdates("network", 1000L, 0.0F,
                        this);
                LocationManager locationmanager = mLocManager;

                if (locationmanager != null) {
                    loc = mLocManager.getLastKnownLocation("network");
                }
            }
        }
        return loc;
    }
}

			return true;
		}
	}

	@Override
	public void onLocationChanged(Location location) {
        mLoc = location;
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}
	
	private Location getGPSLocation() {
        isGPSenabled = mLocManager.isProviderEnabled("gps");
        Location loc = null;
        if (isGPSenabled) {
            if (isGPSActive) {
                mLocManager.requestLocationUpdates("gps", 1000L, 0.0F,
                        this);
                LocationManager locationmanager = mLocManager;

                if (locationmanager != null) {
                    loc = mLocManager.getLastKnownLocation("gps");
                }
            }
        }
        return loc;
    }

    private Location getNetworkLocation() {
        isNetworkEnabled = mLocManager.isProviderEnabled("network");
        Location loc = null;
        if (isNetworkEnabled) {
            if (isNetworkActive) {
                mLocManager.requestLocationUpdates("network", 1000L, 0.0F,
                        this);
                LocationManager locationmanager = mLocManager;

                if (locationmanager != null) {
                    loc = mLocManager.getLastKnownLocation("network");
                }
            }
        }
        return loc;
    }
}
