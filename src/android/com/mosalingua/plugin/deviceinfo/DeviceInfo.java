/*
 *
 * 
 */
package com.mosalingua.plugin.deviceinfo;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.provider.Settings;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceInfo extends CordovaPlugin {

    //actions
    public static String ACTION_GET_IMEI = "getIMEI";
    public static String ACTION_GET_SERIAL_NUMBER = "getSerialNumber";
    public static String ACTION_GET_DEVICE_INFO = "getDeviceInfo";

    //seen on Galaxy Tab 2
    public static String PROPERTY_SERIAL_TAB2 = "ril.serialnumber";
    //seen on Galaxy Tab 3
    public static String PROPERTY_SERIAL_TAB3 = "sys.serialnumber";

	//public static String uuid; // Device UUID
	public static String imei; // Device IMEI
	//public static String imsi; // Device IMSI
	//public static String iccid; // Sim IMSI
	//public static String mac; // MAC address
	public static String serial; // Serial number

	/**
	 * Constructor.
	 */
	public DeviceInfo() {
	}

	/**
	 * Sets the context of the Command. This can then be used to do things like
	 * get file paths associated with the Activity.
	 *
	 * @param cordova The context of the main Activity.
	 * @param webView The CordovaWebView Cordova is running in.
	 */
	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		Context context = cordova.getActivity().getApplicationContext();
		//UID.uuid = getUuid(context);
		DeviceInfo.imei = getIMEI(context);
		//UID.imsi = getImsi(context);
		//UID.iccid = getIccid(context);
		//UID.mac = getMac(context);
		DeviceInfo.serial = getSerialNumber(context);
	}*/

	/**
	 * Executes the request and returns PluginResult.
	 *
	 * @param action            The action to execute.
	 * @param args              JSONArry of arguments for the plugin.
	 * @param callbackContext   The callback id used when calling back into JavaScript.
	 * @return                  True if the action was valid, false if not.
	 */
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		
		if (action.equals(ACTION_GET_IMEI)) {
			JSONObject r = new JSONObject();
			r.put("IMEI", DeviceInfo.imei);
			callbackContext.success(r);
		} 
		else if (action.equals(ACTION_GET_SERIAL_NUMBER)) {
			JSONObject r = new JSONObject();
			r.put("SERIAL", DeviceInfo.serial);
			callbackContext.success(r);
		}
		else if (action.equals(ACTION_GET_DEVICE_INFO)) {
			JSONObject r = new JSONObject();
			r.put("IMEI", DeviceInfo.imei);
			r.put("SERIAL", DeviceInfo.serial);
			callbackContext.success(r);
		}
		else {
			return false;
		}
		return true;
	}

	/**
	 * Get the device's Serial Number.
	 *
	 * @param context The context of the main Activity.
	 * @return
	 */
	public String getSerialNumber(Context context) {

		String serial = null;
		try {
			 //first attempt
			 serial = getManufacturerSerialNumberGalaxyTab(PROPERTY_SERIAL_TAB2);
			 //try another property if failed
			 if(serial==null) {
			 	serial = getManufacturerSerialNumberGalaxyTab(PROPERTY_SERIAL_TAB3);
			 }
		}
		catch(Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		finally {
			if(serial==null) {
				serial = "";
			}
		}
		
		return serial;
		
		
	}

	/**
	 * Get the device's International Mobile Station Equipment Identity (IMEI).
	 *
	 * @param context The context of the main Activity.
	 * @return
	 */
	public String getIMEI(Context context) {

		String imei = "";
		
		try {
			final TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		    imei = mTelephony.getDeviceId();
		}
		catch(Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		return imei;
		
	}

    //http://stackoverflow.com/questions/14161282/serial-number-from-samsung-device-running-android
	public String getManufacturerSerialNumberGalaxyTab(string property) {
	    String serial = null; 
	    try {
	        Class<?> c = Class.forName("android.os.SystemProperties");
	        Method get = c.getMethod("get", String.class, String.class);
	        serial = (String) get.invoke(c, property, "unknown");
	  	} 
	  	catch (Exception ignored) {
	  		//we don´t need to log this one as it might be expected after all
	  	}
  		return serial;
	}

	/**
	 * Get the device's Universally Unique Identifier (UUID).
	 *
	 * @param context The context of the main Activity.
	 * @return
	 */
	/**public String getUuid(Context context) {
		String uuid = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
		return uuid;
	}*/

	/**
	 * Get the device's International mobile Subscriber Identity (IMSI).
	 *
	 * @param context The context of the main Activity.
	 * @return
	 */
	/**public String getImsi(Context context) {
		final TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String imsi = mTelephony.getSubscriberId();
		return imsi;
	}*/

	/**
	 * Get the sim's Integrated Circuit Card Identifier (ICCID).
	 *
	 * @param context The context of the main Activity.
	 * @return
	 */
	/**public String getIccid(Context context) {
		final TelephonyManager mTelephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		String iccid = mTelephony.getSimSerialNumber();
		return iccid;
	}*/

	/**
	 * Get the Media Access Control address (MAC).
	 *
	 * @param context The context of the main Activity.
	 * @return
	 */
	/**public String getMac(Context context) {
		final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		final WifiInfo wInfo = wifiManager.getConnectionInfo();
		String mac = wInfo.getMacAddress();
		return mac;
	}*/

}
