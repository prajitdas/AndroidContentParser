package com.prajitdas.parserapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prajitdas.parserapp.util.ProviderContent.ProviderItem;

import android.app.Application;
import android.content.res.Configuration;
import android.widget.Toast;

public class ParserApplication extends Application {
	
	private static boolean audioAccessPolicyAllowed;
	
	private static final String CONTACT_BUTTON_LOADER = "getContactUsingloader";

	private static final String CONTACT_BUTTON_QUERY = "getContactUsingQuery";
	/**
	 * public static final Strings for known content providers
	 */
	private static final String CONTACT_PROVIDER = "ContactProvider";
	
	/**
	 * public static final Strings for known content providers
	 */
	private static final String CONTACTS_PROVIDER = "ContactsProvider"; 
	
	private static boolean contactsAccessPolicyAllowed;
	
	private static final String DEBUG_TAG = "ParserApplicationDebugTag";

	private static boolean imageAccessPolicyAllowed;

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, ProviderItem> ITEM_MAP = new HashMap<String, ProviderItem>();

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<ProviderItem> ITEMS = new ArrayList<ProviderItem>();

	private static final String MEDIA_BUTTON_LOADER = "getMediaUsingloader";

	private static final String MEDIA_BUTTON_QUERY = "getMediaUsingQuery";

	private static boolean mediaAccessPolicyAllowed;
	
	private static int ProviderCount;
	
	private static String queryOrLoader;

	private static ParserApplication singleton;
	
	private static boolean videoAccessPolicyAllowed;
	
	public static String getContactButtonLoader() {
		return CONTACT_BUTTON_LOADER;
	}
	
	public static String getContactButtonQuery() {
		return CONTACT_BUTTON_QUERY;
	}

	public static String getContactProvider() {
		return CONTACT_PROVIDER;
	}

	public static String getContactsProvider() {
		return CONTACTS_PROVIDER;
	}

	public static String getDebugTag() {
		return DEBUG_TAG;
	}

	public static Map<String, ProviderItem> getITEM_MAP() {
		return ITEM_MAP;
	}

	public static List<ProviderItem> getITEMS() {
		return ITEMS;
	}

	public static String [] getITEMSStringVal() {
		String [] tempList = new String[ITEMS.size()];
		for(int i = 0; i < ITEMS.size(); i++)
			tempList[i] = ITEMS.get(i).toString();
		return tempList;
	}

	public static String getMediaButtonLoader() {
		return MEDIA_BUTTON_LOADER;
	}
	
	public static String getMediaButtonQuery() {
		return MEDIA_BUTTON_QUERY;
	}

	public static int getProviderCount() {
		return ProviderCount;
	}

	public static String getQueryOrLoader() {
		return queryOrLoader;
	}

	/**
	 * @return the singleton
	 */
	public static ParserApplication getSingleton() {
		return singleton;
	}

	public static boolean isAudioAccessPolicyAllowed() {
		return audioAccessPolicyAllowed;
	}

	public static boolean isContactsAccessPolicyAllowed() {
		return contactsAccessPolicyAllowed;
	}

	public static boolean isImageAccessPolicyAllowed() {
		return imageAccessPolicyAllowed;
	}

	public static boolean isMediaAccessPolicyAllowed() {
		return mediaAccessPolicyAllowed;
	}

	public static boolean isVideoAccessPolicyAllowed() {
		return videoAccessPolicyAllowed;
	}

	public static void setAudioAccessPolicyAllowed(boolean audioAccessPolicyAllowed) {
		ParserApplication.audioAccessPolicyAllowed = audioAccessPolicyAllowed;
	}
	
	public static void setContactsAccessPolicyAllowed(
			boolean contactsAccessPolicyAllowed) {
		ParserApplication.contactsAccessPolicyAllowed = contactsAccessPolicyAllowed;
	}

	public static void setImageAccessPolicyAllowed(boolean imageAccessPolicyAllowed) {
		ParserApplication.imageAccessPolicyAllowed = imageAccessPolicyAllowed;
	}

	public static void setITEM_MAP(Map<String, ProviderItem> iTEM_MAP) {
		ITEM_MAP = iTEM_MAP;
	}

	public static void setITEMS(List<ProviderItem> iTEMS) {
		ITEMS = iTEMS;
	}

	public static void setMediaAccessPolicyAllowed(boolean mediaAccessPolicyAllowed) {
		ParserApplication.mediaAccessPolicyAllowed = mediaAccessPolicyAllowed;
	}

	public static void setProviderCount(int providerCount) {
		ProviderCount = providerCount;
	}

	public static void setQueryOrLoader(String aQueryOrLoaderString) {
		queryOrLoader = aQueryOrLoaderString;
	}

	/**
	 * @param singleton the singleton to set
	 */
	public static void setSingleton(ParserApplication singleton) {
		ParserApplication.singleton = singleton;
	}

	public static void setVideoAccessPolicyAllowed(boolean videoAccessPolicyAllowed) {
		ParserApplication.videoAccessPolicyAllowed = videoAccessPolicyAllowed;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		setSingleton(this);
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}
	
	public static void makeToast(String someString) {
		Toast.makeText(ParserApplication.getSingleton(), 
				someString, Toast.LENGTH_LONG).show();
	}
}